import locks.*;
import trylock.bench;
import trylock.trycriticalSection;

public class Test5 {
	private static final String TTASLock = "trypriorityCLHLock";
	private static int THREAD_COUNT;
	private static final int TOTAL_ITERS = 2520;
	private static int ITERS;
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		if (args.length >= 2)
			THREAD_COUNT = Integer.parseInt(args[1]);
		else
			THREAD_COUNT = 8;
		ITERS = TOTAL_ITERS/THREAD_COUNT;
		String lockClass = (args.length==0 ? TTASLock : args[0]);
		final trycriticalSection counter = new trycriticalSection(0, (Lock)Class.forName("locks." + lockClass).newInstance());

		final bench[] threads = new bench[THREAD_COUNT];

		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t] = new bench(counter, ITERS);
		}

		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t].start();
		}

		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t].join();
		}
		System.out.println("Lock was abandoned " + trypriorityCLHLock.trylock + " times as the timeout occured");
		System.out.println("Mutual Exclution is mantained. Counter value is " + trycriticalSection.count);	// Uncomment here
	}
}


import locks.*;
import barrier.*;

public class Test3 {

	public static int THREAD_COUNT;
	private static final String TTASLock = "TTASLock";
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		if (args.length >= 1)
			THREAD_COUNT = Integer.parseInt(args[0]);
		else
			THREAD_COUNT = 8;
		if (args.length >= 2)
			implementation.select = Integer.parseInt(args[1]);
		barriers.THREAD_COUNT = THREAD_COUNT;
		implementation.THREAD_COUNT = THREAD_COUNT;
		final barriers bar = new barriers(0, (Lock)Class.forName("locks." + TTASLock).newInstance());
		
		final implementation[] threads = new implementation[THREAD_COUNT];
		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t] = new implementation(bar);
		}

		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t].start();
		}

		long totalTime = 0;
		for(int t=0; t<THREAD_COUNT; t++) {
			totalTime += threads[t].getElapsedTime();
			threads[t].join();
		}

		System.out.println("Average waiting time per thread is " + totalTime/THREAD_COUNT + "ms");
	}
}

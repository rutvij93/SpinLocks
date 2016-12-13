import locks.*;
import bench.*;

public class Test4 {
	private static final String TTASLock = "priorityCLHLock";
	private static int THREAD_COUNT;
	private static final int TOTAL_ITERS = 3000;
	private static int ITERS;
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		System.out.println("Threads with even ID have higher priority (1). Change priority setting in TestThread2 if needed.");
		if (args.length >= 2)
			THREAD_COUNT = Integer.parseInt(args[1]);
		else
			THREAD_COUNT = 2;
		ITERS = TOTAL_ITERS/THREAD_COUNT;
		String lockClass = (args.length==0 ? TTASLock : args[0]);
		final criticalSection counter = new criticalSection(0, (Lock)Class.forName("locks." + lockClass).newInstance());

		final TestThread2[] threads = new TestThread2[THREAD_COUNT];

		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t] = new TestThread2(counter, ITERS);
		}

		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t].start();
		}

		long totalTime = 0;
		long totalTime2 = 0;
		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t].join();
		}
		for(int t=0; t<THREAD_COUNT; t++) {
			totalTime += threads[t].getElapsedTime();
			totalTime2 += threads[t].getAmplifiedElapsedTime();
		}

		System.out.println("Average waiting time per thread is " + totalTime/THREAD_COUNT + "ms");
		System.out.println("Amplified average waiting time per thread is " + totalTime2/THREAD_COUNT + "ms");

	}
}

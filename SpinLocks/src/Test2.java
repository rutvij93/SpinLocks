import bench.*;
import locks.*;
public class Test2 {
	private static final String TASLock = "TASLock";
	private static int THREAD_COUNT;
	private static final int TOTAL_ITERS = 64000;
	private static int ITERS;
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		if (args.length >= 2)
			THREAD_COUNT = Integer.parseInt(args[1]);
		else
			THREAD_COUNT = 2;
		ALock.capacity = THREAD_COUNT;
		ITERS = TOTAL_ITERS/THREAD_COUNT;
		String lockClass = (args.length==0 ? TASLock : args[0]);
		final criticalSection CS = new criticalSection(0, (Lock)Class.forName("locks." + lockClass).newInstance());

		final TestThread2[] threads = new TestThread2[THREAD_COUNT];
		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t] = new TestThread2(CS, ITERS);
		}

		for(int t=0; t<THREAD_COUNT; t++) {
			threads[t].start();
		}

		long time= System.currentTimeMillis();
		long end1 = time+2000;
		while(System.currentTimeMillis() < end1);
		
		TestThread2.running = false;

		System.out.println("No of CS executions for "+ THREAD_COUNT + " thread in 2 sec is " + criticalSection.count );

	}
}

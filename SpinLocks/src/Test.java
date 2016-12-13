import bench.*;
import locks.*;

public class Test {

	public static int THREAD_COUNT;
	private static final String TASLock = "CLHLock";
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (args.length >= 2)
			THREAD_COUNT = Integer.parseInt(args[1]);
		else
			THREAD_COUNT = 8;
		ALock.capacity = THREAD_COUNT;
		String lockClass = (args.length==0 ? TASLock : args[0]);
		final criticalSection CS = new criticalSection(0, (Lock)Class.forName("locks." + lockClass).newInstance());
		for(int t=0; t<THREAD_COUNT; t++)
			new TestThread(CS).start();
	}
}

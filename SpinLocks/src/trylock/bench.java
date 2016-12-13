package trylock;

import bench.ThreadId;
//import locks.trypriorityCLHLock;

public class bench extends Thread implements ThreadId{
	private static int ID_GEN = 0;
	private trycriticalSection CS;
	private int id;
	private long elapsed;
	private int iter;
	public volatile static boolean running = true;

	public bench(trycriticalSection CS, int iter) {
		id = ID_GEN++;
		this.CS = CS;
		this.iter = iter;
	}
	
	@Override
	public void run() {
		if (id%2 == 0){
			Thread.currentThread().setPriority(1);								//Customize Priority setting here. Uncomment line 24
		}
		long start = System.currentTimeMillis();
		for(int i=0; i<iter; i++)
			CS.emptyCS();
		long end = System.currentTimeMillis();
		elapsed = end - start;
//		System.out.println("Lock was abandoned " + trypriorityCLHLock.trylock + " times as the timeout occured");
	}
	
	public int getThreadId(){
		return id;
	}

	public long getElapsedTime() {
		return elapsed;
	}
}

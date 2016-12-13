package bench;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public class TestThread2 extends Thread implements ThreadId{
	private static int ID_GEN = 0;
	private criticalSection CS;
	private int id;
	private long elapsed;
	private long elapsed2;
	private int iter;
	public volatile static boolean running = true;

	public TestThread2(criticalSection CS, int iter) {
		id = ID_GEN++;
		this.CS = CS;
		this.iter = iter;
	}
	
	@Override
	public void run() {
		if (id%2 == 0){
			Thread.currentThread().setPriority(1);								//Customize Priority setting here. Uncomment line 24
		}
//		Thread.currentThread().setPriority(ThreadLocalRandom.current().nextInt(1, 5 + 1));	// For random priority
		long start = System.currentTimeMillis();
		for(int i=0; i<iter; i++)
			CS.emptyCS();
		long end = System.currentTimeMillis();
		elapsed = end - start;
		elapsed2 = elapsed * Thread.currentThread().getPriority();
		System.out.println("Waiting time for thread with ID = " + id + " and priority = " + Thread.currentThread().getPriority() + " is "+ elapsed + "ms" + ". Its amplified wait time is " + elapsed2 + "ms");
	}
	
	public int getThreadId(){
		return id;
	}

	public long getElapsedTime() {
		return elapsed;
	}
	public long getAmplifiedElapsedTime() {
		return elapsed2;
	}
}

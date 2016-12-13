package bench;

import java.util.concurrent.ThreadLocalRandom;

public class TestThread extends Thread implements ThreadId {
	private static int ID_GEN = 0;
	private static final int MAX_COUNT = 10000;

	private criticalSection CS;
	private int id;
	public TestThread(criticalSection CS) {
		id = ID_GEN++;
		this.CS = CS;
	}
	
	@Override
	public void run() {
//		if (id%2 == 0){
//			Thread.currentThread().setPriority(1);								//Customize Priority setting here. Uncomment line 21
//		}
		Thread.currentThread().setPriority(ThreadLocalRandom.current().nextInt(1, 5 + 1));
		for(int i=0; i<MAX_COUNT; i++)
			CS.emptyCS();
		System.out.println("Counter value is " + criticalSection.count + ". Thread id is " + id + " and its priority is " + Thread.currentThread().getPriority());
	}
	
	public int getThreadId(){
		return id;
	}
}

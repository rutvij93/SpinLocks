package trylock;

import locks.Lock;

public class trycriticalSection {
	private Lock lock;
	public static int count;
	public trycriticalSection(int count, Lock lock) {
		this.lock = lock;
	}

//	@SuppressWarnings("static-access")
	public void emptyCS() {
		lock.lock();
		count = count + 1;
//		try {
////			Thread.currentThread().sleep(10);
//			count = count + 1;
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		lock.unlock();
	}
}

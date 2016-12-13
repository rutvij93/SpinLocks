package bench;
import locks.*;
public class criticalSection {
	private Lock lock;
	public static int count;
	public criticalSection(int count, Lock lock) {
		this.lock = lock;
	}

	public void emptyCS() {
		lock.lock();
		count = count+1;			// This line was uncommented while checking for mutual exclusion
		lock.unlock();
	}
}
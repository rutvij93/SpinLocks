package locks;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements Lock{
	private final AtomicReference<Qnode> tail = new AtomicReference<Qnode>(new Qnode());;
	private final ThreadLocal<Qnode> mynode;
	private final ThreadLocal<Qnode> mypred;
	public CLHLock() {
		this.mynode = new ThreadLocal<Qnode>() {
			protected Qnode initialValue() {
				return new Qnode();
			}
		};
		this.mypred = new ThreadLocal<Qnode>() {
			protected Qnode initialValue() {
				return null;
			}
		};
	}
	public void lock() {
		final Qnode qnode = this.mynode.get();
		qnode.locked = true;
		Qnode pred = this.tail.getAndSet(qnode);
		this.mypred.set(pred);
		while(pred.locked){}
	}
	public void unlock() {
		final Qnode qnode = this.mynode.get();
		qnode.locked = false;
		this.mynode.set(this.mypred.get());
	}
	private static class Qnode{
		private volatile boolean locked;
	}
}

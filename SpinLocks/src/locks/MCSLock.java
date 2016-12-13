package locks;

import java.util.concurrent.atomic.AtomicReference;

public class MCSLock implements Lock {
	private final AtomicReference<Qnode> tail = new AtomicReference<Qnode>(null);
	private final ThreadLocal<Qnode> mynode;
	public MCSLock() {
		this.mynode = new ThreadLocal<Qnode>() {
			protected Qnode initialValue(){
				return new Qnode();
			}
		};
	}
	
	public void lock() {
		final Qnode qnode = this.mynode.get();
		final Qnode pred = this.tail.getAndSet(qnode);
		if (pred != null){
			qnode.locked = true;
			pred.next = qnode;
			while (qnode.locked) {}
		}
	}

	public void unlock() {
		final Qnode qnode = this.mynode.get();
		if (qnode.next == null){
			if (this.tail.compareAndSet(qnode,null)){
				return;
			}
			while (qnode.next == null){}
		}
		qnode.next.locked = false;
		qnode.next = null;
	}
	class Qnode{
		private volatile boolean locked;
		private volatile Qnode next;
	}
}

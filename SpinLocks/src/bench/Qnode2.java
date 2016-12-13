package bench;


public class Qnode2 implements Comparable<Qnode2>{
	public volatile int priority;
	public Qnode2(int x){
		this.priority = x;
	}
	public int getpriority(){
		return priority;
	}
	public boolean equals(Qnode2 other){
		return this.getpriority() == other.getpriority();
	}
	@Override
	public int compareTo(Qnode2 other) {
		if (other == null){
			return -1;
		}
		if (getpriority() >= other.getpriority()){
			return 1;
		}
		else {
			return -1;
		}
	}
	public String toString(){
		return "Priority of this thread is " + getpriority();
	}
}

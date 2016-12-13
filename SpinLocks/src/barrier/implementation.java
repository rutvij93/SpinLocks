package barrier;
import bench.*;

public class implementation extends Thread implements ThreadId{
	public static int ID_GEN = 0;
	public barriers bar;
	public int id;
	public long elapsed;
	public static int THREAD_COUNT;
	public static int select = 0;

	public implementation(barriers bar) {
		id = ID_GEN++;
		this.bar = bar;
	}
	
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		foo();
//		System.out.println("Thread " + id + " executed foo");							//Uncomment To check correctness
		
		if (select == 2){
			if (id == 0){
				bar.barrier2[id] = 1;
			}
			else{
				while (bar.barrier2[id - 1] == 0);
				bar.barrier2[id] = 1;
			}
			if (bar.barrier2[THREAD_COUNT - 1] == 1 && id == THREAD_COUNT - 1)
				bar.barrier2[THREAD_COUNT - 1] = 2;
			while (bar.barrier2[THREAD_COUNT - 1] != 2);
		}
		else{
			bar.barrier();
			while (barriers.count < THREAD_COUNT);
		}
		bar();
//		System.out.println("Thread " + id + " executed bar");							//Uncomment To check correctness
		
		long end = System.currentTimeMillis();
		elapsed = end - start;
	}
	
	
	public void foo(){
		
	}
	
	public void bar(){
		
	}
	
	public int getThreadId(){
		return id;
	}

	public long getElapsedTime() {
		return elapsed;
	}
}

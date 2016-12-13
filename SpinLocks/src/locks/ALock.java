package locks;

import java.util.concurrent.atomic.AtomicInteger;

public class ALock implements Lock {
    ThreadLocal<Integer> mySlotIndex = new       
    ThreadLocal<Integer> (){
         protected Integer initialValue() {
              return 0;
         }
    };
    AtomicInteger tail;
    boolean[] flag;
    int size;
    public static int capacity;
    public ALock() {
         size = capacity;
         tail = new AtomicInteger(0);
         flag = new boolean[capacity];
         flag[0] = true;
    }
    public void lock() {
         int slot = tail.getAndIncrement() % size;
         mySlotIndex.set(slot);
         while (! flag[slot]) {};
    }
    public void unlock() {
         int slot = mySlotIndex.get();
//         flag[slot] = false;
//         flag[(slot + 1) % size] = true;
         flag[(slot + 1) % size] = true;
//         if ((slot + 1) % size == 1)
//			try {
//				Thread.currentThread().sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
         flag[slot] = false;
    }
}
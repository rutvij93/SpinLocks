PartII
Question1: 
	Execution: 	java Test3 <THREAD_COUNT> <BARRIER>
	Example: 	java Test3 64 1
		 	java Test3 10 2
To check the correctness of the barrier please uncomment the print statements in package-->barrier	implemention.java

Question2:
	Execution: 	java <Test4 or Test> <Lockname> <ThreadCount>
	Locks:		TASLock, TTASLock, CLHLock, MCSLock
	Test.java: 	To check correctness of the lock implementation. Counter value is displayed to prove mutual exclusion.
			Expected counter value = (1000 * Thread Count)
	Test4.java: 	To check performance of the lock implementation. Average time per thread is displayed.

Question3:
	Execution: 	java <Test4 or Test> <Lockname> <ThreadCount>
	priorityCLHLock: 	Just lock and unlock methods implemented.
	Test.java: 	To check correctness of the lock implementation. Counter value is displayed to prove mutual exclusion.
			Expected counter value = (1000 * THREAD_COUNT)
			Priority of the threads also displayed. Threads are assigned random priority between 1 and 5. 
			To set priority for your self please modify the commented section in package-->bench	TestThread.java
	Test4.java: 	To check performance of the lock implementation. Average time per thread is displayed.

--------------------------

	trypriorityCLHLock: 	trylock, lock and unlock methods implemented.
				Set the try value as per your requirement. Current value is "10000 nano Seconds". Also "backoff" value can be changed as required,
				modify mindelay and maxdelay.
	Execution: 	java <Test5> <Lockname> <ThreadCount>
			To check for mutual exclution uncomment the print statement in Test5.java. TOTAL_ITERS = 2520. You should get that output with any thread count
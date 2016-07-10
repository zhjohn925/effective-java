package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo6_yield.java
// java -cp class_path effective_java.ThreadDemo6_yield

class SimpleThread extends Thread {

  //t.start() calls no-argument run() only
	//override run() method in Thread
	public void run() {
		//Job of thread
		for (int i=0; i<10; i++) {
			System.out.println("Child thread no-argument i="+i);
			//pause the current thread, give chance to the thread
			//   with same priority in the waiting list
			Thread.yield();
		}
	}

}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo6_yield {
	//main thread
	public static void main(String[] args) {
 		//main thread declares and start a child thread
 		SimpleThread t = new SimpleThread();  //thread instantialization
		t.start();   //job executed by child thread

		//Job execuated by main thread
		//  the child thread calls Thread.yield(), hence
		//  the main thread is expected to complete first.
		for (int i=0; i<10; i++) {
			System.out.println("Main thread i="+i);
		}
	}
}
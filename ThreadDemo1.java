package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo1.java
// java -cp class_path effective_java.ThreadDemo1

class SimpleThread extends Thread {

  //t.start() calls no-argument run() only
	//override run() method in Thread
	public void run() {
		//Job of thread
		for (int i=0; i<100; i++) {
			System.out.println("Child thread no-argument i="+i);
		}
	}

  //overloaded method
  //called explicitly like normal methods
	public void run(int limit) {
		//Job of thread
		for (int i=0; i<limit; i++) {
			System.out.println("Child thread with-argument i="+i);
		}
	}

}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo1 {
	//main thread
	public static void main(String[] args) {
 		//main thread declares and start a child thread
 		SimpleThread t = new SimpleThread();  //thread instantialization
		t.start();   //job executed by child thread
		//calling t.run() will not create a new thread, the job in run() method
		//  is executed by main thread. effectively only one thread (main) 
		//  runs, including the job below.
		//t.run();
		//Job execuated by main thread
		for (int i=0; i<100; i++) {
			System.out.println("Main thread i="+i);
		}
	}
}
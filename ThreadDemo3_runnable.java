package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo3_runnable.java
// java -cp class_path effective_java.ThreadDemo3_runnable

class SimpleThread implements Runnable {
	public void run() {
		//Job of thread
		for (int i=0; i<100; i++) {
			System.out.println("Child thread no-argument i="+i);
		}
	}
}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo3_runnable {
	//main thread
	public static void main(String[] args) {
 		//main thread declares and start a child thread
 		SimpleThread r = new SimpleThread();  //target runnable
 		Thread t = new Thread(r);
		t.start();   //job executed by child thread

		//Job execuated by main thread
		for (int i=0; i<100; i++) {
			System.out.println("Main thread i="+i);
		}
	}
}
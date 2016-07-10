package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo2.java
// java -cp class_path effective_java.ThreadDemo2

class SimpleThread extends Thread {

  //override start() method
	public void start() {
		super.start();  //this creates a new child thread, invokes run()
		                //  and executed simultaneously with main thread.

		//this is run by main thread
		for (int i=0; i<10; i++) {
			System.out.println("This is run by main thread. i="+i);
	  }
	} 

  //t.start() calls no-argument run() only
	public void run() {
		//Job of thread
		for (int i=0; i<20; i++) {
			System.out.println("Child thread no-argument i="+i);
		}
	}


}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo2 {
	//main thread
	public static void main(String[] args) {
 		//main thread declares and start a child thread
 		SimpleThread t = new SimpleThread();  //thread instantialization
		t.start();   //job executed by child thread

		//Job execuated by main thread
		for (int i=10; i<20; i++) {
			System.out.println("Main thread i="+i);
		}
	}
}
package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo7x_join.java
// java -cp class_path effective_java.ThreadDemo7x_join

class SimpleThread extends Thread {

  static Thread mt;

	public void run() {
		//join() method forces this (child) thread to pause.
		//   won't execute until the mt thread completes
		try {
		  mt.join();
	  } catch (InterruptedException e) {}

		//Job of this (child) thread
		for (int i=0; i<10; i++) {
			System.out.println("Child thread no-argument i="+i);
		}
	}

}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo7x_join {
	//main thread
	public static void main(String[] args) throws InterruptedException {
 		//set mt to main thread
		SimpleThread.mt = Thread.currentThread();

 		//main thread declares and starts a child thread
 		SimpleThread t = new SimpleThread();  //thread instantialization
		t.start();   //job executed by child thread
    
    //Job of the main thread will complete first since
    //  join() is called in the child thread
		for (int i=0; i<10; i++) {
			System.out.println("Main thread i="+i);
			Thread.sleep(1000);
		}
	}
}
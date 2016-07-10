package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo7_join.java
// java -cp class_path effective_java.ThreadDemo7_join

class SimpleThread extends Thread {

  //t.start() calls no-argument run() only
	//override run() method in Thread
	public void run() {
		//Job of thread
		for (int i=0; i<10; i++) {
			System.out.println("Child thread no-argument i="+i);
			try { Thread.sleep(1000); }
			catch (InterruptedException e) {}
		}
	}

}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo7_join {
	//main thread
	public static void main(String[] args) throws InterruptedException {
 		//main thread declares and start a child thread
 		SimpleThread t = new SimpleThread();  //thread instantialization
		t.start();   //job executed by child thread
    //join() method forces main thread to wait for
    //  t thread completion.
		t.join();
		//Job execuated by main thread
		//  the below job by main thread won't execute
		//  until t.run() completes
		for (int i=0; i<10; i++) {
			System.out.println("Main thread i="+i);
		}
	}
}
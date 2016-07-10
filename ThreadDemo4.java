package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo4.java
// java -cp class_path effective_java.ThreadDemo4

//SimpleThread --extends---> Thread --interface--> Runnable
class SimpleThread extends Thread {

  //t.start() calls no-argument run() only
	public void run() {
		//Job of thread
		for (int i=0; i<100; i++) {
			//Thread name "Thread-1" is set by JVM
			System.out.println("Child thread no-argument executed by thread: "+
				   Thread.currentThread().getName()+". i="+i);
		}
	}


}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo4 {
	//main thread
	public static void main(String[] args) {
 		//main thread declares and start a child thread
 		SimpleThread r = new SimpleThread();  

 		//Thread t = new Thread(Runnable r);
 		//  "SimpleThread --interface--> Runnable"
 		//  Hence, the below is ok
 		Thread t = new Thread(r);

		t.start();   //job in SimpleThread executed by child thread

		//Job execuated by main thread
		for (int i=0; i<100; i++) {
			//Thread name "main" is set by JVM
			System.out.println("This executed by thread: "+
				    Thread.currentThread().getName()+". i="+i);
		}
	}
}
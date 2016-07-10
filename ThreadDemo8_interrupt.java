package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo8_interrupt.java
// java -cp class_path effective_java.ThreadDemo8_interrupt

class SimpleThread extends Thread {

	public void run() {
		//Job of child thread won't execute 10 loops if 
		//   got interrupt.
		try {
				for (int i=0; i<10; i++) {
					System.out.println("Child thread no-argument i="+i);
					Thread.sleep(2000); 
				}	
		}	catch (InterruptedException e) {
				System.out.println("Child thread got interrupt.");
		}
	}

}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo8_interrupt {
	//main thread
	public static void main(String[] args) throws InterruptedException {
 		//main thread declares and start a child thread
 		SimpleThread t = new SimpleThread();  //thread instantialization
		t.start();   //job executed by child thread
    //issues interrupt to the child thread
    //  t.run() won't run full number of loop 
		t.interrupt();
		//Job execuated by main thread
		for (int i=0; i<10; i++) {
			System.out.println("Main thread i="+i);
		}
	}
}
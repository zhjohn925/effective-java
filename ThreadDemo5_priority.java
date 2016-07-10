package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo5_priority.java
// java -cp class_path effective_java.ThreadDemo5_priority

//SimpleThread --extends---> Thread --interface--> Runnable
class SimpleThread extends Thread {

}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
public class ThreadDemo5_priority {
	//main thread
	public static void main(String[] args) {

		//output 5 is default main thread priority
		System.out.println("Default main thread priority is "+
			        Thread.currentThread().getPriority());

 		//main thread declares
 		//hence t1's parent thread is main, and inherits main's priority
 		SimpleThread t1 = new SimpleThread();  

 		//output 5 inherited from main
		System.out.println("Child thread t1 priority inherited from parent thread (main) is "+
			        t1.getPriority());

		//Change main thread priority
		Thread.currentThread().setPriority(7);  //must be 1..10

		//output 7 now for main thread priority
		System.out.println("main thread priority is changed to "+
			        Thread.currentThread().getPriority());

 		//output is still 5 since t1 inherits the main thread priority before the change
		System.out.println("Child thread t1 priority is still "+ t1.getPriority() + 
			    ". inherited before the main thread priority is changed");

		//main thread declares
 		//hence t2's parent thread is main, and inherits main's priority
 		SimpleThread t2 = new SimpleThread();

 		//output is 7 since t2 inherits the main thread priority after the change
		System.out.println("Child thread t2 priority is "+ t2.getPriority() + 
			    ". inherited after the main thread priority is changed");
 		  
	}
}
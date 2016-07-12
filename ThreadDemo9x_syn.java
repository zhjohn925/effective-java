package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo9x_syn.java
// java -cp class_path effective_java.ThreadDemo9x_syn

class Display {

	//static method with synchronized has class-level lock, hence
	//  the different threads access the different objects of the same class,
	//  still run sequentially.  
	public static synchronized void wish(String name) {
		for (int i=0; i<10; i++) {
			System.out.print("Good morning, ");
			try { Thread.sleep(2000); } 
			catch (InterruptedException e) {}
			System.out.println(name);
		}
	}
}

class SimpleThread extends Thread {
	Display d;
	String  name;

	SimpleThread(Display d, String name) {
		this.d = d;
		this.name = name;
	}

	public void run() {
		d.wish(name);
	}
}


//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo9x_syn {
	//main thread
	public static void main(String[] args) {
		Display d1 = new Display();
		Display d2 = new Display();

    //The two threads (t1 & t2) access different objects (d1 & d2) of the 
    //   same class, it seems Synchronization is not applied. However,
    //   the wish() method also with static modifier that has class-level
    //   lock. Hence t1 and t2 still run sequentially.
    //In other words, if no static modifier, that is object-level lock, then
    //   t1 and t2 will run simultaneously. 
 		SimpleThread t1 = new SimpleThread(d1, "Eric"); 
 		SimpleThread t2 = new SimpleThread(d2, "Dave"); 
		t1.start();  
		t2.start();
 
	}
}
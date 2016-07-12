package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo9z_blksyn.java
// java -cp class_path effective_java.ThreadDemo9z_blksyn

class Display {

	//static method with synchronized has class-level lock, hence
	//  the different threads access the different objects of the same class,
	//  still run sequentially.  
	public void wish(String name) {
		;;;;;;;;;;;;//a lakh lines of code
		synchronized(this) {
			for (int i=0; i<10; i++) {
				System.out.print("Good morning, ");
				try { Thread.sleep(2000); } 
				catch (InterruptedException e) {}
				System.out.println(name);
			}
	  }
		;;;;;;;;;;;;//a lakh lines of code
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
public class ThreadDemo9z_blksyn {
	//main thread
	public static void main(String[] args) {
		Display d = new Display();
 
 		SimpleThread t1 = new SimpleThread(d, "Eric"); 
 		SimpleThread t2 = new SimpleThread(d, "Dave"); 
		t1.start();  
		t2.start();
 
	}
}
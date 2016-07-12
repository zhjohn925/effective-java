package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo9y_syn.java
// java -cp class_path effective_java.ThreadDemo9y_syn

class Display {

	//static method with synchronized has class-level lock, hence
	//  the different threads access the different objects of the same class,
	//  still run sequentially.  
	public synchronized void displayn() {
		for (int i=0; i<10; i++) {
			System.out.print(i);
			try { Thread.sleep(2000); } 
			catch (InterruptedException e) {}
		}
	}

	public synchronized void displayc() {
		for (int i=65; i<75; i++) {
			System.out.print((char)i);
			try { Thread.sleep(2000); } 
			catch (InterruptedException e) {}
		}
	}

}

class SimpleThreadn extends Thread {
	Display d;
	SimpleThreadn(Display d) {
		this.d = d;
	}
	public void run() {
		d.displayn();
	}
}

class SimpleThreadc extends Thread {
	Display d;
	SimpleThreadc(Display d) {
		this.d = d;
	}
	public void run() {
		d.displayc();
	}
}

//Example implementing two threads.  
//  1. main thread  - main()
//  2. child thread - SimpleThread
//Both jobs run simultaneously
public class ThreadDemo9y_syn {
	//main thread
	public static void main(String[] args) {

		Display d = new Display(); 
		//Both threads access to the same object
		//  once one acquires lock, another enters waiting state.
		//  Hence, t1 and t2 run sequentially.
 		SimpleThreadn t1 = new SimpleThreadn(d); 
 		SimpleThreadc t2 = new SimpleThreadc(d); 
		t1.start();  
		t2.start();
 
	}
}
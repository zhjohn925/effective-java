package effective_java;

//see notes/readme.thread

// javac -d class_path ThreadDemo9_syn.java
// java -cp class_path effective_java.ThreadDemo9_syn

class Display {
	public synchronized void wish(String name) {
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
public class ThreadDemo9_syn {
	//main thread
	public static void main(String[] args) {
		Display d = new Display();

    //If only one thread (for example, t1) is running, then
    //  synchronized modifier is not required. But this example
 		//  has two threads (t1 & t2) access object d, will 
    //  change state of name. Hence synchronized modifier
    //  is required. 
 		SimpleThread t1 = new SimpleThread(d, "Eric"); 
 		SimpleThread t2 = new SimpleThread(d, "Dave"); 
		t1.start();  
		t2.start();
 
	}
}
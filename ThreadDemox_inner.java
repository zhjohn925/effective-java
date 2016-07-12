package effective_java;

// javac -d class_path ThreadDemox_inner.java
// java -cp class_path effective_java.ThreadDemox_inner

//in order to print the final total value(5050), we may try
// 1. Thread.sleep(1000);   //bad: never know how much time
// 2. b.join();             //bad: if there are some other codes after for..loop
//                          //     hence wait for extra time
//the below shows good solution by calling wait() & notify()


class GetTotal extends Thread {
	int total = 0;  //total will be 5050 after for loop
	public void run() {
		synchronized (this) {
			System.out.println("Child thread starts calculation.");
			for (int i=1; i<=100; i++) {
				total = total + i;
			}		
			System.out.println("Child thread wakes up the main thread in waiting state.");
			this.notify();
	  }
		//there might be lakh lines of codes below 
	}
}

public class ThreadDemox_inner {
	public static void main(String[] args) throws InterruptedException {
		GetTotal t = new GetTotal();
    t.start();  //child thread
    //below executed by main thread
    synchronized(t) {     
      System.out.println("the main thread acquires object t lock, executing...");
      System.out.println("the main thread enters the waiting state.");     
      t.wait();  //the main thread enters the waiting state
                 //  until getting notified to wake up 
      //There is a case wait() is called after notify() is called, for example,
      //  insert "Thread.sleep(10000);" after t.start() above.
      //then main thread will stay in waiting state forever.  to work around this,
      //do   t.wait(10000);
      System.out.print("the main thread get the final total value = ");                                  
    	System.out.println(t.total);
    }	
	}
}
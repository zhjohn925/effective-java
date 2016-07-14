package effective_java;

// javac -d class_path ThreadDemod_deadlock.java
// java -cp class_path effective_java.ThreadDemod

// synchronized modifier is only reason to create deadlock situation.
// if remove one or more synchronized, deadlock will go away.

class A {
	public synchronized void d1(B b) {
		System.out.println("class A:d1() is called");
		//sleep helps to hit deadlock in this case
		try { Thread.sleep(3000); } 
		catch (InterruptedException e) {}
    b.last();
	}

	public synchronized void last() {
		System.out.println("class A:last() is called");
	}
}

class B {
	public synchronized void d2(A a) {
		System.out.println("class B:d2() is called");
		//sleep helps to hit deadlock in this case
	  try { Thread.sleep(3000); } 
		catch (InterruptedException e) {}
    a.last();
	}

	public synchronized void last() {
		System.out.println("class B:last() is called");
	}
}


public class ThreadDemod_deadlock extends Thread {
	A a = new A();
	B b = new B();

	public void m1() {
		this.start();
		a.d1(b);   //this line executed by main thread
	}

  //it is called when Thread::start() is invoked
	public void run() {
		b.d2(a); //this line executed by child thread
	}

	public static void main(String[] args) {
		ThreadDemod_deadlock d = new ThreadDemod_deadlock();
		//this line invokes two threads (main and child)
		//  main thread  ----------------> a.d1() --> b.last()
		//                          own lock of a, but wait for lock of b
		//  child thread --> b.start()---> b.d2() --> a.list() 
		//                          own lock of b, but wait for lock of a
		//
		d.m1();
	}
}
package effective_java;

// javac -d class_path GenericDemo1.java
// java -cp class_path effective_java.GenericDemo1

class Gen<T> {  //T is parameter type
	T ob;
	Gen(T ob) {
		this.ob = ob;
	}
	public void show() {
		System.out.println(ob.getClass().getName());
	}
	public T getOb() {
		return ob;
	}
}

public class GenericDemo1 {
	public static void main (String[] args) {
		Gen<String> g1 = new Gen<String>("durga");
		g1.show();
		System.out.println(g1.getOb());  //durga
		Gen<Integer> g2 = new Gen<Integer>(10);
		g2.show();
		System.out.println(g2.getOb()); //10
		Gen<Double> g3 = new Gen<Double>(99.9);
		g3.show();
		System.out.println(g3.getOb());	//99.9	
	}
}
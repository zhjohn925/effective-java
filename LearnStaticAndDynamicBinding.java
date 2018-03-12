//javac -d class_path LearnStaticAndDynamicBinding.java
//java -cp class_path effective_java/LearnStaticAndDynamicBinding

//Output:
//  Student 1 Person 1 Undergrad 2

package effective_java;

class Person {
	public void method1() {
		System.out.print("Person 1 ");
	}
	public void method2() {
		System.out.print("Person 2 ");
	}
}

class Student extends Person {
	public void method1() {
		System.out.print("Student 1 ");
		super.method1();  //Compile time uses static binding to Person.method1()
		method2();    //-> this.method2(). Run time considers "this" 
		              //as dynamic binding to Undergrad.method2()
	}
	public void method2() {
		System.out.print("Student 2 ");
	}
}

class Undergrad extends Student {
	public void method2() {
		System.out.print("Undergrad 2 ");
	}
}

public class LearnStaticAndDynamicBinding {
	public static void main(String args[]) 
	{
		//Undergrad is-a Person
		Person u = new Undergrad();
		u.method1();
	}
}
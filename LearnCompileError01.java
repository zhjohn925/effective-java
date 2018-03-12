class Person {
	private String name;
	public Person(String n) {
		super();
		this.name = n;
	}
	public void setName(String n) {
		this.name = n;
	}
}

class Student extends Person {
	public Student() {
    //the compile will insert "super()" here automatically.
    //however, there is no Person() constructor is defined.
    //this causes compile error 
		this.setName("Student");
	}
}

public class LearnCompileError01 {
	public static void main(String args[]) 
	{
		Student s = new Student();
	}
}
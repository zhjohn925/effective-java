package effective_java;

//-Operator '==' compares reference.
//-Default equals() does not compare contents.
//-Most of the java library classes implement equals() so that
// it compares the contents of objects instead of their references.
//-Override equals() to meet your need. 

//javac -d class_path Item9x_equals.java
//java -cp class_path effective_jave.Item9x_equals

class Avalue {
	int data;
}

class Bvalue {
	int data;
  @Override public boolean equals(Object o) {
      if (o==this) return true;
      if (!(o instanceof Bvalue)) return false;
      Bvalue v = (Bvalue)o;
      return this.data == v.data;
  }
}

public class Item9x_equals {
	public static void main(String[] args) {
		Integer i1 = new Integer(100);
		Integer i2 = new Integer(100);
		//compare reference ==> false
		System.out.println("Integer i1==i2 ? :"+(i1==i2));
		//compare content ==> true
		System.out.println("Integer i1.equals(i2) ? :"+i1.equals(i2));

		//Default equals() does not compare contents ==> false
		Avalue v1 = new Avalue();
		Avalue v2 = new Avalue();
		v1.data = 99;
		v2.data = 99;
		System.out.println("Class Avalue default v1.equals(v2) ? :"+v1.equals(v2));

		//Override equals() to compare contents ==> true
		Bvalue v3 = new Bvalue();
		Bvalue v4 = new Bvalue();
		v3.data = 99;
		v4.data = 99;
		System.out.println("Class Avalue override v3.equals(v4) ? :"+v3.equals(v4));		
	}
}
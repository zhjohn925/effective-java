package effective_java;

// javac -d class_path Item78e_externalizable.java
// java -cp class_path effective_java.Item78e_externalizable

import java.io.*;

//Externalizable is the child interface of Serializable, contains
//  two methods:  writeExternal(),  readExternal()
//

//The main advantage of Externalization over Serialization is based on
//  our requirement we can save either total object or part of the object
//  so that relatively performance will be improved.

//At the time of deserialization, JVM will invoke no argument constructor 
//   to create a separate new object. Hence no argument constructor is 
//   required in the class

public class Item78e_externalizable implements Externalizable {
	String s;
	int i;
	int j;

	//This is required during deserialization
	//  JVM invokes this to create an new object during deserialization
	//  to implement externalizable
	public Item78e_externalizable() {
		System.out.println("no argument constructor is invoked.");
	}

	public Item78e_externalizable(String s, int i, int j) {
		this.s = s; this.i = i; this.j = j;
	}

  //called automatically by JVM @serialization
  //   j is not in serialization
  public void writeExternal(ObjectOutput oo) throws IOException {
  	oo.writeObject(s);
  	oo.writeInt(i);
  }
   
  //called automatically by JVM @deserialization 
  public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
  	s = (String)oi.readObject();
  	i = oi.readInt();
  }

  public static void main(String[] args) throws Exception {
  	Item78e_externalizable o1 = new Item78e_externalizable("Durga", 10, 20);

		System.out.println("Before serialization, s="
			    +o1.s+", i="+o1.i+", j="+o1.j);

    //serialization 
		FileOutputStream fos = new FileOutputStream("extern.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(o1);
    
    //deserialization
    FileInputStream fis = new FileInputStream("extern.ser");
    ObjectInputStream ois = new ObjectInputStream(fis);
    Item78e_externalizable o2 = (Item78e_externalizable)ois.readObject();

		System.out.println("After serialization, s="
			    +o2.s+", i="+o2.i+", j="+o2.j);


  }
}


//  Output:
//  Before serialization, s=Durga, i=10, j=20
//  no argument constructor is invoked.
//  After serialization, s=Durga, i=10, j=0




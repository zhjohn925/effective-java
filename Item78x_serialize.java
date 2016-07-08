package effective_java;

import java.io.*;

// javac -d class_path Item78x_serialize.java
// java -cp class_path effective_java.Item78x_serialize

// https://www.youtube.com/watch?v=bIzfrvdt7qM&list=PLd3UqWTnYXOl0LRB42B9uTIT3NEDhjhF2

//--Serialization
// -The process of writing State of an Object to a File is called serialization.
//  But strictly speaking it is the process of converting an object 
//  from Java supported form to either File supported form or Network
//  supported form.
// -By using FileOutputStream and ObjectOutputStream classes we can
//  achieve serialization.

//--Deserialization
// -The process of reading State of an object from a File is called deserialization.
//  But strictly speaking it is the process of converting an object from
//  either File or Network supported form into Java supported form.
// -By using FileInputStream and ObjectInputStream classes we can achieve
//  deserialization

//--We can serialize any number of objects to the File. The order to 
//  deserialized has to be same as the order to serialized.

class Dog implements Serializable {
	int x1 = 10;
	int x2 = 20;
	static int x3 = 30;
	//transient is a Modifier applicable only for variables.
	//  it sets the avariable to default value
	//  during serialization 
	transient int x4 = 40;
	//static variables are considered not to be part of 
	//  the object state. hence they won't participate in
	//  serialization. 
  //  transient has no impact on static variables 
	transient static int x5 = 50;
  //transient has no impact on final varaibles 
	transient final int x6 = 60;
}

public class Item78x_serialize {
	public static void main(String[] args) throws Exception {
		Dog d1 = new Dog();
		//serialization
		FileOutputStream fos = new FileOutputStream("dog.serialize");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(d1);
		//deserialization
		FileInputStream fis = new FileInputStream("dog.serialize");
		ObjectInputStream ois = new ObjectInputStream(fis);
    Dog d2 = (Dog)ois.readObject();
    System.out.println("x1 (was 10) = "+d2.x1);
    System.out.println("x2 (was 20) = "+d2.x2);
    System.out.println("static x3 (was 30) = "+d2.x3);
    System.out.println("transient x4 (was 40) = "+d2.x4);
    System.out.println("transient static x5 (was 50) = "+d2.x5); 
    System.out.println("transient final x6 (was 60) = "+d2.x6);
	}
}

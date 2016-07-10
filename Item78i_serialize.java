package effective_java;

import java.io.*;

// javac -d class_path Item78i_serialize.java
// java -cp class_path effective_java.Item78i_serialize

//When the Parent class is serialable, 
//   the child class inherits Serializable automatically from its Parent class.

//When the Parent class is non-serialable, and its Child class is serialable, 
//  -at the time of serialization, if any variable inheriting from 
//   non-serializable Parent, then JVM ignores original value and save 
//   default value (declared in Parent) to the file.
//	-at the time of deserialization, if the Parent class is non-serializable then
//   JVM will execute Instance Control Flow in that non-serializable Parent and
//   share its instance variables to the current. The Instance Control Flow will
//   invoke no argument constructor hence requires the non-serializable
//   class contains non-argument constructor. Otherwise, will get runtime 
//   Exception.
//   

//Parent is serializable
class Animal implements Serializable {
	int a = 10;
} 

//Dog class automatically inherits Serializable 
//  from Animal
class Dog extends Animal {
	int d = 20;
}

//Parent is non-serializable
class Person {
	int p = 30;
}

//Child class is serializable while Parent class is not
class Student extends Person implements Serializable {
	int s = 40;
}

public class Item78i_serialize {
	public static void main(String[] args) throws Exception {
		Dog d1 = new Dog();
		Student s1 = new Student();
		s1.p = 333;  //this value is ignored see comments above
		System.out.println("Before serial, Animal a="+d1.a+", Dog d="+d1.d);
    System.out.println("Before serial, Person p="+s1.p+", Student s="+s1.s);

		//serialization
		FileOutputStream fos = new FileOutputStream("animal.ser");
		ObjectOutputStream oos =  new ObjectOutputStream(fos);
		oos.writeObject(d1);
		oos.writeObject(s1);

		//deserialization
		FileInputStream fis = new FileInputStream("animal.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Dog d2 = (Dog)ois.readObject();
		Student s2 = (Student)ois.readObject();
		System.out.println("After serial, Animal a="+d2.a+", Dog d="+d2.d);
		System.out.println("After serial, Person p="+s2.p+", Student s="+s2.s);

		//	Output:
		//	Before serial, Animal a=10, Dog d=20
		//	Before serial, Person p=333, Student s=40
		//	After serial, Animal a=10, Dog d=20
		//	After serial, Person p=30, Student s=40

	}
}
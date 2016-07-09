package effective_java;

import java.io.*;

// javac -d class_path Item78y_serialize.java
// java -cp class_path effective_java.Item78y_serialize

// https://www.youtube.com/watch?v=bIzfrvdt7qM&list=PLd3UqWTnYXOl0LRB42B9uTIT3NEDhjhF2

//--We can serialize any number of objects to the File. The order to 
//  deserialized has to be same as the order to serialized.

class Dog implements Serializable {
	int x = 10;
	@Override
	public String toString() {
		return ("It is a dog, x="+x);
	}
}

class Cat implements Serializable {
	int x = 20;
	@Override
	public String toString() {
		return ("It is a cat, x="+x);
	}
}

class Rat implements Serializable {
	int x = 30;
	@Override
	public String toString() {
		return ("It is a rat, x="+x);
	}
}

public class Item78y_serialize {
	public static void main(String[] args) throws Exception {
		Dog d1 = new Dog();
		Cat c1 = new Cat();
    Rat r1 = new Rat();  
		//serialization
		FileOutputStream fos = new FileOutputStream("animal.serialize");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(d1);
		oos.writeObject(c1);
		oos.writeObject(r1);
		//deserialization
		FileInputStream fis = new FileInputStream("animal.serialize");
		ObjectInputStream ois = new ObjectInputStream(fis);
    //if we know the order of serialization
    //  Dog d2 = (Dog)ois.readObject();
    //  System.out.println(d2);
    //  Cat c2 = (Cat)ois.readObject();
    //  System.out.println(c2);
    //  Rat r2 = (Rat)ois.readObject(); 
    //  System.out.println(r2);
    //Otherwise:
    while (true) {
    	try {
	   		Object o = ois.readObject();
		    if (o != null) {
			    if (o instanceof Dog) {
			    	Dog d2 = (Dog)o;
			    	System.out.println(d2);
			    } else if (o instanceof Cat) {
			    	Cat c2 = (Cat)o;
			    	System.out.println(c2);
			    } else if (o instanceof Rat) {
			    	Rat r2 = (Rat)o;
			    	System.out.println(r2);	
			    } else {
			    	break;
			    }
		    } 	   		
	    } catch (Exception e) {
	    	break;
	    }
    }
  }
}

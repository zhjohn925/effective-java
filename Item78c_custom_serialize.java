package effective_java;

// javac -d class_path Item78c_custom_serialize.java
// java -cp class_path effective_java.Item78c_custom_serialize

import java.io.*;

//The two methods provide customized serialization
//  - writeObject() and readObject
//  to restore the variables with transient modifier
//  during serailziation.   
//If no customize serialization methods are defined,
//  the password will be null due to transient modifier.

//serialVersionUID is required to implement serialization
//  and deserialization in different JVMs. Even Account
//  class is changed by adding some variables, deserialization
//  is still OK. Otherwise, will get exception.

class Account implements Serializable {

	private static final long serailVersionUID = 1L;
	String username = "Durga";
	transient String password = "password";

	//called by JVM automatically @serialization
	private void writeObject(ObjectOutputStream oos) throws Exception {
		oos.defaultWriteObject();
		String ePassword = "encrpt"+password;
		oos.writeObject(ePassword);
	}

	//called by JVM automatically @deserialization
	private void readObject(ObjectInputStream ois) throws Exception {
		ois.defaultReadObject();
		String ePassword = (String)ois.readObject();
		//remove "encrpt" to restore password
		password = ePassword.substring(6);
	}
}

public class Item78c_custom_serialize {
	public static void main(String[] args) throws Exception {
		Account a1 = new Account();
		System.out.println("Before serialization, username="
			    +a1.username+", password="+a1.password);

    //serialization 
		FileOutputStream fos = new FileOutputStream("account.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(a1);
    
    //deserialization
    FileInputStream fis = new FileInputStream("account.ser");
    ObjectInputStream ois = new ObjectInputStream(fis);
    Account a2 = (Account)ois.readObject();

		System.out.println("After serialization, username="
			    +a2.username+", password="+a2.password);


	}
} 


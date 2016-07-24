package effective_java;

// javac -d class_path GenericDemo2.java
// java -cp class_path effective_java.GenericDemo2

import java.util.*;

public class GenericDemo2 {
	
	public static void main(String[] args) {
		System.out.println(max(23, 42, 1));
		System.out.println(max("apples", "tots", "chicken"));
	}
   
  //It requires T must be a class which implements Comparable.
  //Note:  
	//Comparable<T> is interface, but use extends keyword
	//   in generic
	private static <T extends Comparable<T>>  
		T max(T a, T b, T c) {
			T m = a;
			if (b.compareTo(m) > 0) {
				m = b;
			}
			if (c.compareTo(m) > 0) {
				m = c;
			}
			return m;  	
	}
}
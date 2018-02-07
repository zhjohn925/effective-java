//-java.lang, Interface Comparable<T>
//  Method:  int compareTo(T o);
//-java.util,  Interface Comparator<T> 
//  int compare(T o1, T o2);

// javac -d class_path Test_Comparator_Comparable.java 
// java -cp class_path effective_java/Test_Comparator_Comparable
// Output:
// Sorted by Comparable::compareTo() as default: 
// Lacy, age: 2
// Roger, age: 10
// Shaggy, age: 3
// Tammy, age: 1
// Tommy, age: 4
// Sorted by Comparator::compare(): 
// Tammy, age: 1
// Lacy, age: 2
// Shaggy, age: 3
// Tommy, age: 4
// Roger, age: 10


package effective_java;

import java.util.*;

class Dog implements Comparator<Dog>, Comparable<Dog>
{
  private String name;
  private int age;
  
  public Dog() {}
  
  public Dog(String name, int age) {
    this.name = name;  this.age = age;
  }
  
  //Overriding the Comparable::compareTo() method
  public int compareTo(Dog d) {
    return (this.name).compareTo(d.name);
  }
  
  //Overriding the Comparator::compare() method
  public int compare(Dog d1, Dog d2) {
    return d1.age - d2.age;
  }
  
  //Overriding Object::toString()
  public String toString() {
    return this.name + ", age: " + this.age;
  }
}

public class Test_Comparator_Comparable
{
  public static void main(String args[]) 
  {
    List<Dog> list = new ArrayList<Dog>();
    list.add(new Dog("Shaggy", 3));
    list.add(new Dog("Lacy", 2));
    list.add(new Dog("Roger", 10));
    list.add(new Dog("Tommy", 4));
    list.add(new Dog("Tammy", 1)); 
    
    System.out.println("Sorted by Comparable::compareTo() as default: ");
    Collections.sort(list);
    for (Dog d: list) {
      System.out.println(d);
    }
    
    System.out.println("Sorted by Comparator::compare(): ");
    Collections.sort(list, new Dog());
    for (Dog d: list) {
      System.out.println(d);
    }    
    
  }
}


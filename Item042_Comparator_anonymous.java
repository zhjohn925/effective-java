
//javac -d class_path Item042_Comparator_anonymous.java 
//java -cp class_path effective_java/Item042_Comparator_anonymous
// Output:
//  Unsorted
//  111 bbbb london
//  131 aaaa nyc
//  121 cccc jaipur
//  Sorted by rollno
//  111 bbbb london
//  121 cccc jaipur
//  131 aaaa nyc
//  Sorted by name
//  131 aaaa nyc
//  111 bbbb london
//  121 cccc jaipur


package effective_java;

import java.util.*;

class Student 
{
  private int rollno;
  private String name,  address;
  
  public Student (int rollno, String name, String address)
  {
    this.rollno = rollno; 
    this.name = name;  this.address = address;
  }
  
  public int getRollno()     { return rollno;  }
  public String getName()    { return name;    }
  public String getAddress() { return address; }
  
  public String toString()
  {
     return this.rollno + " " + this.name + " " + this.address;
  }
}

class SortByRollno implements Comparator<Student>
{
   public int compare(Student a, Student b)
   {  
      return a.getRollno() - b.getRollno();
   }
}

class SortByName implements Comparator<Student>
{
   public int compare(Student a, Student b)
   {
      return a.getName().compareTo(b.getName());
   }
}

public class Item042_Comparator_anonymous
{
  public static void main(String args[])
  {
    ArrayList<Student> ar = new ArrayList<Student>();
    ar.add(new Student(111, "bbbb", "london"));
    ar.add(new Student(131, "aaaa", "nyc"));
    ar.add(new Student(121, "cccc", "jaipur"));
    
    System.out.println("Unsorted");
    for (int i=0; i<ar.size(); i++) {
      System.out.println(ar.get(i));
    } 
    
    System.out.println("Sorted by rollno");
    //passed in anonymous class SortByRollno object
    Collections.sort(ar, new SortByRollno());
    for (int i=0; i<ar.size(); i++) {
      System.out.println(ar.get(i));
    } 
    
    System.out.println("Sorted by name");
    //passed in anonymous class SortByName object
    Collections.sort(ar, new SortByName());
    for (int i=0; i<ar.size(); i++) {
      System.out.println(ar.get(i));
    }    
  }
}

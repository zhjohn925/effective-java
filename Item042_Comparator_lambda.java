//Item 42: Prefer Lambdas to Anonymous classes:
//This example replaces anonymous class by a lambda

//The sort() second paramater is Comparator instance will be replaced by lambda
//static <T> void Collections::sort(List<T> list, Comparator<? super T> c)

//javac -d class_path Item042_Comparator_lambda.java 
//java -cp class_path effective_java/Item042_Comparator_lambda
// Output:
//  Unsorted:
//  111 bbbb london
//  131 aaaa nyc
//  121 cccc jaipur
//  Sorted by rollno:
//  111 bbbb london
//  121 cccc jaipur
//  131 aaaa nyc
//  Sorted by name:
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

//class SortByRollno implements Comparator<Student>
//{
//   public int compare(Student a, Student b)
//   {  
//      return a.getRollno() - b.getRollno();
//   }
//}

//class SortByName implements Comparator<Student>
//{
//   public int compare(Student a, Student b)
//   {
//      return a.getName().compareTo(b.getName());
//   }
//}

public class Item042_Comparator_lambda
{
  public static void main(String args[])
  {
    ArrayList<Student> ar = new ArrayList<Student>();
    ar.add(new Student(111, "bbbb", "london"));
    ar.add(new Student(131, "aaaa", "nyc"));
    ar.add(new Student(121, "cccc", "jaipur"));
    
    System.out.println("Unsorted: ");
    for (int i=0; i<ar.size(); i++) {
      System.out.println(ar.get(i));
    } 
    
    System.out.println("Sorted by rollno: ");
    //Replace anonymous class SortByRollno instance with a lambda
    //Collections.sort(ar, new SortByRollno());
    Collections.sort(ar, (s1,s2)->Integer.compare(s1.getRollno(), s2.getRollno()));
    for (int i=0; i<ar.size(); i++) {
      System.out.println(ar.get(i));
    }    
    
    System.out.println("Sorted by name: ");
    //Replace anonymous class SortByName instance with a lamda
    //Collections.sort(ar, new SortByName());
    Collections.sort(ar, (s1,s2)->s1.getName().compareTo(s2.getName()));
    for (int i=0; i<ar.size(); i++) {
      System.out.println(ar.get(i));
    }     
    
  }
}

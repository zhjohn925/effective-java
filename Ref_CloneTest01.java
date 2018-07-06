package effective_java;

// javac -d class_path Ref_CloneTest01.java
// java -cp class_path effective_java/Ref_CloneTest01

//1. the Cloneable interface has no methods; its only purpose is to allow the use of 
//   instanceof in a type inquiry:   if (obj instanceof Cloneable) ... 
//   OR indicates it is legal to clone.
//   Such interfaces are called tagging interfaces (or marker interfaces). 
//2. The clone() method is inherited from the Object class, declared as "protected"
//   access modifier. Your code can't simply call anObject.clone(). A subclass can 
//   call clone() method only to clone its own objects. You must redefine clone() 
//   to be public to allow objects to be cloned by any method.
//3. The Object.clone() method can make only a field-by-field copy. If the object 
//   contains references to other objects, then copying the field gives you another 
//   reference to the same other objects, so the original and the cloned objects 
//   still share some information.
//   - if the shared other objects are immutable, then the sharing is safe. 
//   - however, if the shared other objects are mutable, then you must redefine the 
//     clone() method to make a deep copy.  
//4. The Object.clone() returns a new object (not a reference to the original)

import java.util.Date;   //mutable
import java.util.GregorianCalendar;


class Employee2Clone implements Cloneable {
   private String name;
   private double salary;
   private Date hireDay;   //mutable
   
   public Employee2Clone(String name, double salary)
   { 
      this.name = name; 
      this.salary = salary;
      hireDay = new Date();
   }
   
   /////////////////////////////
   //redefine clone() method
   /////////////////////////////
   //It is a good idea to leave the "throws" specifier in place; that gives
   //subclasses the option of throwing a CloneNotSupportedException if they
   //can't support cloning. 
   public Employee2Clone clone() throws CloneNotSupportedException
   {
      //call Object.clone()
      Employee2Clone cloned = (Employee2Clone)super.clone();
      //clone mutable fields
      cloned.hireDay = (Date)hireDay.clone();
      return cloned;
   }
   
   public void setHireDay(int year, int month, int day)
   {
      Date newHireDay = new GregorianCalendar(year, month-1, day).getTime();
      //example of instance field mutation
      hireDay.setTime(newHireDay.getTime());
   }
   
   public void raiseSalary(double byPercent)
   {
      double raise = salary * byPercent / 100;
      salary += raise;
   }
   
   public String toString()
   {
      return "Employee[name="+name+", salary="+salary+", hireDay="+hireDay+"]";
   }
}


public class Ref_CloneTest01
{
   //if placing "throws" specifier, no try...catch is needed 
   //public static void main(String[] args) throws CloneNotSupportedException
   public static void main(String[] args) 
   {
      try {
         Employee2Clone original = new Employee2Clone("John Public", 5000);
         original.setHireDay(2000, 1, 1);
         Employee2Clone copy = original.clone();
         copy.raiseSalary(10);
         copy.setHireDay(2002, 12, 31);
         System.out.println("Original = " + original);
         System.out.println("Copy = "+copy);
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
   }
}
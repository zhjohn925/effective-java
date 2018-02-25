//This is a typical example of a safe use of a generic varargs parameter.
//The method flatten() takes an arbitrary number of lists as arguments
//and returns a single list containing the elements of all of the input
//lists in sequence. 
//Because the method is annotated with @SafeVarargs, it doesn't generate
//any warnings.

//The rule of deciding when to use the @SafeVarargs annotation:
//- Use @SafeVarargs on every method with a varargs parameter of a generic
//  or parameterized type. so that the compiler won't give the warnings
//- You should never write unsafe varargs methods like dangeous() and 
//  toArray() in Item032_generic_and_varargs_dangeous
//- A generic method is safe when
//  1. it does not store anything in the varargs parameter array, and 
//  2. it does not make the array(or a clone) visible to untrusted code. 
//- The @SafeVarargs annotation is legal only on methods that can't be 
//  overridden, because it is impossible to guarantee that every possible
//  overriding method will be safe. 
//- In Java 8, the annotation was legal only on static method and final 
//  instance methods.  
//- In Java 9, it became legal on private instance method as well.

package effective_java;

//$ javac -d class_path Item032_generic_and_varargs_list.java 
//$ java -cp class_path effective_java/Item032_generic_and_varargs_list
//  Output:
//   [1, 2, 3, 4.0]


import java.util.List;
import java.util.ArrayList;

public class Item032_generic_and_varargs_list
{

   @SafeVarargs
   static <T> List<T> flatten(List<? extends T>...lists)
   {
     List<T> result = new ArrayList<>();
     for (List<? extends T> list: lists) {
       result.addAll(list); 
     }
     return result;
     
   } 
   
   public static void main(String args[]) 
   {
     List<Integer> intList = new ArrayList<>();
     List<Double> doubleList = new ArrayList<>();
     
     intList.add(1); intList.add(2); intList.add(3);
     doubleList.add(4.0);
     
     List<Number> result = flatten(intList, doubleList); 
     System.out.println(result);
   }
}
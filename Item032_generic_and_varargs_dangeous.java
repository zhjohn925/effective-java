//This example shows
//- mix array and generic will get problems
//- It is unsafe to give another method access to a generic varargs
//  parameter array.  
//- get ClassCastException
//  Exception in thread "main" java.lang.ClassCastException: 
//  [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;


//- The purpose of varargs is to allow clients to pass a variable
//  number of arguments to a method, but it is a leaky abstraction:
//      when you invoke a varargs method, an array is created to 
//      hold the varargs parameters. That array, which should be 
//      an implementation detail, is visible. 
//  As a consequence, you get confusing compiler warnings when 
//  varargs parameters have generic or parameterized types.
//- Heap pollution occurs when a variable of a parameterized type
//  refers to an object that is not of that type. 
//  It can cause the compiler's automatically generated casts to 
//  fail, violating the fundamental guaranteed of the generic type 
//  system. 
//

package effective_java;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Item032_generic_and_varargs_dangeous
{
   //The generics or parameterized type as varargs causes the 
   //compile warning: "uses unchecked or unsafe operations"
   //ie. List<String>...stringLists  
   //Every method below has the same warning

   //Mixing generics and varargs can violate type safety!
   //@SuppressWarnings("unchecked")
   static void dangeous(List<String>...stringLists) {
     //List.of is supported by Java 9
     //List<Integer> intList = List.of(42);
     Integer intArr[] = {42};
     List<Integer> intList = Arrays.asList(intArr);
     Object[] objects = stringLists;
     objects[0] = intList;    //Heap pollution
     String s = stringLists[0].get(0);   //ClassCastException
   }
   
   //Unsafe - Exposes a reference to its generic parameter array!
   //This method returns its varargs parameter array, it can 
   //propagate heap pollution up the call stack.
   //@SuppressWarnings("unchecked")
   static <T> T[] toArray(T...args) {
     return args;
   }
   
   
   //- This method has problem when it invokes the toArray method,
   //  which has a generic varargs parameter.
   //- When compiling this method, the compiler generates code to 
   //  create a varargs parameter array in which to pass two T instances
   //  to toArray().  This code allocates an array of type Object[], no
   //  matter what types of objects are passed to pickTwo() at the call
   //  site.
   //- The toArray() method simply returns this array to pickTwo(), which 
   //  in turn returns it to its caller, so pickTwo() will always return 
   //  an array of type Object[]
   //@SuppressWarnings("unchecked")
   static <T> T[] pickTwo(T a, T b, T c) {
     switch(ThreadLocalRandom.current().nextInt(3)) {
       case 0: return toArray(a, b);
       case 1: return toArray(a, c);
       case 2: return toArray(b, c); 
     }
     throw new AssertionError();  //Can't get here
   }
   
   
   //Fixed in Java 9 by using the List 
   //static <T> List<T> pickTwo(T a, T b, T c) {
   //    switch(rnd.nextInt(3)) {
   //      case 0: return List.of(a, b);
   //      case 1: return List.of(a, c);
   //      case 2: return List.of(b, c);
   //    }
   //    throw new AssertionError();
   //}
   
   
   public static void main(String args[])
   {
     //This throws a ClassCastException, though it contains no visible cast.
     //The compiler has generated a hidden cast to String[] on the value
     //  returned by pickTwo() so that it can be stored in attributes.
     //The cast fails, because Object[] is not a subtype of String[]. 
     //
     //Exception in thread "main" java.lang.ClassCastException: 
     //[Ljava.lang.Object; cannot be cast to [Ljava.lang.String; 
     
     String[] attributes = pickTwo("Good", "Fast", "Cheap");
     
     //Fixed in Java 9
     //List<String> attributes = pickTwo("Good", "Fast", "Cheap");
     
   } 
  
}
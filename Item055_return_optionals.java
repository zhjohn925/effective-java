//When should you declare a method to return Optional<T> rather than T?
//As a rule, you should declare a method to return Optional<T> if it 
//might not be able to return a result, and clients will have to
//perform special processing if no result is returned.  

//An Optional-returning method is more flexible and easier to use than 
//one that throws an exception, and it is less error-prone than one 
//that returns null. For performance-critical methods, it may be
//better to return a null or throw an exception. 

//The Optional<T> class represents an immutable container that can hold
//either a single non-null T reference or nothing at all (empty).

//-Exceptions should be reserved for exceptional conditions (Item 69),
//  and throwing an exception is expensive because the entire stack trace
//  is captured when an exception is created.

//-If a client neglects to check for a null return and stores a null 
//   return value away in some data structure, a NullPointerException
//   may result at some arbitrary time in the future, at some place 
//   in the code that has nothing to do with the problem.

package effective_java;

import java.util.*;
 
public class Item055_return_optionals
{
   //See GenericDemo_comparable.java
   public static <E extends Comparable<E>> 
     Optional<E> max(Collection<E> c) 
   {      
     if (c.isEmpty()) {
       return Optional.empty();
     }
     
     E result = null;
     for (E e: c) {
       if (result==null || e.compareTo(result)>0) {
         result = Objects.requireNonNull(e);
       }
     }
     
     return Optional.of(result);
   }
   
   public static void main(String args[]) 
   {
   }
} 



package effective_java;

//- For maximum flexibility, use wildcard types on input parameters 
//  that represent producers or consumers.
//- PECS stands for producer-extends, consumer-super. In other words, 
//  if a parameterized type represents a T producer, use <? extends T>; 
//  if it represents a T consumer, use <? super T>.
//- See GenericDemo_comparable.java

//In summary, using wildcard types in your APIs, while tricky, makes
//the APIs far more flexible. If you write a library that will be widely
//used, the proper use of wildcard types should be considered mandatory.
//Remember the basic rule: producer-extends, consumer-super (PECS). 
//Also remember that all comparables and comparators are consumers.
//- See GenericDemo_comparable.java

import java.util.List;
import java.util.Arrays;

//$ javac -d class_path Item031b_use_bounded_wildcards.java 
//$ java -cp class_path effective_java/Item031b_use_bounded_wildcards
//  Output:
//  [1, 4, 3, 2, 5, 6, 7, 8]


public class Item031b_use_bounded_wildcards
{
  //List::set() return the element previously at the specified position
  public static void swap(List<?> list, int i, int j)
  {
    //list.set(j, list.get(i)) gets compile error:
    //error: incompatible types: Object cannot be converted to CAP#1
    // - The problem is that the type of list is List<?>, and you can’t 
    //   put any value except null into a List<?>.
    //
    //list.set(i, list.set(j, list.get(i)));
    //
    //To fix this, use a private helper method to capture the wildcard type
    swapHelper(list, i, j);
  }
  
  private static <E> void swapHelper(List<E> list, int i, int j)
  {
    list.set(i, list.set(j, list.get(i)));
  }

  public static void main(String args[])
  {
    Integer [] intArr = {1, 2, 3, 4, 5, 6, 7, 8};
    List<Integer> list = Arrays.asList(intArr);
    swap(list, 1, 3);
    System.out.println(list); 
  }
}
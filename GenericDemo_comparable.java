package effective_java;

//- For maximum flexibility, use wildcard types on input parameters 
//  that represent producers or consumers.
//- PECS stands for producer-extends, consumer-super. In other words, 
//  if a parameterized type represents a T producer, use <? extends T>; 
//  if it represents a T consumer, use <? super T>.
//- See Item031a_use_bounded_wildcards.java
//      Item031b_use_bounded_wildcards.java


import java.util.*;

public class GenericDemo_comparable 
{
  public static <E extends Comparable<E>> E max(Collection<E> c)
  {
     if (c.isEmpty()) {
       throw new IllegalArgumentException("Empty Collection");
     }
     
     E result = null;
     
     for (E e : c) {
       if (result==null || e.compareTo(result)>0) {
         result = Objects.requireNonNull(e);
       }
     }
     
     return result;
  } 
  
  //implement max() by using wildCards
  // - Comparables are always consumers, so you should generally 
  //   use Comparable<? super T> in preference to Comparable<T>.
  // - the list parameter produces T instances, so we change the type
  //   from List<T> to List<? extends T>.
  // - For example, this helps 
  //   List<ScheduledFuture<?>> scheduledFutures = ... ;
  //   The ScheduledFuturedoes not implement Comparable<ScheduledFuture>. 
  //   Instead, it is a subinterface of Delayed, which extends Comparable<Delayed>. 
  //   In other words, a ScheduledFuture instance isn’t merely comparable to
  //   other ScheduledFuture instances; it is comparable to any Delayed instance.
  
  public static <T extends Comparable<? super T>> T maxByWildCard(List<? extends T> list)
  {
     if (list.isEmpty()) {
       throw new IllegalArgumentException("Empty List");
     }
     
     T result = null;
     
     for (T e : list) {
       if (result==null || e.compareTo(result)>0) {
         result = Objects.requireNonNull(e);
       }
     }
     
     return result;
  }

  public static void main(String args[]) 
  {
    Integer intArr[] = { 1, 2, 3, 4, 6}; 
    List<Integer> intList = Arrays.asList(intArr);
    int m = max(intList);
    System.out.println("max of intArr[] = "+m);
    
    String strArr[] = { "abc", "bcd", "def" };
    List<String> strList = Arrays.asList(strArr);
    String s = max(strList);
    System.out.println("max of strArr[] = " + s);
    
  }

}
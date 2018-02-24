package effective_java;

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
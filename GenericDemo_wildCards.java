package effective_java;

//The wildcard character (?) can be used to represent
//an unknown type using generic code.

//Wildcards can be used with parameters, fileds, local variables,
//and return types. However, it is best practice not to use 
//wildcards in a return type.

//$ javac -d class_path GenericDemo_wildCards.java 
//$ java -cp class_path effective_java/GenericDemo_wildCards
//  Output:
//  The list contains the element: [One, Two, Three]
//  The list contains the element: 3
//  The list does not contain the element: Two
//  The list contains the element: Three

import java.util.List;
import java.util.ArrayList;

public class GenericDemo_wildCards
{
  public static <T> void checkList(List<?> myList, T obj) 
  {
    if (myList.contains(obj)) {
      System.out.println("The list contains the element: "+obj);
    } else {
      System.out.println("The list does not contain the element: "+obj);
    }
  }
  
  //bounded
  public static <T> void checkNumber(List<? extends Number> myList, T obj)
  {
    if (myList.contains(obj)) {
      System.out.println("The list contains the element: "+obj);
    } else {
      System.out.println("The list does not contain the element: "+obj);
    }
  }
  
  public static void main(String args[])
  {
    List<Integer> intList = new ArrayList<>();
    intList.add(1);
    intList.add(2);
    intList.add(3);
  
    List<String> strList = new ArrayList<>();
    strList.add("One");
    strList.add("Two");
    strList.add("Three");
    
    List<Object> objList = new ArrayList<>();
    objList.add("Two");
    objList.add(strList);
    
    checkList(objList, strList);
    checkList(intList, 3);
    checkList(intList, "Two");
    checkList(strList, "Three");
    
    //Compile error: method checkNumber in class GenericDemo_wildCards 
    //cannot be applied to given types
    //checkNumber(objList, 3);
  }
}
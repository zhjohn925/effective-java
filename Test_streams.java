package effective_java;

//1. A java.util.Stream represents a sequence of elements on which 
//   one or more operations can be performed.
//2. intermediate operations return the stream itself so you can 
//   chain multiple method calls in a row.
//3. terminal operations return a result.
//4. Streams are created on a source ie. a java.util.Collection
//   like lists or sets (maps are not supported)
//5. Stream operations can either be executed sequential or parallel.


import java.util.*;

public class Test_streams
{
  public static void main(String args[])
  {
    //create a source in form of a list
    List<String> stringCollection = new ArrayList<>();
    stringCollection.add("ddd2");
    stringCollection.add("aaa2");
    stringCollection.add("bbb1");
    stringCollection.add("aaa1");
    stringCollection.add("bbb3");
    stringCollection.add("ccc");
    stringCollection.add("bbb2");
    stringCollection.add("ddd1");
      
    stringCollection
         .stream()
         .filter((s)->s.startsWith("a"))  //intermediate operation 
         .forEach(System.out::println);   //terminal operation  
  }
}
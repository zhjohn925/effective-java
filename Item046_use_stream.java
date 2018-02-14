//Scanner::tokens() supported in java 9
//To compile and run it:
//$ export JAVA_HOME=`/usr/libexec/java_home -v 9.0.4`
//$ javac -d class_path Item046_use_stream.java 
//$ java -cp class_path effective_java/Item046_use_stream

package effective_java;

import java.util.*;
import java.util.stream.Stream;
import java.io.*;
//import static java.util.Comparator.reverseOrder;
//import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
//import static java.util.stream.Collectors.toList;


public class Item046_use_stream
{
  public static void main(String args[]) throws IOException
  {
    File file = new File("dict.txt");
    Map<String, Long> freq;
    
    try(Stream<String> words = new Scanner(file).tokens()) {
      //freq = words.collect(groupingBy(w->w, counting()));
      //OR:
      freq = words.collect(groupingBy(String::toLowerCase, counting()));
    }
    
    if (freq.size()>0) {
      System.out.println(freq.keySet()); 
      System.out.println(freq.values());
    }
  }

}
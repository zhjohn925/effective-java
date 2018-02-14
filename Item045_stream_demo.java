//Item 45:
//Use streams judiciously
//-The elements in a stream can come from anywhere. Common sources include
// collections, arrays, files, regular expression pattern matchers, 
// pseudorandom number generators, and other streams.
//-The data elements in a stream can be object references or primitive values. 

package effective_java;

//javac -d class_path Item045_stream_demo.java 

//Output:
// $ java -cp class_path effective_java/Item045_stream_demo  dict.txt 2
//   5:[acb, Abc, bca, bAc, cAb]
//   2:[surprise, prisesur]
//   2:[weird, wdeir]
// $ java -cp class_path effective_java/Item045_stream_demo  dict.txt 3
//   5:[acb, Abc, bca, bAc, cAb]




import java.io.*;
import java.util.*;
//import java.util.Arrays;
import java.util.stream.*;
import java.nio.file.*;   

public class Item045_stream_demo
{
  public static void main(String args[]) throws IOException
  {
    Path dictionary = Paths.get(args[0]);
    int minGroupSize = Integer.parseInt(args[1]);
    
    try (Stream<String> words = Files.lines(dictionary)) 
    { 
      //:Collects all the words into a map that groups the words 
      //:by their alphabetized form (alphabetize() returns as key)
      //:     
      //Map<String, List<String>> wordMap = 
      //     words.collect(Collectors.groupingBy(word->alphabetize(word)));
      //:
      //:wordMap.values() returns List<String> 
      //:wordMap.keySet() returns keys
      //:wordMap.values().stream() creates Stream<List<String>>
      //System.out.println(wordMap.values());
      //:
      //:java -cp class_path effective_java/Item045_use_streams  dict.txt 2
      //:Output shows 6 groups:     
      //:[[acb, Abc, bca, bAc, cAb], [here], [surprise, prisesur], \
      //:[what], [hello], [weird, wdeir]]
     
      words.collect(Collectors.groupingBy(word->alphabetize(word)))
           .values().stream()
           .filter(group->group.size() >= minGroupSize)
           .forEach(g->System.out.println(g.size()+":"+g));
    }
    
    "Hello the world".chars().forEach(System.out::println);
  }
  
  
  //this function returns as a key in groupingBy()
  private static String alphabetize(String s) 
  {
    char[] a = s.toLowerCase().toCharArray();
    Arrays.sort(a);
    return new String(a);
  }
}
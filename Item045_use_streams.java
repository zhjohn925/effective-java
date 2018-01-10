//Item 45:
//Use streams judiciously

package effective_java;

//Output:
// $ java -cp class_path effective_java/Item045_use_streams  dict.txt 1
//   2:[abc, abc]
//   1:[here]
//   2:[surprise, surprise]
//   1:[what]
//   1:[hello]
//   2:[weird, weird]
// $ java -cp class_path effective_java/Item045_use_streams  dict.txt 2
//   2:[abc, abc]
//   2:[surprise, surprise]
//   2:[weird, weird]


import java.io.*;
import java.util.Arrays;
import java.util.stream.*;
import java.nio.file.*;

public class Item045_use_streams
{
  public static void main(String args[]) throws IOException
  {
    Path dictionary = Paths.get(args[0]);
    int minGroupSize = Integer.parseInt(args[1]);
    
    try (Stream<String> words = Files.lines(dictionary)) 
    {
      words.collect(Collectors.groupingBy(word->alphabetize(word)))
           .values().stream()
           .filter(group->group.size() >= minGroupSize)
           .forEach(g->System.out.println(g.size()+":"+g));
    }
  }
  
  private static String alphabetize(String s) 
  {
    char[] a = s.toCharArray();
    Arrays.sort(a);
    return new String(a);
  }
}
package effective_java;

//$ javac -d class_path Item047_stream_sublists.java
//$ java -cp class_path effective_java/Item047_stream_sublists

import java.util.List;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.stream.*;

class SubLists {
  public static<E> Stream<List<E>> of (List<E> list) 
  {
    //Stream::flatMap() takes a callback function to
    //each element in a stream 
    return Stream.concat(
              Stream.of(Collections.emptyList()), 
              prefixes(list).flatMap(SubLists::suffixes));
  }
  
  private static <E> Stream <List<E>> prefixes(List<E> list) 
  {
    //IntStream is the int primitive specialization of Stream; 
    //.rangeClosed() includes the last numnber; so 1 <= range <= list.size();
    //.mapToObj() takes a callback function which is a lambda function 
    //equivalent to for...loop
    return IntStream.rangeClosed(1, list.size())
                    .mapToObj(end->list.subList(0, end));
  }
  
  private static <E> Stream <List<E>> suffixes(List<E> list)
  {
    //IntStream is the int primitive specialization of Stream;
    //.range() excludes the last numnber; so 0 <= range < list.size();
    //.mapToObj() takes a callback function which is a lambda function 
    return IntStream.range(0, list.size())
                    .mapToObj(start->list.subList(start, list.size()));
  }

};

public class Item047_stream_sublists
{
  public static void main (String[] args) 
  { 
    List<Integer> list = new ArrayList<>();
    for (int i=0; i<4; i++) {
      list.add(i);
    }
    //Stream s = SubLists.of(list); //get compile error & warning
    Stream<List<Integer>> s = SubLists.of(list);    
    List<List<Integer>> l = s.collect(Collectors.toList());
    System.out.println(l);
  }
}
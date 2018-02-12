package effective_java;

import java.util.stream.*;

//Please note:
//the elements (char) in the stream are stored as integers


public class Item045_stream_chars
{
  public static void main(String args[]) 
  {
    //Output:
    //  721011081081113211610410132119111114108100   
    "Hello the world".chars().forEach(System.out::print);
    System.out.println();
    
    //To workaround,  use cast
    //Output:
    //   Hello the world
    "Hello the world".chars().forEach(c->System.out.print((char)c));
    System.out.println(); 
  }

}



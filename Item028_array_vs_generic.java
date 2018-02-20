
//- arrays and generics have very different type rules:
//  Arrays are covariant and reified; generics are invariant and erased.
//- As a consequence,
//  arrays provide runtime type safety but not compile-time type safety,
//  and vice versa for generics.
//- As a rule, 
//  arrays and generics don't mix well. 
//  If you find yourself mixing them and getting compile-time errors or warnings,
//  your first impulse should be to replace the arrays with lists. ie.
//  use the collection type List<E> in preference to the array type E[].
//- generics are implemented by erasure: 
//  this means that they enforce their type constraints only at compile time,
//  and discard(erase) their element type information at runtime. for example,
//  the runtime type of a List<Integer> is simply List, and the runtime 
//  type of a List<String>[] instance is List[]. 
//- arrays are reified:
//  this means that arrays know and enforce their element type at runtime.


package effective_java;

// $ javac -d class_path Item028_array_vs_generic.java 
// $ java -cp class_path effective_java/Item028_array_vs_generic
//   Output:
//   Choose a random integer: 3


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


//Can have a ClassCastException at runtime
class ChooserBadly {
  private final Object[] choiceArray;
  
  public ChooserBadly(Collection choices) {
    choiceArray = choices.toArray();
  }
  
  //To use this class, you have to cast the choose() method's return
  //  value from Object to the desired type every time you use invoke 
  //  the method, and the cast will fail at runtime if you get the type wrong. 
  public Object choose() {
    Random rnd = ThreadLocalRandom.current();
    return choiceArray[rnd.nextInt(choiceArray.length)];
  }
}


//Making Chooser generic - won't compile
class ChooserByArray<T> {
  //private final T[] choiceArray;
  
  public ChooserByArray(Collection<T> choices) {
    //compile error:
    //error: incompatible types: Object[] cannot be converted to T[]
    //choiceArray = choices.toArray();
  }
}


//Do it with List<T>, perhaps a tad slower, but it's worth it for the peace
//of mind that you won't get a ClassCastException at runtime
class Chooser<T> {
  private final List<T> choiceList;
  
  //Interface Collection<E>
  public Chooser(Collection <T> choices) {
    choiceList = new ArrayList<>(choices);
  }
  
  public T choose() {
    //A random number generator isolated to the current thread
    //    static ThreadLocalRandom 	current()
    //    which returns the current thread's ThreadLocalRandom.
    Random rnd = ThreadLocalRandom.current();
    return choiceList.get(rnd.nextInt(choiceList.size()));
  }
}

public class Item028_array_vs_generic
{
  public static void main(String args[])
  {
    List<Integer> intList = new ArrayList<>();
    for (int i=0; i<9; i++) {
      intList.add(i);
    }
    
    Chooser<Integer> option = new Chooser<Integer>(intList);
    System.out.println("Choose a random integer: " + option.choose());
    
  }
}
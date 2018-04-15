package effective_java;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigInteger;

//////////////////////////////
//Overloaded methods
//////////////////////////////
class CollectionClassifier {
  public static String classify(Set<?> s) {
    return "Set";
  }
  
  public static String classify(List<?> l) {
    return "List";
  }
  
  public static String classify(Collection<?> c) {
    return "Unknown Collection";
  }
}

/////////////////////////////////
//Override methods
/////////////////////////////////
class Wine {
  String name() { return "Wine"; }
}

class SparklingWine extends Wine {
  @Override 
  String name() { return "SparklingWine"; } 
}

class Champagne extends Wine {
  @Override
  String name() { return "Champagne"; }
}



public class Item052_overload_override 
{
  public static void main(String args[]) 
  {
    Collection<?>[] collections = {
      new HashSet<String>(),
      new ArrayList<BigInteger>(),
      //return a collection of the values contained in the map
      new HashMap<String, String>().values() 
    };
    
    //Because the classify() method is overloaded, and the choice
    //of which overloading to invoke is made at compile time.
    //For all three iterations of the loop, the compile-time type
    //of the parameter is the same: Collection<?>. Therefore, 3 of
    //"unknown Collection" are printed:
    
    //Output:
    //  Unknown Collection
    //  Unknown Collection
    //  Unknown Collection
    for (Collection<?> c : collections) {
      System.out.println(CollectionClassifier.classify(c));
    }
    
    //selection among overloaded methods is static, while selection 
    //among overridden methods is dynamic.
    
    //The correct version of an overridden method is chosen at runtime,
    //based on the runtime type of the object on which the method is 
    //invoked. 
    
    //Output:
    // Wine
    // SparklingWine
    // Champagne
    
    
    //Java 9:
    //List<Wine> wineList = List.of(
    //  new Wine(), 
    //  new SparklingWine(),
    //  new Champagne()
    //);
    
    List<Wine> wineList = new ArrayList<>();
    wineList.add(new Wine());
    wineList.add(new SparklingWine());
    wineList.add(new Champagne());
    
    for (Wine c: wineList) {
      System.out.println(c.name());
    }
  }
}
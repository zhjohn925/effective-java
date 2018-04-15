package effective_java;

import java.util.*;

class Plant {
  enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }
  
  final String name;
  final LifeCycle lifeCycle;
  
  Plant(String name, LifeCycle lifeCycle) {
    this.name = name; this.lifeCycle = lifeCycle;
  }
  
  @Override public String toString() {
    return name;
  }
}

public class Item037_use_enummap_not_ordinal_indexing
{
  public static void main(String args[])
  {
    //////////////////////////////////////////////////////////// 
    //Using ordinal() to index into an array - DON'T DO THIS!
    //////////////////////////////////////////////////////////// 
    
    //Because arrays are not compatible with generics (Item 28),
    //the program requires an unchecked cast and will not compile cleanly.
    //This gets compile warning "uses unchecked or unsafe operations".
    //
    //Allocate array with the size of number of enum elements
    Set<Plant>[] plantsByLifeCycle = 
        (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
    
    for (int i=0; i<plantsByLifeCycle.length; i++) {
      plantsByLifeCycle[i] = new HashSet<>();
    }
  
    for (Plant p: garden) {
      plantsByLifeCycle[p.lifeCycle.ordinal()].add(p); 
    }
    
    
  
  }
}
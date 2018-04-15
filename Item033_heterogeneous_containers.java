//Item 33: Consider Typesafe Heterogeneous containers

//- Common uses of generics include collections, such as 
//  Set<E> and Map<K, V>, and single-element containers, such as
//  ThreadLocal<T> and AtomicReference<T>.
//  In all of these uses, it is the container that is parameterized.
//  This limits you to a fixed number of type parameters per container.
//  A Set<> has a single type parameter, representing its element type;
//  a Map<> has two, representing its key and value types.
//- Sometimes, however,
//  you need more flexibility.  For example, a database row can have
//  arbitrarily many columns, and it would be nice to be able to access
//  all of them in a typesafe manner.

package effective_java;

//$ javac -d class_path Item033_heterogeneous_containers.java 
//$ java -cp class_path effective_java/Item033_heterogeneous_containers
//  Output:
//  Java, cafebabe, effective_java.Favorites. 

import java.util.*;

//The Class object for the type will play the part of parameterized key.
//ie. Class<T>
//For example, String.class is of type Class<String>,  and 
//Integer.class if of type Class<Integer>. 
//When a class literal is passed among methods to communicate both
//compile-time and runtime type information, it is called a type token.

class Favorites {
  //The Class object for the type will play the part of the parameterized key. 
  private Map<Class<?>, Object> favorites = new HashMap<>();
  
  public <T> void putFavorite(Class<T> type, T instance) {
    //Objects.requireNonNull Checks that the specified 
    //   object reference is not null.
    //Dynamic cast to instance ensures the right object is added to the Map
    favorites.put(Objects.requireNonNull(type), type.cast(instance));
  }
  
  
  //T cast(Object obj)
  //Casts an object to the class or interface represented 
  //   by this Class object.
  public <T> T getFavorite(Class<T> type) {
    //This is the correct object reference to return, but it has the wrong
    //compile-time type: it is Object. and we need to return a T.
    //So, dynamically casts the object reference to the type represented 
    //by the Class object, using Class's cast() method.
    //It simply checks that its argument is an instance of the type represented
    //by the Class object. if so, it returns the argument; otherwise it throws
    //a ClassCastException. 
    return type.cast(favorites.get(type));
  }
}

public class Item033_heterogeneous_containers
{

  public static void main(String args[])
  {
    Favorites f = new Favorites();
    f.putFavorite(String.class, "Java");
    f.putFavorite(Integer.class, 0xcafebabe);
    f.putFavorite(Class.class, Favorites.class);
    
    String favoriteString = f.getFavorite(String.class);
    int favoriteInteger = f.getFavorite(Integer.class);
    Class<?> favoriteClass = f.getFavorite(Class.class);
    
    
    //Java’s printf method differs from C’s in
    //that you should use %n where you’d use \n in C. 
    //The %n generates the applicable platform-specific 
    //line separator, which is \n on many but not all platforms.
    System.out.printf("%s, %x, %s. %n", favoriteString, favoriteInteger,
        favoriteClass.getName()); 
  }
}


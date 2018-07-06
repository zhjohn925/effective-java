//Item 6: Avoid creating unnecessary objects

//String s = new String("bikini");   // DON'T DO THIS!
//String s = "bikini";    //DO THIS 

//An object can always be reused if it is immutable (Item 17);
//you can also avoid creating unnecessary objects by using 
//static factory methods (Item 1) in preference to constructors 
//on immutable classes that provide both. For example,
//Boolean.valueOf(String) is preferable to the 
//constructor Boolean(String), which was deprecated in Java 9.

package effective_java;

import java.util.regex.Pattern;

//$ javac -d class_path Item006_unnecessary_objects_regExp.java 
//$ java -cp class_path effective_java/Item006_unnecessary_objects_regExp

//Some object creations are much more expensive than others. If
//you're going to need such an "expensive object" repeatedly, it
//may be advisable to cache it for reuse.

public class Item006_unnecessary_objects_regExp
{

  //This implementation has performance issue
  //String.matches() is the easiest way to check if a string matches
  //a regular expression, but it internally creates a Pattern instance
  //for the regular expression and uses it only once, after which it
  //becomes eligible for garbage collection. Creating a Pattern instance
  //is expensive because it requires compiling the regular expression
  //into a finite state machine.
  static boolean isRomanNumeral_bad(String s)
  {
    return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"+"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
  }
  
  //To improve the performance, explicitly compile the regular expression
  //into a Pattern instance (which is immutable) as part of class initialization,
  //cache it, and reuse the same instance for every invocation of the 
  //isRomanNumeral method  
  //Note: the field ROMAN will be initialized needlessly, regardless of 
  //      isRomanNumeral_good() is invoked or not. It would be possible to 
  //      eliminate the initialization by lazily initializing the field (Item 83)
  //      the first time the isRomanNumeral_good() is invoked, but this is not
  //      recommended. 
  private static final Pattern ROMAN = 
       Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})"
                       +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
  
  static boolean isRomanNumeral_good(String s) 
  {
    return ROMAN.matcher(s).matches();
  }
  
  public static void main(String args[])
  {
    String s = "MD"; 
    if (isRomanNumeral_bad(s)) {
      System.out.println(s + " is a roman numeral");
    } else {
      System.out.println(s + " is NOT a roman numeral");
    }
    if (isRomanNumeral_good(s)) {
      System.out.println(s + " is a roman numeral");
    } else {
      System.out.println(s + " is NOT a roman numeral");
    }
  }
}




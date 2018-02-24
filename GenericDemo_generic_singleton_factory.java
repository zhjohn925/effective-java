//Described in Item30 and Item42

//$ javac -d class_path GenericDemo_generic_singleton_factory.java 
//$ java -cp class_path effective_java/GenericDemo_generic_singleton_factory

package effective_java;

//On occasion, you will need to create an object that is immutable but 
//applicable to many different types. 
//Because generics are implemented by erasure (Item 28), you can use 
//a single object for all required type parameterizations, but you need 
//to write a static factory method to repeatedly dole out the object for
//each requested type parameterization. 
//This pattern, called the generic singleton factory, is used for
//function objects (Item 42) such as Collections.reverseOrder, and
//occasionally for collections such as Collections.emptySet.

import java.util.function.UnaryOperator;


//Interface UnaryOperator<T>
//static <T> UnaryOperator<T> 	identity()
//Returns a unary operator that always returns its input argument.

public class GenericDemo_generic_singleton_factory
{
  //Generic singleton factory pattern 
  private static UnaryOperator<Object> IDENTITY_FN = (t)->t;
  
  //The cast of IDENTITY_FN to (UnaryFunction<T>) generates an unchecked cast
  //warning, as UnaryOperator<Object> is not a UnaryOperator<T> for every T.
  //Since this function returns its argument unmodified (itself), we know that
  //it is typesafe. Therefore we an confidently suppress the unchecked cast 
  //warning.
  @SuppressWarnings("unchecked")
  public static <T> UnaryOperator<T> identityFunction() 
  {
    return (UnaryOperator<T>)IDENTITY_FN; 
  }

  public static void main(String args[])
  {
     String[] strings = {"jute", "hemp", "nylon"};
     UnaryOperator<String> sameString = identityFunction();
     for (String s: strings) {
       System.out.println(sameString.apply(s));
     }
     
     Number[] numbers = {1, 2.0, 3L};
     UnaryOperator<Number> sameNumber = identityFunction();
     for (Number n: numbers) {
       System.out.println(sameNumber.apply(n));
     }
  }

}
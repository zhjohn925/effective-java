//-Lambdas are limited to functional interfaces.
//-Lambdas are by far the best way to represent small function objects.
//-The primary advantage of lambdas over anonymous classes is that
// they are more succinct.

//Interface ToIntBiFunction<T,U>
//Method:   int applyAsInt(T t, U u)

// This interface represents a function that takes two objects(String) 
// arguments and returns an int result.
// Using lambda to represent ToIntBiFunction interface function object.

// javac -d class_path Item042_ToIntBiFunction_lamda.java 
// java -cp class_path effective_java/Item042_ToIntBiFunction_lamda 
// Output:
//   intf.applyAsInt(x,y) = 5


package effective_java;

import java.util.function.ToIntBiFunction;


public class Item042_ToIntBiFunction_lamda
{
  public static void main(String args[])
  {
     //Using lambda to represent ToIntBiFunction interface function object.  
     ToIntBiFunction<String, String> intf = (x,y)->Integer.parseInt(x)+Integer.parseInt(y);  
    
     System.out.printf("intf.applyAsInt(x,y) = %d\n", intf.applyAsInt("2", "3"));
  }
}
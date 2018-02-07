//-Lambdas are limited to functional interfaces.
//-Lambdas are by far the best way to represent small function objects.
//-The primary advantage of lambdas over anonymous classes is that
// they are more succinct.

//the lambda-based operation enum:
//   Using the DoubleBinaryOperator interface for the lambdas
//   that represent the enum constant's behavior. This interface represents
//   a function that takes two double arguments and returns a double results

// javac -d class_path Item042_enum_lamda.java 
// java -cp class_path effective_java/Item042_enum_lamda  1   2
//     1.000000 + 2.000000 = 3.000000
//     1.000000 - 2.000000 = -1.000000
//     1.000000 * 2.000000 = 2.000000
//     1.000000 / 2.000000 = 0.500000

package effective_java;

import java.util.function.*;

//The lambda represents the DoubleBinaryOperator interface
enum Operation {

  PLUS("+",   (x,y)->x+y), //constructor defined  
  MINUS("-",  (x,y)->x-y), //below 
  TIMES("*",  (x,y)->x*y),
  DIVIDE("/", (x,y)->x/y);
  
  ////////////////////////////////////////////////////
  //Common constructor & members for each above enum
  ////////////////////////////////////////////////////
  
  private final String symbol;
  private final DoubleBinaryOperator op;
  
  //constructor
  Operation (String symbol, DoubleBinaryOperator op)
  {
    this.symbol = symbol;
    this.op = op;
  }
  
  @Override public String toString() 
  {
    return symbol;
  }
  
  public double apply(double x, double y) 
  {
    return op.applyAsDouble(x, y);
  }
}


public class Item042_enum_lamda
{
  public static void main(String args[])
  {
     double x = Double.parseDouble(args[0]);
     double y = Double.parseDouble(args[1]);
     for (Operation op : Operation.values()) {
       System.out.printf("%f %s %f = %f\n", x, op, y, op.apply(x,y));
     }
  }
}
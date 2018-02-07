//-Lambdas are limited to functional interfaces.
//-Lambdas are by far the best way to represent small function objects.
//-The primary advantage of lambdas over anonymous classes is that
// they are more succinct.

//the lambda-based operation enum:
//   Using the ToIntBiFunction interface for the lambdas
//   that represent the enum constant's behavior. This interface represents
//   a function that takes two double arguments and returns a double results

// javac -d class_path Item042x_enum_lamda.java 
// java -cp class_path effective_java/Item042x_enum_lamda  1   2
//  1 + 2 = 3
//  1 - 2 = -1
//  1 * 2 = 2
//  1 / 2 = 0

package effective_java;

import java.util.function.*;

//The lambda represents the DoubleBinaryOperator interface
enum Operation {

  PLUS("+", (x,y)->x+y), //constructor defined  
  MINUS("-", (x,y)->x-y), //below 
  TIMES("*", (x,y)->x*y),
  DIVIDE("/", (x,y)->x/y);
  
  ////////////////////////////////////////////////////
  //Common constructor & members for each above enum
  ////////////////////////////////////////////////////
  
  private final String symbol;
  private final ToIntBiFunction<Integer, Integer> op;
  
  //constructor
  Operation (String symbol, ToIntBiFunction<Integer, Integer> op)
  {
    this.symbol = symbol;
    this.op = op;
  }
  
  @Override public String toString() 
  {
    return symbol;
  }
  
  public int apply(int x, int y) 
  {
    Integer xObj = x;
    Integer yObj = y; 
    return op.applyAsInt(xObj, yObj);
  }
}


public class Item042x_enum_lamda
{
  public static void main(String args[])
  {
     Integer x = Integer.parseInt(args[0]);
     Integer y = Integer.parseInt(args[1]);
     for (Operation op : Operation.values()) {
       System.out.printf("%d %s %d = %d\n", x, op, y, op.apply(x,y));
     }
  }
}
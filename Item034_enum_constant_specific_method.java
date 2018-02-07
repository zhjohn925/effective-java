//Item 34:
//Use Enums instead of int constants

//Use 'switch' has shortcoming: 
//not easily debug when adding a new enum constant but forget to add
//a corresponding case to the 'switch'
//To fix this:
//declare an abstract apply method in the enum type, and override it 
//with a concrete method for each constant in a constant-specific class body. 
//Such methods are known as constant-specific method implementations

//Output:
//		$ java -cp class_path  effective_java/Item034_enum_constant_specific_method 2 4
//		2.000000 + 4.000000 = 6.000000
//		2.000000 - 4.000000 = -2.000000
//		2.000000 * 4.000000 = 8.000000
//		2.000000 / 4.000000 = 0.500000

package effective_java;

//import java.util.*;
import java.io.*;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Operation 
{
	//default constructor
	//PLUS   { public double apply(double x, double y) { return x+y; } },
	//MINUS  { public double apply(double x, double y) { return x-y; } },
	//TIMES  { public double apply(double x, double y) { return x*y; } },
	//DIVIDE { public double apply(double x, double y) { return x/y; } };
	//OR: constructor with String parameter
	PLUS("+")   { public double apply(double x, double y) { return x+y; } },
	MINUS("-")  { public double apply(double x, double y) { return x-y; } },
	TIMES("*")  { public double apply(double x, double y) { return x*y; } },
	DIVIDE("/") { public double apply(double x, double y) { return x/y; } };

  ///////////////////////////////////////////////////////////////// 
  //Below are common constructor and member variables & functions 
  //for each above enum
  ///////////////////////////////////////////////////////////////// 
  
  private final String symbol;

	//constructor with String parameter
	Operation (String symbol) { this.symbol = symbol; }

  //override to print symbol for each enum 
	@Override public String toString() { return symbol; }
  
  //abstract methods in an enum type must be overridden with 
  //concrete methods in all of its constants.
  public abstract double apply(double x, double y);

  // Implementing a fromString method on an enum type
  private static final Map<String, Operation> stringToEnum =
           Stream.of(values()).collect(Collectors.toMap(Object::toString, e -> e));

  public static Optional<Operation> fromString(String symbol)
  {
  	return Optional.ofNullable(stringToEnum.get(symbol));
  }         

}


public class Item034_enum_constant_specific_method
{
	public static void main(String args[])
	{
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		for (Operation op : Operation.values()) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}

    //Output:  since overriding toString() makes "PLUS" to "+"
    //  valueOf('PLUS') is +
		System.out.println("valueOf('PLUS') is "+Operation.valueOf("PLUS"));

		//Stream<String> s = Stream.of("apple", "banana", "orange");
    //Map<Character, String> m = s.collect(Collectors.toMap(z -> z.charAt(0), z -> z));
    //System.out.println(m);
    //Output:
    //   {a=apple, b=banana, o=orange}

    System.out.println(Operation.fromString("+"));     //Output: Optional[+]
    System.out.println(Operation.fromString("PLUS"));  //Output: Optional.empty
	}
}
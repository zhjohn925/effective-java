package effective_java;

import java.util.Map;
import java.util.HashMap;

//javac -d class_path Item30_operation_enum.java
//java -cp class_path effective_java.Item30_operation_enum 3.0 6.0

//public enum Operation {
enum Operation {
	PLUS("+") {
		double apply(double x, double y) { return x+y; }
	},
	MINUS("-") {
		double apply(double x, double y) { return x-y; }
	},
	TIMES("*") {
		double apply(double x, double y) { return x*y; }
	},
	DIVIDE("/") {
		double apply(double x, double y) { return x/y; }
	};

	private final String symbol;

	//map symbol to operation
	private static final Map<String, Operation> stringToNum
							= new HashMap<String, Operation>();
	static {
		for (Operation op : values()) {
			stringToNum.put(op.toString(), op);
		}
	}

	//Constructor
	Operation(String symbol) {
		this.symbol = symbol;
	}

	//overwrite toString() method to return its own symbol
	@Override public String toString() { return symbol; }

	//implement fromString (inverting to toString)
	public static Operation fromString(String symbol) {
		return stringToNum.get(symbol);
	}

	//abstract function is declared to guanteed each enum object calls
	//   apply() method
	abstract double apply(double x, double y);
}

public class Item30_operation_enum {
	public static void main(String[] args) {
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		double z;

		for (Operation op : Operation.values()) {
			System.out.printf("%f %s %f = %f%n", x,op,y,op.apply(x,y));
		}

		z = Operation.fromString("+").apply(x, y);
		System.out.printf("fromString: %f + %f = %f%n", x, y, z);
	}
}
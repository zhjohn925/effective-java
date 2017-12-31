//Item 10: 
//Obey the general contract when overriding equals
// - Reflexive: for any non-null reference value x,  
//   x.equals(x) must return true.
// - Symmetric: for any non-null reference values x and y,
//   x.equals(y) must return true if and only if y.equals(x) returns true.
// - Transitive: for any non-null reference values x, y and z,
//   if x.equals(y) returns true and y.equals(z) returns true,
//   then x.equals(z) must return true.
// - Consistent: for any non-null reference values x and y, 
//   multiple invocations of x.equals(y) must consistently return true, or
//   consistently return false. 
// - For any non-null reference value x, 
//   x.equals(null) must return false.

//Note:
//There is no way to extend an instantiable class
//and add a new member while preserving the equals contract

//Override equals() in subclasses can cause trouble !
//Can cause infinite recursion !
//!!!Output: Exception in thread "main" java.lang.StackOverflowError

package effective_java;

import java.util.*;

class Point
{
	private final int x;
	private final int y;

	public Point(int x, int y)
	{
		this.x = x; this.y = y;
	}

	@Override public boolean equals (Object o)
	{
		//it seems this is not got called ?
		//System.out.println("equals is called");
		if (!(o instanceof Point)) return false;
		Point p = (Point)o;
		return (p.x==x && p.y==y);
	}
}

class UnitCircle 
{
	//Java 9:
	//private static final Set<Point> unitCircle = 
	//        Set.of(new Point(1, 0), new Point(0, 1), 
	//        	     new Point(-1, 0), new Point(0, -1));

  private static final Set<Point> unitCircle = 
          new HashSet<>(Arrays.asList(new Point(1, 0), new Point(0, 1), 
          	                          new Point(-1, 0), new Point(0, -1)));  

	private static final Set<String> h = new HashSet<>(Arrays.asList("a", "b"));       	                     

	public static boolean onUnitCircle(Point p)
	{
		return unitCircle.contains(p);
	}  

	public static boolean hasString(String s)
	{
		return h.contains(s);
	}
}


public class Item010e_override_equals_contract
{
	public static void main(String args[])
	{
    Point x_pos1 = new Point(1, 0);
    //false -- I donot understand yet
    System.out.println("x_pos1 in unitCircle: "+UnitCircle.onUnitCircle(x_pos1));
    //true
    System.out.println("hasString(a): "+UnitCircle.hasString("a")); 
	}
}
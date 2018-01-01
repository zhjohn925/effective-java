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
//There is no way to extend an instantiable class (ie. not an abstract class)
//and add a new member while preserving the equals contract

//????????
//The Liskov substitution principle says that any important property
//of a type should also hold for all its subtypes so that any method
//written for the type should work equally well on its subtypes

package effective_java;

import java.util.*;

class Point
{
	private final int x;
	private final int y;
	private int hashCode;  //Automatically initialized to 0

	public Point(int x, int y)
	{
		this.x = x; this.y = y;
	}

	@Override public boolean equals (Object o)
	{
		if (!(o instanceof Point)) return false;
		Point p = (Point)o;
		return (p.x==x && p.y==y);
	}

  //hashCode method with lazily initialized cached hash code
	@Override public int hashCode() 
	{
     int result = hashCode;
     if (result==0) {
       result = Integer.hashCode(x);
       result = 31*result + Integer.hashCode(y);
       hashCode = result;
     }
     return result;
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

	public static boolean onUnitCircle(Point p)
	{
		return unitCircle.contains(p);
	}  
}


public class Item010e_override_equals_contract
{
	public static void main(String args[])
	{
    Point x_pos1 = new Point(1, 0);
    //Output:
    //  "x_pos1 in unitCircle: true"
    //Note:
    //  false -- If skip overriding hashCode() 
    System.out.println("x_pos1 in unitCircle: "+UnitCircle.onUnitCircle(x_pos1));
    
	}
}
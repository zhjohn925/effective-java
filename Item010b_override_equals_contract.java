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

//Override equals() in subclasses can cause trouble !
//Can violate symmetry !

package effective_java;

import java.util.*;
import java.awt.Color;

class Point
{
	private final int x;
	private final int y;

  //defined as 'public' allows subclasses 
	public Point(int x, int y)
	{
		this.x = x; this.y = y;
	}

	@Override public boolean equals(Object o)
	{
		if (!(o instanceof Point)) return false;
		Point p = (Point)o;
		return (x==p.x && y==p.y);
	}
}

class ColorPoint extends Point
{
	private final Color color;

	public ColorPoint(int x, int y, Color c)
	{
    super(x, y);
    this.color = c;
	}

	//violates symmetry !
	@Override public boolean equals(Object o)
	{
		if (!(o instanceof ColorPoint))
			return false;
		return super.equals(o) && ((ColorPoint)o).color == color;
	}

}


public class Item010b_override_equals_contract
{
	public static void main(String args[])
	{
		Point p = new Point(1, 2);
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);

    //Output: Violate symmetry !
		//p.equals(cp) is true
    //cp.equals(p) is false
		System.out.println("p.equals(cp) is "+p.equals(cp));
		System.out.println("cp.equals(p) is "+cp.equals(p));
	}
}
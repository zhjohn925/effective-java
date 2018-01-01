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
//Can cause infinite recursion !
//!!!Output: Exception in thread "main" java.lang.StackOverflowError

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

//Create two subclasses of Point ie. ColorPoint and SmellPoint
//can cause infinite recursion

class ColorPoint extends Point
{
	private final Color color;

	public ColorPoint(int x, int y, Color c)
	{
    super(x, y);
    this.color = c;
	}

  //This fixes symmetry violation, but
	//violates transitivity !
	@Override public boolean equals(Object o)
	{
		if (!(o instanceof Point)) return false;

		//if o is a normal Point or SmellPoint, do a color-blind comparison
		//!!! this can cause infinite recursion.
		if (!(o instanceof ColorPoint))
			return o.equals(this);

		//o is a ColorPoint, do a full comparison
		return super.equals(o) && ((ColorPoint)o).color == color;
	}
}

class SmellPoint extends Point
{
	private final boolean smell;

	public SmellPoint(int x, int y, boolean s)
	{
    super(x, y);
    this.smell = s;
	}

  //This fixes symmetry violation, but
	//violates transitivity !
	@Override public boolean equals(Object o)
	{
		if (!(o instanceof Point)) return false;

		//if o can be a normal Point or ColorPoint, do a smell-blind comparison
		//!!! this can cause infinite recursion.
		if (!(o instanceof SmellPoint))
			return o.equals(this);

		//o is a SmellPoint, do a full comparison
		return super.equals(o) && ((SmellPoint)o).smell == smell;
	}
}


public class Item010d_override_equals_contract
{
	public static void main(String args[])
	{
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);
    SmellPoint sp = new SmellPoint(1, 2, true);

    //Output: 
    //  Exception in thread "main" java.lang.StackOverflowError
    boolean b = cp.equals(sp);
	}
}
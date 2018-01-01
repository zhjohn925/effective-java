//Item 11: 
//Override hashCode when you override equals

//If fail to do so, the class will violate the general contract
//for hashCode, which will prevent it from functioning properly
//in collections such as HashMap and HashSet.

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
		if (!(o instanceof Point)) return false;
		Point p = (Point)o;
		return (p.x==x && p.y==y);
	}

  //since this class is immutable, we can use 
  //lazily initialized cached hash code for performance
  //as in Item011b_override_hashcode
	@Override public int hashCode() 
	{
     int result;
     result = Integer.hashCode(x);
     result = 31*result + Integer.hashCode(y);
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


public class Item011a_override_hashcode
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
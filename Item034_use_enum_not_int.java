//Item 34:
//Use Enums instead of int constants

    //Output:
		//  $ java -cp class_path effective_java/Item034_use_enum_not_int 1000
		//  Weight on MERCURY is 377.906697
		//  Weight on VENUS is 905.051007
		//  Weight on EARTH is 1000.000000
		//  Weight on MARS is 379.603997
		//  Weight on JUPITER is 2529.679439
		//  Weight on SATURN is 1065.514113
		//  Weight on URANUS is 904.855483
		//  Weight on NEPTUNE is 1136.263520

package effective_java;

//enum type with data and behaviors
enum Planet 
{
	MERCURY(3.302e+23, 2.439e6),
	VENUS (4.869e+24, 6.052e6),
	EARTH (5.975e+24, 6.378e6),
	MARS (6.419e+23, 3.393e6),
	JUPITER(1.899e+27, 7.149e7),
	SATURN (5.685e+26, 6.027e7),
	URANUS (8.683e+25, 2.556e7),
	NEPTUNE(1.024e+26, 2.477e7);

  //universal gravitational constant (m^3/kg s^2)
  private static final double G = 6.67300e-11;

  //Fields can be public, but it is better to make
  //them private and provide public accessors (Item 16).
	private final double mass;    // kilograms
	private final double radius;  // meters
	private final double surfaceGravity;  // m/s^2

	//constructor
	Planet (double mass, double radius)
	{
		this.mass = mass; this.radius = radius;
		surfaceGravity = G * mass / (radius * radius);
	}

	public double mass()   { return mass;   }
	public double radius() { return radius; }
	public double surfaceGravity() { return surfaceGravity; }

	public double surfaceWeight(double mass)
	{
		return mass * surfaceGravity;  // F = ma
	}
}

public class Item034_use_enum_not_int
{
	public static void main(String args[])
	{
    double earthWeight = Double.parseDouble(args[0]);
    //the mass of a subject in the earch
    double mass = earthWeight / Planet.EARTH.surfaceGravity();

    //calculate this subject's weight in different planets
    //Planet.values() is a static method that returns an array
    //of its values in the order they were declared.
    for (Planet p: Planet.values()) {
    	System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));
    }

    //valueOf(String) method that translates a constant’s name
    //into the constant itself
    //Ouput: 
    //  valueOf('MERCURY') is MERCURY
    System.out.println("valueOf('MERCURY') is "+Planet.valueOf("MERCURY"));

	}
}
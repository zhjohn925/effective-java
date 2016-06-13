package effective_java;

// javac -d class_path Item30_planet_enum.java
// java -cp class_path effective_java.Item30_planet_enum 175


//Enum type with data and behavior
//public enum Planet
enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS  (4.869e+24, 6.052e6),
    EARTH  (5.975e+24, 6.378e6),
    MARS   (6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN (5.685e+26, 6.027e7),
    URANUS (8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);
    
    private final double mass;            // in kilograms 
    private final double radius;          // in meters
    private final double surfaceGravity;   // in m / s^2
    
    //Universal gravitational constant in m^3 / kg s^2
    private static final double G = 6.67300E-11;
    
    //Constructor
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G*mass/(radius * radius);
    }
    
    public double mass()           { return mass; }
    public double radius()         { return radius; }
    public double surfaceGravity() { return surfaceGravity; }

    public double surfaceWeight(double mass) {
        return mass*surfaceGravity;     //F = m*a
    }
}

//public class WeightTable {
public class Item30_planet_enum {
    public static void main(String[] args)
    {
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()) {
            // %s calls toString()
            // %n inserts new line
            System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));
        }
    }
}
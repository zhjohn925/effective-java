package effective_java;

//Donot use a Tagged class
//-vastly inferior to a class hierarchy
//-looks messy, error-prone and inefficient
//-just a pallid imitation of a class hierarchy
//Instead, implement class hierarchy for a tagged class
//-see Item20_hierarchy_class.java

// javac -d class_path Item20_tagged_class.java
// java -cp class_path effective_java.Item20_tagged_class

class Figure {
	enum Shape {RECTANGLE, CIRCLE};

	//Tag field - the shape of this figure
	final Shape shape;

	//These fields are used only if shape is RECTANGLE
	double length;
	double width;
	//This field is used only if shape is CIRCLE
	double radius;
  
  //Constructor for circle
  Figure(double radius) {
  	shape = Shape.CIRCLE;
  	this.radius = radius;
  }
  //Constructor for rectangle
  Figure(double length, double width) {
  	shape = Shape.RECTANGLE;
  	this.length = length;
  	this.width = width;
  }

  double area() {
  	switch(shape) {
  		case RECTANGLE:
  			return length*width;
  		case CIRCLE:
  			return Math.PI*(radius*radius);
  		default:
  			throw new AssertionError();
  	}
  }
}


public class Item20_tagged_class {
	public static void main(String[] args) {
		System.out.println("Donot use a Tagged class!!!");
		System.out.println("-vastly inferior to a class hierarchy");
		System.out.println("-looks messy, error-prone and inefficient");
		System.out.println("-just a pallid imitation of a class hierarchy");
		System.out.println("Instead, implement class hierarchy for a tagged class");
		System.out.println("-see Item20_hierarchy_class.java");
		Figure rectangle = new Figure(6.0, 3.0);
		Figure circle = new Figure(5.0);	
		System.out.println("Rectangle Area: "+rectangle.area());
		System.out.println("Circle Area: "+circle.area());
	}
}

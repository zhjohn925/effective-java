package effective_java;

//implement Class hierarchy replacement for a tagged class
//  (as in Item10_tagged_class.java)

// javac -d class_path Item20_hierarchy_class.java
// java -cp class_path effective_java.Item20_hierarchy_class


abstract class Figure {
	abstract double area();
}

class Circle extends Figure {
	final double radius;
	Circle (double radius) {
		this.radius = radius;
	}
	double area() {
		return Math.PI * (radius*radius);
	}
}

class Rectangle extends Figure {
	final double length;
	final double width;
	Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	double area() {
		return length*width;
	}
}

class Square extends Rectangle {
	Square(double size) {
		super(size, size);
	}
}

public class Item20_hierarchy_class {
	public static void main(String[] args) {
		System.out.println("Donot use a Tagged class!!!");
		System.out.println("Instead, this implements Class hierarchy replacement for a tagged class");
		Figure rectangle = new Rectangle(6.0, 3.0);
		Figure circle = new Circle(5.0);
		Figure square = new Square(6.0);	
		System.out.println("Rectangle Area: "+rectangle.area());
		System.out.println("Circle Area: "+circle.area());
		System.out.println("Square Area: "+square.area());		
	}
}
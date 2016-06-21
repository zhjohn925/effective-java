package effective_java;

// javac -d class_path Item30x_enum.java 
// java -cp class_path effective_java.Item30x_enum

enum Spiciness {
	//define regular enum
	//NOT, MILD, MEDIUM, HOT, FLAMING;
	
	//define enum by calling constructor
	NOT("not spicy at all"),
	MILD("a little hot."),
	MEDIUM("a little hot."),
	HOT("maybe too hot"),
	FLAMING("maybe too hot.");

	private final String description;
	
	Spiciness(String des) {
		this.description = des;
	}

	@Override public String toString() {
		return description;
	}
};

class Burrito {
	Spiciness degree;
	public Burrito(Spiciness degree) {
		this.degree = degree;
	}
	public void describe() {
		System.out.print("This Burrito with ");
		System.out.println("Spiciness degree "+degree.ordinal()+": "+degree);
	}
}

public class Item30x_enum {
	public static void main(String[] args) {
		Burrito 
			plain = new Burrito(Spiciness.NOT),
			greenChile = new Burrito(Spiciness.MEDIUM),
			jalapeno = new Burrito(Spiciness.HOT);

		plain.describe();
		greenChile.describe();
		jalapeno.describe();		
	}
}


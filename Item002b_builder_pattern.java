//Item 2:
//Abstract classes have abstract builders; concrete classes have concrete builders

//Note that Pizza.Builder is a generic type with a recursive type parameter (Item 30).
//This, along with the abstract self() method, allows method chaining to work
//properly in subclasses, without the need for casts. 

package effective_java;

import java.util.*;

//public abstract class Pizza
abstract class Pizza
{
	public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
	
	//EnumSet class implements Set interface
	final Set<Topping> p_toppings;

  //constructor
  Pizza(Builder<?> builder) 
  {
  	p_toppings = builder.toppings.clone();    //see Item 50
  }

  //Defined as static can be invoked by Pizza.Builder() 
  //Generics T is required to implement in Builder class, T
  //represents Builder itself (recursive)
	abstract static class Builder<T extends Builder<T>> 
	{
		//member varaible with initail value
		EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

		public T addTopping(Topping topping) 
		{
			toppings.add(Objects.requireNonNull(topping));
			return self();  //be overrided, return subclass' Builder object
		}

    //this method is called at the end after other Builder methods 
    //to produce a pizza, like Stream terminal function
		abstract Pizza build();

		//Subclasses must override this method to return 'this'. 
	  protected abstract T  self(); 
	}
}


//New-York-Sytle pizza
//public class NyPizza extends Pizza
class NyPizza extends Pizza
{
	public enum Size { SMALL, MEDIUM, LARGE }
	private final Size size;

  //constructor
	private NyPizza(Builder builder)
	{
		super(builder);
		size = builder.size;
	}

  //static class member (Builder)
  //T represents Builder itself (recursive)
	public static class Builder extends Pizza.Builder<Builder> 
	{
		private final Size size;

		public Builder(Size size) 
		{
			this.size = Objects.requireNonNull(size);
		}

		@Override public NyPizza build() 
		{
			//return NyPizza object by calling constructor with 
			//a Builder object parameter
			return new NyPizza(this);
		}

		@Override protected Builder self() { return this; }
	}
}


//Another type of Pizza
//public class Calzone extends Pizza 
class Calzone extends Pizza
{
	private final boolean sauceInside;

  //constructor
  private Calzone(Builder builder)
  {
  	super(builder);
  	sauceInside = builder.sauceInside;
  }

	public static class Builder extends Pizza.Builder<Builder> 
	{
		private boolean sauceInside = false;   //default

		public Builder sauceInside() 
		{
			sauceInside = true;
			return this;
		}

		@Override public Calzone build() 
		{
			//call Calzone constructor
			return new Calzone(this);
		}

		@Override protected Builder self() { return this; }
	}
}

public class Item002b_builder_pattern 
{
  public static void main(String args[])
  {
    NyPizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL).addTopping(NyPizza.Topping.SAUSAGE)
                                              .addTopping(NyPizza.Topping.ONION).build();
    Calzone calzone = new Calzone.Builder().addTopping(Calzone.Topping.HAM).sauceInside().build();                                   
  }	
}

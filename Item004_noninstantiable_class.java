//Item 4:
//Noninstantiability with a private constructor
//Usage: a class is just a grouping of static methods and static fields.
//ie. java.lang.Math

package effective_java;

//public class UtilityClass
class UtilityClass
{
	//Suppress default constructor for noninstantiability
  private UtilityClass() 
  {
  	//The 'private' is inaccessible outside the class.
  	//The 'AssertionError()' isn't strictly required, but it guarantees 
  	//the class will never be instantiated under any 
  	//circumstances. ie. not even inside the class. 
    throw new AssertionError();
  }

  public static final int A_NUMBER = 8;
}


public class Item004_noninstantiable_class
{
	public static void main(String args[])
	{
    System.out.println("UtilityClass.A_NUMBER = "+UtilityClass.A_NUMBER); 
	}
}
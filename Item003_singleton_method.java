//Item 3:
//Enforce the singleton property with a private
//constructor or an Enum type

package effective_java;

//public class Elvis
class Elvis 
{
	//private final, therefore can only be invoked via
	//a public method 
	private static final Elvis INSTANCE = new Elvis();

  private Elvis() {}

  //the public method to call singleton 
  public static Elvis getInstance()
  {
  	return INSTANCE;
  }

	public void leaveTheBuilding()
	{
		System.out.println("Wow baby, I am outta here!");
	}
}


public class Item003_singleton_method
{
	public static void main(String args[])
	{
	  Elvis elvis = Elvis.getInstance();
	  elvis.leaveTheBuilding();
	}
}
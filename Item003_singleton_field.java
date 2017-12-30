//Item 3:
//Enforce the singleton property with a private
//constructor or an Enum type

//singleton with public final field.  This approach is preferable. 

package effective_java;

//public class Elvis
class Elvis
{
	public static final Elvis INSTANCE = new Elvis();

  private Elvis() {}

	public void leaveTheBuilding() 
	{
		System.out.println("Whoa baby, I am outta here!");
	}
}


public class Item003_singleton_field
{
	public static void main(String args[])
	{
    Elvis elvis = Elvis.INSTANCE;  
    elvis.leaveTheBuilding();
  }
}

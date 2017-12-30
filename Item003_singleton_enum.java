//Item 3:
//Enforce the singleton property with a private
//constructor or an Enum type

//a single-element enum type is often the best way to 
//implement a singleton.

package effective_java;

//public enum Elvis
enum Elvis
{
	INSTANCE;   //can be any name ie. x

	public void leaveTheBuilding() 
	{
		System.out.println("Wow baby, I am outta here!");
	}
}


public class Item003_singleton_enum
{
	public static void main(String args[])
	{
    Elvis elvis = Elvis.INSTANCE;
    elvis.leaveTheBuilding();
  }
}

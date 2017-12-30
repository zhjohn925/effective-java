package effective_java;

//public class Elvis
class Elvis
{
	public static final Elvis INSTANCE = new Elvis();
	private Elvis() {}

	private Object readResolve() 
	{ //Return the one true Elvis and let the garbage collector
		//take care of the Elvis impersonator
    return INSTANCE;
	}

	public void leaveTheBuilding()
	{
		System.out.println("Wow baby, I am outta here !");
	}
} 

public class Item003_singleton_serializable
{
	public static void main(String[] args)
	{
		Elvis elvis = Elvis.INSTANCE;
		elvis.leaveTheBuilding();
	}
}
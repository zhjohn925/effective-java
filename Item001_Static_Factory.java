//Item 1:
//The tradictional way to obtain an instance by providing a public constructor.
//Another technique is to provide a public static factory method.

public static Boolean valueOf(boolean b)
{
	return b ? Boolean.TRUE : Boolean.FALSE;
}

//The static factory method is not required to create a new object
//each time they're invoked.  It can greatly improve performance if 
//equivalent objects are requested often, especially if they are
//expensive to create.

//This allows immutable classes (Item 17) to use preconstructed instances, or 
//to cache instances as they're constructed, and dispense them repeatedly to 
//avoid creating unnecessary duplicate objects.

//The ability of static factory methods to return the same object from repeated
//invocations allows classes to maintain strict control over what instances
//exist at any time.  Classes that do this are said to be instance-controlled 
//classes. 

//Instance control allows a class to guarantee that it is a singleton (Item 3) 
//or noninstantiable (Item 4).

//The static factory methods can return an object of any subtype of 
//their return type.  Usage:
//interface-based frameworks (Item 20)   ???
//noninstantiable companion class (Item 4)   ???

//The class of the returned object can vary from call to call as a function
//of the input parameters. (Item 36)
//The class of the returned object need not exist when the class containing 
//the method is written. (Item 65)




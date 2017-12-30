//Item 6:
//Avoid creating unnecessary objects.

//autoboxing can create unnecessary objects 
//Note: prefer primitives to boxed primitives, and 
//watch out for unintentional autoboxing.

//Output:
//Sum with autoboxing duration is 7705ms
//Sum with primitive duration is 1415ms


package effective_java;

class Sum {
	public static long sum_autoboxing() 
	{
		Long sum = 0L;  //Autoboxed, Note the capital 'L' in Long
		for (long i=0; i<=Integer.MAX_VALUE; i++) {
			//sum is declared as autoboxing Long, which means that
			//the program constructs about 2^31 unnecessary Long instances. 
			sum += i;
		}
		return sum;
	}

	//Changing the declaration of sum from Long to long reduces the 
	//runtime from 7.705 seconds to 1.415 seconds on my machine.
	public static long sum_primitive()
	{
		long sum = 0L;  //Primitive type
		for (long i=0; i<=Integer.MAX_VALUE; i++) {
			sum += i;
		}
		return sum;
	}
}

public class Item006_unnecessary_objects_autoboxing
{
	public static void main(String args[])
	{
		long start, duration;
		start = System.currentTimeMillis();
    Sum.sum_autoboxing();
    duration = System.currentTimeMillis() - start;
    System.out.println("Sum with autoboxing duration is "+duration+"ms");
  
    start = System.currentTimeMillis();
    Sum.sum_primitive();
    duration = System.currentTimeMillis() - start;
    System.out.println("Sum with primitive duration is "+duration+"ms");
	}
}
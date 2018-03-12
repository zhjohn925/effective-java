package effective_java;

class ArrayLocation 
{
	double [] coords;
	public ArrayLocation(double [] coords)
	{
		this.coords = coords;
	}
}


//Memory model:
//both object and array are stored (reference) in the Heap
//
// Main's scope                        The Heap
//  coords[]  ----------------------->[5.0,  0.0]
//                (reference)              ^
//                                         | (reference)
//                                         | 
//                 new (constructor)  +----|-----+ 
//  accra (object)------------------->| coords[] |
//                                    +----------+
// 


public class Test_Memory_model_array
{
	public static void main(String args[])
	{
    double[] coords = {5.0, 0.0};
    ArrayLocation accra = new ArrayLocation(coords);
    //Please NOTE: this is not good that
    //  the outside assignment can change objects' member variables!
    coords[0] = 32.9;
    coords[1] = -117.2;
    //output:  32.9
    System.out.println(accra.coords[0]);
	}
}
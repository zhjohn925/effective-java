//Item 17:
//Immutable complex number class

//To make a class immutable, follow these five rules:
//  1. Don't provide methods that modify the object's state (known ad mutators).
//  2. Ensure that the class can't be extended.
//  3. Make all fields final.
//  4. Make all fields private.
//  5. Ensure exclusive access to any mutable components.

package effective_java;

//final class can't be extended (rule 2)
final class Complex 
{
	//rule 3, all fields are final
	//rule 4, all fields are private
	private final double r;
	private final double i;

  public static final Complex ONE = new Complex(1, 0);
  public static final Complex ZERO = new Complex(0, 0);
  public static final Complex I = new Complex(0, 1);

	public Complex(double r, double i)
	{
		this.r = r;  this.i = i;
	}

	public double realPart()      { return r; }
	public double imaginaryPart() { return i; }

	public Complex plus(Complex c)
	{
		return new Complex(r+c.r, i+c.i);
	}

	public Complex minus(Complex c)
	{
		return new Complex(r-c.r, i-c.i);
	}

	public Complex times(Complex c)
	{
		return new Complex(r*c.r-i*c.i, r*c.i+i*c.r);
	}

	public Complex dividedBy(Complex c)
	{
		double tmp = c.r*c.r + c.i*c.i;
		return new Complex((r*c.r+i*c.i)/tmp,  (i*c.r-r*c.i)/tmp);
	}

	@Override public boolean equals(Object o)
	{
		if (o==this) return true;
		if (!(o instanceof Complex)) return false;

		Complex c = (Complex) o;
		return Double.compare(c.r, r)==0 &&
		       Double.compare(c.i, i)==0;
	}

	@Override public int hashCode()
	{
		return 31*Double.hashCode(r) + Double.hashCode(i);
	}

	@Override public String toString()
	{
		return "("+r+" + "+i+"i)";
	}

}


public class Item017_immutable_complex
{
	public static void main(String args[])
	{
     System.out.println("ONE is "+Complex.ONE.toString());
     System.out.println("I is "+Complex.I.toString());
     System.out.println("'ONE + I' is "+Complex.ONE.plus(Complex.I).toString());
	}
}
package effective_java;

//minimize mutability
//javac -d class_path Item15_complex.java
//java -cp class_path effective_java.Item15_complex

final class Complex {

	private final double re;
	private final double im;

	public Complex (double re, double im) {
		this.re = re;
		this.im = im;
	}

	//Minimize mutability - Accessors with no corresponding mutators
	public double realPart()      { return re; }
	public double imaginaryPart() { return im; }

	public Complex add(Complex c) {
		return new Complex(re+c.re, im+c.im);
	}

	public Complex subtract(Complex c) {
		return new Complex(re-c.re, im-c.im);
	}

	public Complex multiply(Complex c) {
		return new Complex(re*c.re-im*c.im, 
											 re*c.im+im*c.re);
	}

	public Complex divide(Complex c) {
		double tmp = c.re*c.re+c.im*c.im;
		return new Complex((re*c.re+im*c.im)/tmp, 
											 (im*c.re-re*c.im)/tmp);
	}

	@Override public boolean equals(Object o) {
		if (o==this) return true;
		if (!(o instanceof Complex))  return false;
		Complex c = (Complex)o;

		//Use compare() instead of ==. why ?
		return Double.compare(re, c.re)==0 &&
		       Double.compare(im, c.im)==0; 
	}

	@Override public int hashCode() {
		int result = 17 + hashDouble(re);
		result = 31 * result + hashDouble(im);
		return result;
	}

	private int hashDouble(double val) {
		long longBits = Double.doubleToLongBits(re);
		return (int) (longBits ^ (longBits >>> 32));
	}

	@Override public String toString() {
		return "("+re+" + "+im+"i)";
	}
}

public class Item15_complex {
	public static void main(String[] args) {
		Complex c33 = new Complex(3, 3);
		Complex c56 = new Complex(5, 6);
		Complex c89 = new Complex(8, 9);
		if (c89.equals(c33.add(c56))) {
			System.out.println("c89 = c33 + c56 is expected.");
		} else {
			System.out.println("c89 != c33 + c56 something goes wrong.");
		}
	}
}
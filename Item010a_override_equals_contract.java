//Item 10: 
//Obey the general contract when overriding equals
// - Reflexive: for any non-null reference value x,  
//   x.equals(x) must return true.
// - Symmetric: for any non-null reference values x and y,
//   x.equals(y) must return true if and only if y.equals(x) returns true.
// - Transitive: for any non-null reference values x, y and z,
//   if x.equals(y) returns true and y.equals(z) returns true,
//   then x.equals(z) must return true.
// - Consistent: for any non-null reference values x and y, 
//   multiple invocations of x.equals(y) must consistently return true, or
//   consistently return false. 
// - For any non-null reference value x, 
//   x.equals(null) must return false.

//Note:
//There is no way to extend an instantiable class (ie. not an abstract class)
//and add a new member while preserving the equals contract

package effective_java;

import java.util.*;

final class CaseInsensitiveStringViolateSymmetry 
{
	private final String s;

	public CaseInsensitiveStringViolateSymmetry(String s) 
	{
		this.s = Objects.requireNonNull(s);
	}

	//violates symmetry !
	@Override public boolean equals(Object o)
	{
		if (o instanceof CaseInsensitiveStringViolateSymmetry) 
			return s.equalsIgnoreCase(((CaseInsensitiveStringViolateSymmetry)o).s);
		if (o instanceof String) {
			//one-way interoperability ! since s.equals() is oblivious to 
			//the case-insensitive strings.
			return s.equalsIgnoreCase((String)o);
		}
		return false;
	}
}

final class CaseInsensitiveString 
{
	private final String s;

	public CaseInsensitiveString(String s) 
	{
		this.s = Objects.requireNonNull(s);
	}

	//Changed to compare the same type of instances only
	@Override public boolean equals(Object o)
	{
		return (o instanceof CaseInsensitiveString) &&
			     ((CaseInsensitiveString)o).s.equalsIgnoreCase(s);
	}
}


public class Item010a_override_equals_contract
{
	public static void main(String args[])
	{
    CaseInsensitiveStringViolateSymmetry cis_v = new CaseInsensitiveStringViolateSymmetry("Coffee");
		String s_v = "coffee";
    //Output: Obey symmetric
    //  s_v.equals(cis_v) is false; cis_v.equals(s_v) is false
		System.out.println("s_v.equals(cis_v) is "+s_v.equals(cis_v)+"; cis_v.equals(s_v) is "+cis_v.equals(s_v));


		CaseInsensitiveString cis = new CaseInsensitiveString("Coffee");
		String s = "coffee";
    //Output: Obey symmetric
    //  s.equals(cis) is false; cis.equals(s) is false
		System.out.println("s.equals(cis) is "+s.equals(cis)+"; cis.equals(s) is "+cis.equals(s));

		List<CaseInsensitiveString> list = new ArrayList<>();
		list.add(cis);
		System.out.println("list.contains(s) is "+list.contains(s));       //return false
		System.out.println("list.contains(cis) is "+list.contains(cis));   //return true 
	}
}
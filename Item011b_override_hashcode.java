//Item 11: 
//Override hashCode when you override equals

//If fail to do so, the class will violate the general contract
//for hashCode, which will prevent it from functioning properly
//in collections such as HashMap and HashSet.

//If a class is immutable and the cost of computing the hash code is
//significant, you might consider caching the hash code in the object
//rather than recalculating it each time it is requested. ie.
//choose to lazily initialize the hash code the first time hash-Code is invoked

package effective_java;

import java.util.*;

final class PhoneNumber 
{
	private final short areaCode;
	private final short prefix;
	private final short lineNum;
	// Automatically initialized to 0
	private int hashCode;  //used to cache hashCode

	public PhoneNumber(int areaCode, int prefix, int lineNum)
	{
		this.areaCode = rangeCheck(areaCode, 999, "area code");
		this.prefix = rangeCheck(prefix, 999, "prefix");
		this.lineNum = rangeCheck(lineNum, 9999, "line num");
	}

	private static Short rangeCheck(int val, int max, String arg)
	{
		if (val < 0 || val > max) 
			throw new IllegalArgumentException(arg + ":" + val);
		return (short)val;
	}

	@Override public boolean equals (Object o)
	{
		if (o == this)  return true;
		if (! (o instanceof PhoneNumber))
			return false;
		PhoneNumber pn = (PhoneNumber)o;
		return pn.areaCode==areaCode && 
		       pn.prefix==prefix &&
		       pn.lineNum==lineNum;
	}

  //hashCode method with lazily initialized cached hash code
	@Override public int hashCode() 
	{
    int result = hashCode;
    if (result==0) {
    	result = Short.hashCode(areaCode);
    	result = 31*result + Short.hashCode(prefix);
    	result = 31*result + Short.hashCode(lineNum);
    	hashCode = result;
    }
    return result;
	}
}


public class Item011b_override_hashcode
{
	public static void main(String args[])
	{
		//use PhoneNumber as a key
		Map<PhoneNumber, String> m = new HashMap<>();
		m.put(new PhoneNumber(707, 867, 5309), "Jenny");
    //Output:
    //  m.get(new PhoneNumber(707, 867, 5309)) is Jenny
    //Note if skip overriding hashCode(), the Output:
    //  m.get(new PhoneNumber(707, 867, 5309)) is null     
		System.out.println("m.get(new PhoneNumber(707, 867, 5309)) is " + m.get(new PhoneNumber(707, 867, 5309)));
  }
}
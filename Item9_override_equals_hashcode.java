package effective_java;

import java.util.Map;
import java.util.HashMap;

// javac -d class_path Item9_override_equals_hashcode.java
// java -cp class_path effective_java.Item9_override_equals_hashcode

//public final class PhoneNumber {
final class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    
    //Constructor
    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode,   999,  "area code");
        rangeCheck(prefix,     999,  "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode   = (short)areaCode;
        this.prefix     = (short)prefix;
        this.lineNumber = (short)lineNumber;
    }
    
    private static void rangeCheck(int arg, int max, String name) {
        if (arg<0 || arg>max) {
            throw new IllegalArgumentException(name+": "+arg);
        }
    }
    
    //override equals() method requires also override hashCode()
    @Override public boolean equals(Object o) {
        if (o==this) return true;
        if (!(o instanceof PhoneNumber)) return false;
        PhoneNumber pn = (PhoneNumber)o;
        return pn.lineNumber == lineNumber &&
               pn.prefix == prefix         &&
               pn.areaCode == areaCode;
    }
    
    //Lazily initialized, cached hashCode
    //??? reduce the cost of computing the hash code ???
    private volatile int hashCode;   //see Item 71
    
    //this is must when equals() is override
    //31*result = (result<<5)-result, 31 is prime number
    @Override public int hashCode() {
        int result = hashCode;
        if (result==0) {
          result = 17;
          result = (result<<5) - result + areaCode;
          result = (result<<5) - result + prefix;
          result = (result<<5) - result + lineNumber;    
          hashCode = result;
        }   
        return result;
    }
    
    //regular way:
    //this is must when equals() is override
    //31*result = (result<<5)-result, 31 is prime number
    //@Override public int hashCode() {
    //    int result = 17;
    //    result = (result<<5) - result + areaCode;
    //    result = (result<<5) - result + prefix;
    //    result = (result<<5) - result + lineNumber;
    //    return result;
    //}
}

public class Item9_override_equals_hashcode {
    public static void main(String[] args) {
        PhoneNumber pn1 = new PhoneNumber(408, 707, 5309); //both have the
        PhoneNumber pn2 = new PhoneNumber(408, 707, 5309); //same phone number 
        if (pn1.equals(pn2)) {
            //this will print no matter of hashCode is override or not
            System.out.println("Both phone numbers are the same");
        }
        Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
        m.put(pn1, "Jenny");
        //print "Jenny"
        System.out.println("The owner of pn1:" + m.get(pn1));
        //print "Jenny" if hashCode() is override. Otherwise
        //   print "null" unexpectedly
        System.out.println("The owner of pn2:" + m.get(pn2));
    }    
}
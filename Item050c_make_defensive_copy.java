//You must program defensively, with the assumption that clients
//of your class will do their best to destroy its invariants.

package effective_java;

//The Period class may appear to be immutable and to enforce
//the invariant that the start of a period does not follow its
//end.  It is, however, easy to violate this invariant by 
//exploiting the fact that Date is mutable.

//Date is obsolete and should no longer be used in new code.
//Actually compile warning also means it. 

//Prior to Java 8, to protect the internals of a Period instance
//from the malicious attack, it is essential to make a defensive
//copy of each mutable parameter to the constructor and to use
//the copies in getter methods.

//Note, do not use the clone method to make a defensive copy of 
//a parameter whose type is sub-classable by untrusted parties. 
//Because Date is non-final, the clone method is not guaranteed 
//to return an object whose class is java.util.Date: it could 
//return an instance of an untrusted subclass that is specially
//designed for malicious mischief.  

import java.util.*;

//Makes defensive copies of parameters
final class Period 
{
  private final Date start;
  private final Date end;
  
  /**
   * @param start the beginning of the period
   * @param end the end of period; must not precede start
   * @throws IllegalArgumentException if start is after end
   * @throws NullPointerException if start or end is null
   */
  public Period(Date start, Date end) {
  
    //Note that defensive copies are made before checking
    //the validity of the parameters, and the validity check 
    //is performed on the copies rather than on the 
    //originals.
    this.start = new Date(start.getTime()); 
    this.end = new Date(end.getTime());
    
    //The validity check
    if (this.start.compareTo(this.end) > 0) {
      throw new IllegalArgumentException(start + " after " + end);
    }
  }
  
  //return copies. otherwise, the invariant can be broken by
  //   p.end().setYear(78); 
  public Date start() { return new Date(start.getTime()); }
  public Date end()   { return new Date(end.getTime());   }
} 

public class Item050c_make_defensive_copy
{
  public static void main(String args[]) 
  {
    Date start = new Date();
    Date end = new Date();
    Period p = new Period(start, end);
    
    //p.end was Mon Mar 05 13:55:11 PST 2018
    System.out.println("p.end was "+p.end());
    
    //Compile warning:
    //uses or overrides a deprecated API
    end.setYear(78);
    p.end().setYear(78);
    
    //Now p.end is Mon Mar 05 13:55:11 PST 2018
    System.out.println("Now p.end is "+p.end());
  }
}
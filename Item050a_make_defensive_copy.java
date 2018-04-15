//You must program defensively, with the assumption that clients
//of your class will do their best to destroy its invariants.

package effective_java;

//The Period class may appear to be immutable and to enforce
//the invariant that the start of a period does not follow its
//end.  It is, however, easy to violate this invariant by 
//exploiting the fact that Date is mutable.

//Date is obsolete and should no longer be used in new code.
//Actually compile warning also means it. 

//As of Java 8, the obvious way to fix this problem is to use
//Instant (or Local-DateTime or ZonedDateTime) in place of a Date
//because Instant are immutable (Item 17).

import java.util.*;

//Broken "immutable" time period class
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
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException(start + " after " + end);
    }
    this.start = start;
    this.end = end;
  }
  
  public Date start() { return start; }
  public Date end()   { return end;   }
} 

public class Item050a_make_defensive_copy
{
  public static void main(String args[]) 
  {
    Date start = new Date();
    Date end = new Date();
    Period p = new Period(start, end);
    
    //p.end was Mon Mar 05 11:46:28 PST 2018
    System.out.println("p.end was "+p.end());
    
    //Compile warning:
    //uses or overrides a deprecated API
    end.setYear(78);
    
    //Now p.end is Sun Mar 05 11:46:28 PST 1978
    System.out.println("Now p.end is "+p.end());
  }
}
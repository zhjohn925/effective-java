//You must program defensively, with the assumption that clients
//of your class will do their best to destroy its invariants.

package effective_java;

//Date is obsolete and should no longer be used in new code.
//Actually compile warning also means it. 

//As of Java 8, the obvious way to fix this problem is to use
//Instant (or Local-DateTime or ZonedDateTime) in place of a Date
//because Instant are immutable (Item 17).

import java.util.*;
import java.time.Instant;

//Broken "immutable" time period class
final class Period 
{
  private final Instant start;
  private final Instant end;
  
  /**
   * @param start the beginning of the period
   * @param end the end of period; must not precede start
   * @throws IllegalArgumentException if start is after end
   * @throws NullPointerException if start or end is null
   */
  public Period(Instant start, Instant end) {
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException(start + " after " + end);
    }
    this.start = start;
    this.end = end;
  }
  
  public Instant start() { return start; }
  public Instant end()   { return end;   }
} 

public class Item050b_make_defensive_copy
{
  public static void main(String args[]) 
  {
    Instant end = Instant.now();
    Instant start = Instant.now();
  
    //Test out IllegalArgumentException: 
    //start = start.plusSeconds(10000);
  
    Period p = new Period(start, end);
    
    //p.end was 2018-03-05T20:03:20.205Z
    System.out.println("p.end was "+p.end());
    
    //Returns a copy of this instant with the specified 
    //duration in seconds added.
    end = end.plusSeconds(10000);
    System.out.println("the new end is "+end);
    
    //This is unchanged ! - immutable
    //Now p.end is 2018-03-05T20:03:20.205Z
    System.out.println("Now p.end is "+p.end());
  }
}
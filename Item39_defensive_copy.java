package effective_java;

import java.util.*;

// javac -d class_path Item39_defensive_copy.java
// java -cp class_path effective_java.Item39_defensive_copy

final class Period {
	//Date class is mutable
	private final Date start;
	private final Date end;

	/**
		* @param start the beginning of the period
		* @param end the end of the period: must not precede start
		* @throws IllegalArgumentException if start is after end
		* @throws NullPointerException if start or end is null
	*/

  //--implement 1:		
	//--Attack the internals of a Period instance
	//Date start = new Date();
	//Date end = new Date();
	//Period p = new Period(start, end);
	//end.setYear(78);		
	//--public Period(Date start, Date end) {
	//--	if (start.compareTo(end)>0) {
	//--		throw new IllegalArgumentException(start+" after "+end)
	//--	}
  //--  this.start = start;
	//--	this.end = end;		
	//--}	

	//--implement 2: makes defensive copies of parameters
	public Period(Date start, Date end) {
		//--Donot use Date's clone method to make the defensive copies.
		//	Because Date is nonfinal, the clone() method is not guaranteed
		//	to return an object whose calss is java.util.Date. It could
		//	return an instance of an untrusted subclass specifically 
		//	designed for malicious mischief. 
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());

		if (this.start.compareTo(this.end)>0) {
			throw new IllegalArgumentException(start+" after "+end);
		}
	}	

	public Date start() {
		//--this can be attacked by p.start().setYear(78)
		//return start;
		//--instead,
		return new Date(start.getTime());
	}

	public Date end() {
		//--this can be attacked by p.end().setYear(78)	
		//return end;
		//--instead,
		return new Date(end.getTime());
	}

	public String toString() {
		return start + " - " + end;
	}
} 


public class Item39_defensive_copy {
	public static void main(String[] args) {
		Date start = new Date();
		Date end = new Date();
		Period p = new Period(start, end);
		//System.out.println("Period0: "+p.start()+" - "+p.end());
		System.out.println("Period0: "+p);
		end.setTime(9000);
		//System.out.println("Period1: "+p.start()+" - "+p.end());
		System.out.println("Period1: "+p);
		p.end().setTime(0);
		//System.out.println("Period2: "+p.start()+" - "+p.end());
		System.out.println("Period2: "+p);
	}
}
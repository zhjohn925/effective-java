package effective_java;

//Item 78: Consider serialization proxies instead of 
//serialized instances.

import java.io.*;
import java.util.*;

//Copy Item39 Period as the enclosing class into here.
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
	public Period(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());

		if (this.start.compareTo(this.end)>0) {
			throw new IllegalArgumentException(start+" after "+end);
		}
	}	

	public Date start() {
		return new Date(start.getTime());
	}

	public Date end() {
		return new Date(end.getTime());
	}

	public String toString() {
		return start + " - " + end;
	}


	//Serialization proxy for Period class
	private static class SerializationProxy 
											implements Serializable {
		private final Date start;
		private final Date end;

		SerializationProxy(Period p) {
			this.start = p.start;
			this.end = p.end;
		}

		private static final long serialVersionUID = 
					23409824382348528L;  //Any number will do (Item 75)										
	}

	//--The writeReplace() method translates an instance of the 
	//	enclosing class (Period) to its serialization proxy 
	//	prior to serialization
	//--With this method in place, the serialization system will 
	//	never generate a serialized instance of the enclosing 
	//  class (Period).  
	private Object writeReplace() {
		return new SerializationProxy(this);
	}

	//--An attacker might fabricate one (instance of the enclosing 
	//	class) in an attempt to voilate the class's invariants.
	//--This readObject() method guarantees that such an 
	//  attack would fail.
	private void readObject(ObjectInputStream stream) 
						throws InvalidObjectException {
		throw new InvalidObjectException("Proxy required");					
	}

	//--This method causes the serialization system to translate
	//	the serialization proxy back into an instance of the
	//	enclosing class upon deserialization.
	private Object readResolve() {
		return new Period(start, end);  //Uses public constructor
	}

}


public class Item78_serialize_proxy {
	// Byte stream could not have come from real Period instance!
	private static final byte[] serializedForm = new byte[] {
	(byte)0xac, (byte)0xed, 0x00, 0x05, 0x73, 0x72, 0x00, 0x06,
	0x50, 0x65, 0x72, 0x69, 0x6f, 0x64, 0x40, 0x7e, (byte)0xf8,
	0x2b, 0x4f, 0x46, (byte)0xc0, (byte)0xf4, 0x02, 0x00, 0x02,
	0x4c, 0x00, 0x03, 0x65, 0x6e, 0x64, 0x74, 0x00, 0x10, 0x4c,
	0x6a, 0x61, 0x76, 0x61, 0x2f, 0x75, 0x74, 0x69, 0x6c, 0x2f,
	0x44, 0x61, 0x74, 0x65, 0x3b, 0x4c, 0x00, 0x05, 0x73, 0x74,
	0x61, 0x72, 0x74, 0x71, 0x00, 0x7e, 0x00, 0x01, 0x78, 0x70,
	0x73, 0x72, 0x00, 0x0e, 0x6a, 0x61, 0x76, 0x61, 0x2e, 0x75,
	0x74, 0x69, 0x6c, 0x2e, 0x44, 0x61, 0x74, 0x65, 0x68, 0x6a,
	(byte)0x81, 0x01, 0x4b, 0x59, 0x74, 0x19, 0x03, 0x00, 0x00,
	0x78, 0x70, 0x77, 0x08, 0x00, 0x00, 0x00, 0x66, (byte)0xdf,
	0x6e, 0x1e, 0x00, 0x78, 0x73, 0x71, 0x00, 0x7e, 0x00, 0x03,
	0x77, 0x08, 0x00, 0x00, 0x00, (byte)0xd5, 0x17, 0x69, 0x22,
	0x00, 0x78 };

	public static void main(String[] args) {
		Period p = (Period) deserialize(serializedForm);
		System.out.println(p);
	}

	//Returns the object with the specified serialized form
	private static Object deserialize(byte[] sf) {
		try {
			InputStream is = new ByteArrayInputStream(sf);
			ObjectInputStream ois = new ObjectInputStream(is);
			return ois.readObject();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
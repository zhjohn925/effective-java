package effective_java;

//  https://www.youtube.com/watch?v=xgf1XJw49oE

//javac -d class_path SimplilearnStringBuffer.java
//java -cp class_path effective_java.SimplilearnStringBuffer


//--String is immutable, the below atually creates 
//  new String object at each call, that causes low performance
//      concat += (i % 10);
//--Instead, use StringBuffer which is mutable. 
//--The below output shows the big performance difference

//Interations: 100000
//Buffer :16
//concatStringBuffer -> length: 100000 time: 23
//concatString -> length: 100000 time: 8474


public class SimplilearnStringBuffer {
	private static final int BUFFSIZE = 16;
	private static final int ITERATIONS = 100000;

	private void concatString() {
		System.out.print("concatString -> ");
		long startTime = System.currentTimeMillis();
		String concat = "";
		for (int i=0; i<ITERATIONS; i++) {
			concat += (i % 10);
		}
		long endTime = System.currentTimeMillis();
		System.out.print("length: "+concat.length());
		System.out.println(" time: "+(endTime-startTime));
	}

	private void concatStringBuffer() {
		System.out.print("concatStringBuffer -> ");
		long startTime = System.currentTimeMillis();
		StringBuffer concat = new StringBuffer(BUFFSIZE);
		for (int i=0; i<ITERATIONS; i++) {
			concat.append(i % 10);
		}
		long endTime = System.currentTimeMillis();
		System.out.print("length: "+concat.length());
		System.out.println(" time: "+(endTime-startTime));
	}

	public static void main(String[] args) {
		SimplilearnStringBuffer strb = new SimplilearnStringBuffer();
		System.out.println("Interations: "+ITERATIONS);
		System.out.println("Buffer :"+BUFFSIZE);
		strb.concatStringBuffer();
		strb.concatString();
	}
}
//Item 9:
//Always use try-with-resources in preference to try-finally
//when working with resources that must be closed.

//with try-finally statements, the first exception can be 
//obliterated by the second one. There is no record of the first exception in
//the exception stack trace, which can greatly complicate debugging
//in real systems—usually it’s the first exception that you want to see
//in order to diagnose the problem.

//To be usable with try-with-resources, a resource must implement
//the AutoCloseable interface, which consists of a
//single void-returning close method.

//The try-with-resources in firstLineOfFile1():
//If exceptions are thrown by  both the readLine call and 
//the (invisible) close, the latter exception is suppressed in favor of the former.
//These suppressed exceptions are not merely discarded; they are printed in 
//the stack trace with a notation saying that they were suppressed. You can also access them
//programmatically with the getSuppressed method.

package effective_java;

import java.util.*;
import java.io.*;

public class Item009_try_with_resources
{
  public static final int BUFFER_SIZE = 4096;

	//try-with-resources
	//the best way to close resources !
	//it throws exceptions if it can't open or read from it. 
  static String firstLineOfFile1(String path) throws IOException
  {
    try (BufferedReader br = new BufferedReader (new FileReader(path)))
    {
    	return br.readLine();
    }
  }


//try-with-resources with a catch clause
	//it does not throw exceptions, but return a default value if
	//it can't open the file or read from it. 
	static String firstLineOfFile2(String path, String defaultVal)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(path)))
		{
			return br.readLine();
		} catch (IOException e) {
			return defaultVal;
		}
	}


  //try-with-resources on multiple resources
  static void copy(String src, String dst) throws IOException
  {
  	try (InputStream  inf  = new FileInputStream(src);
  		   OutputStream outf = new FileOutputStream(dst))
  	{
  		byte[] buf = new byte[BUFFER_SIZE];
  		int n;
  		while ((n=inf.read(buf)) >= 0) {
  			outf.write(buf, 0, n);
  		}
  	}
  }

	
	public static void main(String args[]) throws IOException
	{
		String l = firstLineOfFile1("zreadme.txt");
		System.out.println("firstLineOfFile1(zreadme.txt) is " + l);
		String defaultVal = firstLineOfFile2("no_such_file.txt", "default value");
		System.out.println("firstLineOfFile2(no_such_file.txt) is " + defaultVal);
	}
}
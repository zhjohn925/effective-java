package effective_java;

//One of the disadvantage by using Stream:
//Stream's terminal operation (.forEach() in the example) can not access to 
//the exponent (p) in the previous (intermediate) operations. 
//To be workaround, we know the exponent is simply the number of bits in the 
//binary representation. so use bitLength() to get p in the terminal operation. 

//javac -d class_path Item045_stream_primes.java 
//java -cp class_path effective_java/Item045_stream_primes 
//Output:
//  2: 3
//  3: 7
//  5: 31
//  7: 127
//  13: 8191
//  17: 131071
//  19: 524287
//  31: 2147483647


import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;


public class Item045_stream_primes
{
  private static BigInteger I2 = new BigInteger("2");

  //a Mersenne number is a number of the form 2^p - 1.  if p is prime, 
  //the corresponding Mersenne number may be prime. if so, 
  //it's a Mersenne prime. 
  
  static Stream<BigInteger> primes()
  {
    //BigInteger I2 = new BigInteger("2");
    return Stream.iterate(I2, BigInteger::nextProbablePrime);
  }

  public static void main(String args[])
  {
    //Stream::map() 
    //Returns a stream consisting of the results of applying the given function 
    //to the elements of this stream.

    primes().map(p->I2.pow(p.intValueExact()).subtract(BigInteger.ONE))
    .filter(mersenne->mersenne.isProbablePrime(50))
    .limit(8)
    .forEach(mp->System.out.println(mp.bitLength()+": "+mp));
    
  }

}
//Many enums are naturally associated with a single int value.
//All enums have an ordinal() method, which returns the numerical 
//position of each enum constant in its type. 

package effective_java;

//Abuse of ordinal to derive an associated value 
//-DON'T DO THIS
//If the constants are reordered, the numberOfMusicians() method will break. Or
//if you want to add a second enum constant associated with an int value that
//you've already used, you're out of luck.
//
//enum Ensemble {
//  SOLO, DUET, TRIO, QUARTET, QUINTET, 
//  SEXTET, SEPTET, OCTET, NONET, DECTET;
//  
//  public int numberOfMusicians() {
//    return ordinal() + 1;   //ie. SOLO = 0 + 1 = 1
//                            //    DUET = 1 + 1 = 2
//                            //    TRIO = 2 + 1 = 3
//  }
//}    
//
//To fix this by using instance fields as below

//$ javac -d class_path Item035_enum_donot_use_ordinals.java 
//$ java -cp class_path effective_java/Item035_enum_donot_use_ordinals
//  Output:
//    Number of musicians for SOLO is 1
//    Number of musicians for DUET is 2
//    Number of musicians for TRIO is 3
//    Number of musicians for QUARTET is 4
//    Number of musicians for QUINTET is 5
//    Number of musicians for SEXTET is 6
//    Number of musicians for SEPTET is 7
//    Number of musicians for OCTET is 8
//    Number of musicians for DOUBLE_QUARTET is 8
//    Number of musicians for MONET is 9
//    Number of musicians for DECTET is 10
//    Number of musicians for TRIPLE_QUARTET is 12

enum Ensemble {
  SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
  SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
  MONET(9), DECTET(10), TRIPLE_QUARTET(12);
  
  private final int numberOfMusicians;   //instance field
  
  //constructor
  Ensemble(int size) {
    this.numberOfMusicians = size;
  } 
  
  public int numberOfMusicians() {
    return numberOfMusicians;
  } 
}

public class Item035_enum_donot_use_ordinals
{
  public static void main(String args[]) 
  { 
     for (Ensemble e : Ensemble.values()) {
       System.out.println("Number of musicians for "+e.toString()+" is "+e.numberOfMusicians());
     } 
  }
}
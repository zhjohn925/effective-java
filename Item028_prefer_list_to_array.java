package effective_java;

import java.util.*;

//The below two examples with error.  Array error is caught in runtime, 
//but List error is caught in compile time.

//java -cp class_path effective_java/Item028_prefer_list_to_array
//Exception in thread "main" java.lang.ArrayStoreException:


public class Item028_prefer_list_to_array
{
  public static void main(String args[])
  {
    //Fails at runtime !
    //Throws ArrayStoreException
    Object[] objArray = new Long[1];
    objArray[0] = "I don't fit in";  
    
    //Won't compile
    //error: incompatible types: 
    List<Object> objList = new ArrayList<Long>(); 
    objList.add("I don't fit in");
  }

}
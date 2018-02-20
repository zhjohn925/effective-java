package effective_java;

//$ javac -d class_path GenericDemo_no_generic.java 
//$ java -cp class_path effective_java/GenericDemo_no_generic

import java.util.List;
import java.util.ArrayList;

class ObjectContainer {
  private Object obj;
  
  public Object getObj() { return obj; }
  public void setObj(Object obj)  { this.obj = obj; }
}

//It has the potential to cause exceptions down the road,
//since it is not type-safe and it requires you to use an
//explicit cast whenever the encapsulated object is retrieved.

public class GenericDemo_no_generic
{
  public static void main(String args[])
  {
    ObjectContainer myObj = new ObjectContainer();
    
    myObj.setObj("Test");
    System.out.println("Value of myObj: " + myObj.getObj());
    
    //store an int which is autoboxed to an Integer Object
    myObj.setObj(3);
    System.out.println("Value of myObj: " + myObj.getObj());
    
    List myObjList = new ArrayList();
    //has warning: "uses unchecked or unsafe operations"
    myObjList.add(myObj);
    //1. Must cast by (ObjectContainer); otherwise 
    //   compile error due to getObj() not found.
    //2. Since myObj is an Integer (not a String), therefore,
    //   gets Exception in thread "main" java.lang.ClassCastException
    String myStr = (String)((ObjectContainer)myObjList.get(0)).getObj();
    System.out.println("MyStr: "+myStr);
  }
}


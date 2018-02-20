package effective_java;

//Benefits of using generics:
//1. Stronger type-checking in compile time, which saves time 
//   by fending off ClassCastExeptions that might be thrown at runtime.
//2. Elimination of casts

//$ javac -d class_path GenericDemo_generic_container.java
//$ java -cp class_path effective_java/GenericDemo_generic_container

import java.util.List;
import java.util.ArrayList;


class GenericContainer<T> {
  private T obj;
  
  public GenericContainer() {}
  public GenericContainer(T obj) { this.obj = obj; }
  
  public void setObj(T obj) { this.obj = obj; }
  public T getObj()  { return obj; }
}

class ObjectContainer {
  private Object obj;
  
  public void setObj(Object obj) { this.obj = obj; }
  public Object getObj() { return obj; }
}

public class GenericDemo_generic_container
{
  public static void main(String args[]) 
  { 
    /////////////////////////////////   
    //No generics example:  
    /////////////////////////////////
    List myObjList = new ArrayList();
    
    for (int x=0; x<10; x++) {
      //Object myObj = new ObjectContainer();
      //((ObjectContainer)myObj).setObj("Test" + x);
      ObjectContainer myObj = new ObjectContainer();
      myObj.setObj("Test"+x);
      
      //compile warning: uses unchecked or unsafe operations
      //Suppressing warnings on local variable declaration      
      @SuppressWarnings("unchecked") 
      boolean chk = myObjList.add(myObj);
    }
    
    //Get the objects we need to cast
    for (int x=0; x<myObjList.size(); x++) {
      ObjectContainer obj = (ObjectContainer)myObjList.get(x);
      System.out.println("Objet value: "+obj.getObj());
    }
    
    ///////////////////////////////////
    //generics example:
    ///////////////////////////////////
    List <GenericContainer<String>> myGenericList = new ArrayList<>();
    
    for (int x=0; x<10; x++) {
      GenericContainer<String> myObj = new GenericContainer<>();
      myObj.setObj(" Generic Test" + x); 
      myGenericList.add(myObj);
    }
    
    for (GenericContainer<String> obj : myGenericList) {
      String objStr = obj.getObj();
      System.out.println(objStr);
    }
  }
}

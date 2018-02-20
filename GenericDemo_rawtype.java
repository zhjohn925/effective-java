package effective_java;

//- Note that a generic that does not have a type assigned to it 
//  is known as a raw type:
//  GenericContainer rawContainer = new GenericContainer();

//- Raw types can sometimes be useful for backward compatibility, but it is not a 
//  good idea to use them in everyday code.  

//- Raw types eliminate type-checking at compile time, allowing code to become
//  error-prone at runtime !

class GenericContainer <T> {
  private T obj;
  
  public GenericContainer() {}
  
  public GenericContainer(T t) {
    this.obj = t;
  }
  
  public T getObj() { return obj; }
  
  public void setObj(T t) { obj = t; }
} 


public class GenericDemo_rawtype
{
  @SuppressWarnings("unchecked") 
  public static void main(String args[])
  {
    GenericContainer rawContainer = new GenericContainer();
    //this gets compile warning: "uses unchecked or unsafe operations"
    //@SuppressWarnings("unchecked") to hide the warning
    rawContainer.setObj(3);
    
    int e = (Integer)rawContainer.getObj();
    System.out.println(e);
  } 

}


package effective_java;


class GenericNumberContainer <T extends Number>
{
  private T obj;
  public GenericNumberContainer() {}
  public GenericNumberContainer(T obj) { this.obj = obj; }
  
  public void setObj(T obj) { this.obj = obj; }
  public T getObj() { return obj; }
}


public class GenericDemo_bounded_type
{
  public static void main(String args[])
  {
    GenericNumberContainer<Integer> nObj = new GenericNumberContainer<> ();
    nObj.setObj(3);
    System.out.println("nObj value = "+nObj.getObj()); 
    
    //Compile error: type argument String is not within bounds of 
    //               type-variable T
    //GenericNumberContainer<String> sObj = new GenericNumberContainer<> ();
  }
}
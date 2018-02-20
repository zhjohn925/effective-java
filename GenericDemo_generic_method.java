package effective_java;

//Method arguments can contain generic types, and methods can also 
//contain generic return types

public class GenericDemo_generic_method
{

  //Generic method:
  public static <N extends Number> double add(N a, N b)
  {
     return a.doubleValue() + b.doubleValue();
  }
  
  public static void main(String args[])
  {
    System.out.println("3 + 3f = " + add(3, 3f));
  }
}
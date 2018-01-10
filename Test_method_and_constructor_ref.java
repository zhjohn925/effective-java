package effective_java;

@FunctionalInterface
interface Converter<F, T> 
{
  T convert(F from);
}

public class Test_method_and_constructor_ref
{
  public static void main(String args[])
  {
    Converter<String, Integer> converter = (from)->Integer.valueOf(from);
    Integer converted = converter.convert("123");
    System.out.println(converted);
  }
}
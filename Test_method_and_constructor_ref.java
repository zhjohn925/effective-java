package effective_java;

import java.util.function.Consumer;

class Person 
{
  String firstName, lastName;
  Person() {}
  Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
  public void print()
  {
    System.out.println(firstName + " " + lastName);
  }
}

class Something 
{
  String startsWith(String s)
  {
    return String.valueOf(s.charAt(0));
  }
}

@FunctionalInterface
interface Converter<F, T> 
{
  T convert(F from);
}

interface PersonFactory<P extends Person>
{
  P create(String firstName, String lastName);
}

public class Test_method_and_constructor_ref
{
  public static void main(String args[])
  {
    //use arbitrary interfaces as lambda expressions as long as the interface 
    //only contains one abstract method
    Converter<String, Integer> converter = (from)->Integer.valueOf(from);
    Integer converted = converter.convert("123");
    System.out.println(converted);
    //OR: utilizing static method references
    Converter<String, Integer> converter2 = Integer::valueOf;
    Integer converted2 = converter.convert("246");
    System.out.println(converted2);
    //OR: reference object methods
    Something something = new Something();
    Converter<String, String> converter3 = something::startsWith;
    String converted3 = converter3.convert("Java");
    System.out.println(converted3);
    //OR: reference constructors
    PersonFactory<Person> personFactory = Person::new;
    Person person = personFactory.create("Peter", "Parker");
    person.print();
    
    //Consumer
    Consumer<Person> greeter = (p)->System.out.println("Hello, "+p.firstName);
    greeter.accept(new Person("Luke", "Skywalker"));
  }
}
package effective_java;

//The Predicate functional interface can be used for comparison purposes, returning 
//a boolean to indicate if a given object satisfies the requirements of a test.

// $ javac -d class_path GenericDemo_predicate_lambda.java 
// $ java -cp class_path effective_java/GenericDemo_predicate_lambda
// Output:
//   Java EE 7 Recipes 
//   Introducing Java EE 7 


import java.util.*;
import java.util.function.Predicate;

public class GenericDemo_predicate_lambda
{
   public static void compareStrings (List<String> list, Predicate<String> predicate)
   {
     list.stream()
         .filter(n->predicate.test(n))
         .forEach(n->System.out.println(n+" "));
   }
   
   public static void main(String args[]) 
   {
     List<String> bookList = new ArrayList<>();
     bookList.add("Java 8 Recipes");
     bookList.add("Java EE 7 Recipes");
     bookList.add("Introducing Java EE 7");
     bookList.add("JavaFX 8:  Introduction By Example");
     
     compareStrings(bookList, n->n.contains("Java EE"));
   }
}
//Item 29: Favor Generic Types

//Write your own generic types, stack implementation from Item 7.

package effective_java;

import java.util.*;

//Object-based collection - a prime candidate for generics

class Stack <E> {
  private E[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  
  //The compiler may not be able to prove the program is typesafe, but you can.
  //The elements array will contain only E instances from push(E). This is 
  //sufficient to ensure type safety. but the runtime type of the array won't be
  //E[], it will always be Object[] !
  @SuppressWarnings("unchecked")
  public Stack() {
    //compile error: generic array creation (see Item 28)
    //you can't create an array of a non-reifiable type, such as E
    //:
    //elements = new E[DEFAULT_INITIAL_CAPACITY];
    //:
    //To fix it by creating an array of Object
    //   and cast it to the generic array type.
    //now you get compile waining since the compiler may not be able to prove 
    //that your program is typesafe.
    //   "uses unchecked or unsafe operations"
    //To get rid of warning by the above suppress warning
    elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public boolean isEmpty() {
    return size == 0;
  }
  
  public void push(E e) {
    ensureCapacity();
    elements[size++] = e;
  }
  
  public E pop() {
    if (size==0) throw new EmptyStackException();
    E result = elements[--size];
    //Eliminate obsolete reference
    elements[size] = null;
    return result;
  }

  private void ensureCapacity()
  {
    //elements.length is total allocated array space
    //size is number of element in the array 
    if (elements.length == size) {
      //static <T> T[] copyOf(T[] original, int newLength) 
      elements = Arrays.copyOf(elements, 2*size+1);
    } 
  }
}


public class Item029_favor_generic_types
{
  public static void main(String args[]) 
  {
    //Stack stack = new Stack();
    //stack.push("Hello");
    //Integer i = Integer.valueOf(1);
    //stack.push(i);
    //System.out.println(stack.pop());
    //System.out.println(stack.pop());
  }
}
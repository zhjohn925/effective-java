
package effective_java;

import java.util.*;

class Stack <E> {
  private E[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  //mix generic with array will get compile warning
  @SuppressWarnings("unchecked")
  public Stack() {
    elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
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
  
  public boolean isEmpty() {
    return size==0;
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
  
  //? presents E or its sub class objects
  //if not use wildcard (?), get compile error
  //
  //error: incompatible types: Iterable<Integer> cannot be converted 
  //       to Iterable<Number>
  public void pushAll(Iterable <? extends E> src) {
    for (E e : src) {
      push(e);
    }
  }

  //pop element from Stack and put it into dst
  //? presents E or its super class objects
  //
  //if not use wildcard (?), get compile error:
  //error: incompatible types: Collection<Object> cannot be 
  //converted to Collection<Number>
  public void popAll(Collection<? super E> dst) 
  {
    while (!isEmpty()) {
      dst.add(pop());
    }
  }

  public void printAll()
  {
    if (isEmpty()) {
      System.out.println("Stack is empty");
    } else {
      for (int i=0; i<size; i++) {
        System.out.print(" "+elements[i]);
      }
      System.out.println();
    }
  }

}

public class Item031_use_bounded_wildcards
{
  public static void main(String args[])
  {
     Integer foo[] = {1, 2, 3, 4, 5, 6, 7, 8};
     Iterable<Integer> fooList = Arrays.asList(foo);
     Double dfoo[] = {1.1, 1.2, 1.3};
     Iterable<Double> doubleList = Arrays.asList(dfoo);
     Collection<Object> dst = new ArrayList<>();
     
     Stack<Number> numberStack = new Stack<>();
     numberStack.pushAll(fooList);
     numberStack.pushAll(doubleList);
     numberStack.printAll();
     numberStack.popAll(dst);
     numberStack.printAll();
     System.out.println(dst);
  }
}
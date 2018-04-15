//Varargs methods, formally known as variable arity methods, accept
//zero or more arguments of a specified type.

//The varargs facility works by first creating an array whose size is
//the number of arguments passed at the call site, then putting the 
//argument values into the array, and finally passing the array to
//the method.

//Every invocation of a varargs method causes an array allocation
//and initialization.

//In performance-critical situations, suppose you've determined that
//95 percent of tha calls to a method have three or fewer parameters.  
//Then declare five overloadings of the method, one each with zero
//through three ordinary parameters, and a single varargs method for
//use when the number of arguments exceeds three:

public void foo() {};
public void foo(int a1) {}
public void foo(int a1, int a2) {}
public void foo(int a1, int a2, int a3) {}
public void foo(int a1, int a2, int a3, int... rest) {}

//Now you know that you'll pay the cost of the array creation only in 
//the 5 percent of all invocations where the number of parameters
//exceeds three.

//refer to Item 36 for EnumSet

 

package effective_java;

//A primary use of function object (like function pointers in c) is to implement 
//   the Strategy pattern,  to implement this pattern
// 1. declare an interface to represent the strategy  
// 2. a class that implements this interface for each concrete strategy
// 3. when a concrete strategy is used only once, it is typically declared and 
//    instantiated as an anonymous class. 
//    Note: An anonymous class in this way will create a new instance each time 
//    the call is executed. If it is to be executed repeatedly, consider #4 below
// 4. when a concrete strategy is designed for repeated use, it is generally 
//    implemented as a private static class and exported in a public static final
//    field whose type is the strategy interface

// javac -d class_path Item21_function_object.java
// java -cp class_path effective_java.Item21_function_object


// #1 above: Strategy interface
//public interface Comparator<T> {
interface Comparator<T> {
    public int compare(T t1, T t2);
}

// #2 and #4 above
class StringLengthComparator implements Comparator<String> {

    private StringLengthComparator() {}
    public static final StringLengthComparator INSTANCE = new StringLengthComparator();
    
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}

public class Item21_function_object {
    public static void main(String[] args) {
        int result = StringLengthComparator.INSTANCE.compare("abc", "xyz");
        System.out.println("Compare abc with xyz: "+result);
        result = StringLengthComparator.INSTANCE.compare("ijk", "mnop");
        System.out.println("Compare ijk with mnop: "+result);        
    }
}
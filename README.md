# effective-java
# Joshua Bloch, Effective Java, Second Edition

#The synchronized keyword ensures that only a single thread can execute a method or block at one time.
#Two basic synchroniztion idioms:
 - synchronized methods
 - synchronized statements
 First, when one thread is executing a synchronized method for an object, all other threads that invoke synchronized methods for the same object block until the first thread is done with the object.
 Second, when a synchronized method exists, it automatically establishes a happens-before relationship with any subsequent invocation of a synchronized method for the same object. this guarantees that changes to the state of the object are visible to all threads.

#Atomic access & volatile (Item 66)
an atomic action is one that effectively happens all at once.
- Reads and writes are atomic for reference variables and for most primitive variables (all types except long and double).
- Reads and writes are atomic for all variables declared volatile (including long and double variables).
- Atomic actions cannot be interleaved, so they can be used without fear of thread interferece. However, this does not eliminate all need to synchronize atomic actions, because memory consistency errors are still possible.
- Using simple atomic variable access is more efficient than accessing these variables through synchronized code, but requires more care by the programmer to avoid memory consistency errors.

 #final keyword - Item 15
 finals fields cannot be modified after the object is constructed.

 #Lazy initialiaztion - Item 71 
 - is the act of delaying the initialization of a field until its value is needed.

 #Item 13
 - Ensure that objects referenced by public static final fields are immutable.
 - It is wrong for a class to have a public static final array field, or an accessor
   that returns such a field.  A nonzero-length array is always mutable, clients will be able to modify the contents of the array, so potential security hole.
 - There are two ways to fix this problem:
   	A. make the public array private and add a public immutable list
   			private static final Thing[] PRIVATE_VALUES = { ...... };
   			public static final List<Thing> VALUES = 
   		  		   		Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
   	B. make the array private and add a public method that returns a copy of a private array
   			private static final Thing[] PRIVATE_VALUES = { ...... };
   			public static final Thing[] values() {
   				return PRIVATE_VALUES.clone();
   			}	  		   		


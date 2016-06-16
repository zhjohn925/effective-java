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
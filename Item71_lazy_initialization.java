package effective_java;

// - Should initialize most fields normally, not lazily.
// - Lazy initialization is the act of delaying the initialization of a field until 
//   its value is needed. It is a double-edged sword. It decreases the cost of 
//   initializing a class or creating an instance, at the expense of increasing 
//   the cost of accessing the lazily initialized field.  
// - For a static field, use the lazy initialization holder class idiom (also known as
//   the initialize-on-demand holder class idiom)
// - For an instance field, use the double-check idiom



//1. Normal initializaiton of an instance field
private final FieldType field = computeFieldValue();

//2. Lazy initilization holder class idiom for static fields
private static class FieldHolder {
	static final FieldType field = computeFieldValue();
}

static FieldType getField() { return FieldHolder.field; }

//3. Double-check idiom for lazy initialization of instance fields
private volatile FieldType field;
FieldType getField() {
	FieldType result = field;
	if (result == null) {  //first check (no locking)
		synchronized(this) {
			result = field;
			if (result == null) {  //second check (with locking)
				field = result = computeFieldValue();
			}
		}
	}
	return result;
} 
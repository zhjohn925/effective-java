//Item 2:
//Consider a Builder when faced with many Constructor parameters.

//The builder is typically a static member class (Item 24) of the class
//it builds.

//Usage:
//NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).calories(100)
//                      .sodium(35).carbohydrate(27).build();


package effective_java;

//public class NutritionFacts
class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;
    
    //The NutritionFacts class is immutable
    //private NutritionFacts constructor
    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
    
    public void printInfo() {
        System.out.println("servingSize: "+this.servingSize);
        System.out.println("servings: "+this.servings);
        System.out.println("calories: "+this.calories);
        System.out.println("fat: "+this.fat);
        System.out.println("sodium: "+this.sodium);
        System.out.println("carbohydrate: "+this.carbohydrate);
    }
    
    //The builder is typically a static member class,
    //which is invoked by NutritionFacts.Builder(240, 8)     
    public static class Builder {
        //Required parameters
        private final int servingSize;
        private final int servings;
        //Optional parameters
        private int calories = 0;
        private int fat = 0;
        private int carbohydrate = 0;
        private int sodium = 0;
        //Builder Constructor
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        //Methods
        public NutritionFacts build() {
            //return NutritionFacts object by calling private NutritionFacts constructor
            return new NutritionFacts(this);
        }
        //Setters
        //The builder's setter methods return the builder itself so that 
        //invocations can be chained, resulting in a fluent API
        //ie. 
        //NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).calories(100)
        //                      .sodium(35).carbohydrate(27).build();
        public Builder calories(int val) {
            calories = val;
            return this;
        }
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }
        public Builder sodium(int val) {
            sodium = val;
            return this;
        }        
    }
    

    
}

public class Item002a_builder_pattern {
    public static void main(String args[]) {
        NutritionFacts cocaCola = new NutritionFacts.Builder(230,8).calories(100)
                                    .sodium(35).carbohydrate(27).build();
        cocaCola.printInfo();                                
    }
}


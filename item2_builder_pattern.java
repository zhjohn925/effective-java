package effective_java;

////////////////////////////////////////////////////////////////////
// javac -d  class_path  Item2_builder_pattern.java
// java  -cp class_path  effective_java/Item2_builder_pattern 
////////////////////////////////////////////////////////////////////

//Builder Pattern 
//public class NutritionFacts
class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;
    
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

public class Item2_builder_pattern {
    public static void main(String args[]) {
        NutritionFacts cocaCola = new NutritionFacts.Builder(230,8).calories(100)
                                    .sodium(35).carbohydrate(27).build();
        cocaCola.printInfo();                                
    }
}


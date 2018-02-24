
package org.javase.genericsexamples.coffeehouse;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juneau
 */
public class DoughnutRoast extends CoffeeType implements Medium {
    List<String> description;
    
    public DoughnutRoast(){
        //NOTE: skip <> will get compile warning
        //   "use unchecked or unsafe operations"
        description = new ArrayList<>();
        description.add(AROMATIC);
        description.add(SWEET);
        description.add(GROUND);
    }
    
    @Override
    public List<String> getDescription(){
        return description;
    }
    

}

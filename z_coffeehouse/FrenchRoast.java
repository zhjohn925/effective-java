

package org.javase.genericsexamples.coffeehouse;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juneau
 */
public class FrenchRoast extends CoffeeType implements Dark {
    List<String> description;
    
    public FrenchRoast(){
        //NOTE: skip <> will get compile warning
        //   "use unchecked or unsafe operations"
        description = new ArrayList<>();
        description.add(AROMATIC);
        description.add(BOLD);
        description.add(WHOLE_BEAN);
    }
    
    @Override
    public List<String> getDescription(){
        return description;
    }
    

}

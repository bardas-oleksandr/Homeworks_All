package Problems_01_and_03.Shop;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

class ChoiceMapWrapper implements Serializable {
    private Map<Integer,ProductList<Product>> choiceMap;

    ChoiceMapWrapper(){
        this.choiceMap = new LinkedHashMap<>();
    }

    Map<Integer,ProductList<Product>> get(){
        return this.choiceMap;
    }
}

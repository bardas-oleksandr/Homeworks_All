package Shop;

import Flowers.Flower;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChoiceMapWrapper {
    private Map<Integer,List<Flower>> choiceMap;

    public ChoiceMapWrapper(){
        this.choiceMap = new LinkedHashMap<>();
    }

    public Map<Integer,List<Flower>> get(){
        return this.choiceMap;
    }
}

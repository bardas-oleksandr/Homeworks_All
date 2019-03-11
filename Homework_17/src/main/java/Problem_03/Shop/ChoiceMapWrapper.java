package Problem_03.Shop;

import Problem_03.Flowers.Flower;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChoiceMapWrapper implements Serializable {
    private Map<Integer,List<Flower>> choiceMap;

    public ChoiceMapWrapper(){
        this.choiceMap = new LinkedHashMap<>();
    }

    public Map<Integer,List<Flower>> get(){
        return this.choiceMap;
    }
}

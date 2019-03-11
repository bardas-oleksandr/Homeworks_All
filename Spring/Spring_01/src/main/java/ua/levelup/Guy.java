package ua.levelup;

import java.util.Map;

public class Guy {
    private Map<String, Damsel> damselMap;

    public Guy(){
        this.damselMap = null;
    }

    public Map<String,Damsel> getDamselMap(){
        return this.damselMap;
    }

    public void setDamselMap(Map<String, Damsel> damselMap) {
        this.damselMap = damselMap;
    }
}

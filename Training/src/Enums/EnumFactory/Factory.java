package Enums.EnumFactory;

import Enums.EnumFactory.Warriors.*;

public enum Factory {
    HUMAN(HumanWarrior.class), ORC(OrcWarrior.class);

    private Class type;

    Factory(Class type){
        this.type = type;
    }

    public Warrior createFootman(){
        if(type == HumanWarrior.class){
            return new Footman();
        }else{
            return new Orc();
        }
    }

    public Warrior createKnight(){
        if(type == HumanWarrior.class){
            return new Knight();
        }else{
            return new Ogr();
        }
    }
}

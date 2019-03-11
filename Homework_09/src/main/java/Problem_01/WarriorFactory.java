package Problem_01;

import Problem_01.Warrior.*;

public enum WarriorFactory {
    HUMAN(true), ORCS(false);

    private boolean player;

    WarriorFactory(boolean player){
        this.player=player;
    }

    public Warrior createArcher(){
        if(player){
            return new Archer();
        }else{
            return new Troll();
        }
    }
    public Warrior createFootman(){
        if(player){
            return new Footman();
        }else{
            return new Goblin();
        }
    }
    public Warrior createKnight(){
        if(player){
            return new Knight();
        }else{
            return new Orc();
        }
    }
    public Warrior createHeavyKnight(){
        if(player){
            return new HeavyKnight();
        }else{
            return new CaveTroll();
        }
    }
}

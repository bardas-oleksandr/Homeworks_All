package Problem_01;

import Problem_01.Warrior.Warrior;

import java.util.Random;

public enum GroupFactory {
    HUMAN(true), ORCS(false);

    private boolean player;

    GroupFactory(boolean player){
        this.player=player;
    }

    public void createGroup(Warrior[] group){
        Random rnd = new Random();
        for(int i = 0; i < group.length; i++){
            int choice = rnd.nextInt(4);
            switch(choice){
                case 0:
                    if(this.player){
                        group[i] = WarriorFactory.HUMAN.createArcher();
                    }else{
                        group[i] = WarriorFactory.ORCS.createArcher();
                    }
                    break;
                case 1:
                    if(this.player){
                        group[i] = WarriorFactory.HUMAN.createFootman();
                    }else{
                        group[i] = WarriorFactory.ORCS.createFootman();
                    }
                    break;
                case 2:
                    if(this.player) {
                        group[i] = WarriorFactory.HUMAN.createKnight();
                    }else{
                        group[i] = WarriorFactory.ORCS.createKnight();
                    }
                    break;
                case 3:
                    if(this.player) {
                        group[i] = WarriorFactory.HUMAN.createHeavyKnight();
                    }else{
                        group[i] = WarriorFactory.ORCS.createHeavyKnight();
                    }
                    break;
            }
        }
    }
}

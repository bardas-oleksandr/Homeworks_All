package ua.levelup.XML.Quests;

import ua.levelup.XML.Heroes.Monster;

public class SlayMonsterQuest implements Quest {
    private Monster monster;

    public SlayMonsterQuest(){
        super();
    }

    public SlayMonsterQuest(Monster monster){
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    @Override
    public void questFormation() {
        this.monster.doSomethingBad();
    }

    @Override
    public boolean questResult(int param) {
        if(monster.getPower() < param){
            monster.die();
            return true;
        }
        return false;
    }
}

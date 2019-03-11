package ua.levelup.Anno.Quests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.levelup.Anno.Heroes.Monster;

@Service("slayMonsterQuest")
public class SlayMonsterQuest implements Quest {
    private Monster monster;

    public SlayMonsterQuest(){
        super();
    }

    @Autowired
    public SlayMonsterQuest(@Value("#{bigDragon}")Monster monster){
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

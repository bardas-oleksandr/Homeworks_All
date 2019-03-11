package ua.levelup.Anno.Heroes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.levelup.Anno.Quests.Quest;

@Service("knight")
public class BraveKnight extends GoodGuy {
    public BraveKnight(){
        super();
    }

    @Autowired
    public BraveKnight(@Value("#{slayMonsterQuest}")Quest quest, @Value("12")int power){
        super(quest,power);
    }

    @Override
    public void embarkOnQuest() {
        this.getQuest().questFormation();
        System.out.println("I am brave knight. And I am not gonna let you do it.\n");
        if(this.getQuest().questResult(this.getPower())){
            System.out.println("Brave knight won this battle.\n");
        }else{
            this.die();
        }
    }

    @Override
    public void die() {
        System.out.println("Rest in peace, brave knight...\n");
        this.setAlive(false);
    }
}

package ua.levelup.XML.Heroes;

import ua.levelup.XML.Quests.Quest;

public class BraveKnight extends GoodGuy {
    public BraveKnight(){
        super();
    }

    public BraveKnight(Quest quest, int power){
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

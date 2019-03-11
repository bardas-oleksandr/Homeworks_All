package ua.levelup;

import java.util.List;

public class BraveKnight implements Knight {
    private Quest quest;
    private String knightName;
    private Sword sword;
    private List<Damsel> damselList;

    BraveKnight(Quest quest){
        this.quest = quest;
        this.damselList = null;
    }

    public void setKnightName(String knightName) {
        this.knightName = knightName;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    @Override
    public List<Damsel> getDamselList() {
        return this.damselList;
    }

    @Override
    public void embarkOnQuest() {
        this.quest.embark();
    }

    @Override
    public void appearingOnTheScene(){
        System.out.println("I'm knight. My name is " + this.knightName +
        ".\nMy sword is " + sword.getLength() + " feets long.\n");
    }

    @Override
    public void lastScene(){
        System.out.println("The knight was buried like a hero\n");
    }
}

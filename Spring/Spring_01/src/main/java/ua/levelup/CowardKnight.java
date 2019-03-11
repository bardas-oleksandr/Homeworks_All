package ua.levelup;

import java.util.List;

public class CowardKnight implements Knight {
    private String knightName;
    private Quest quest;
    private Sword sword;
    private List<Damsel> damselList;

    public CowardKnight(Quest quest) {
        this.quest = quest;
    }

    public CowardKnight(Quest quest, Sword sword) {
        this.quest = quest;
        this.sword = sword;
    }

    public void setDamselList(List<Damsel> damselList){
        this.damselList = damselList;
    }

    @Override
    public List<Damsel> getDamselList() {
        return this.damselList;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    @Override
    public void setKnightName(String knightName) {
        this.knightName = knightName;
    }

    @Override
    public void embarkOnQuest() {
        this.quest.embark();
    }

    @Override
    public void appearingOnTheScene() {
        System.out.println("I am " + this.knightName + ". But I have the reasons.\n" +
                "My sword is only " + this. sword.getLength() + " feets long.\n");
    }

    @Override
    public void lastScene() {
        System.out.println("Coward still alive");
    }
}

package ua.levelup.Anno.Heroes;

import ua.levelup.Anno.Quests.Quest;

public abstract class GoodGuy extends LivingBeing {
    private Quest quest;

    public GoodGuy(){
        super();
        this.quest = null;
    }

    public GoodGuy(Quest quest, int power){
        super(power);
        this.quest = quest;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public abstract void embarkOnQuest();
}

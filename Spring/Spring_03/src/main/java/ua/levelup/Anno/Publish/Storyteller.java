package ua.levelup.Anno.Publish;

import ua.levelup.XML.Heroes.GoodGuy;

public class Storyteller {
    private GoodGuy hero;

    public Storyteller() {
        this.hero = null;
    }

    public Storyteller(GoodGuy hero) {
        this.hero = hero;
    }

    public GoodGuy getHero() {
        return hero;
    }

    public void setHero(GoodGuy hero) {
        this.hero = hero;
    }

    public void tellTheStory() {
        System.out.println("Once upon a time...\n");
        this.hero.embarkOnQuest();
        System.out.println("And then they lived long and were very happy...\n");

    }
}

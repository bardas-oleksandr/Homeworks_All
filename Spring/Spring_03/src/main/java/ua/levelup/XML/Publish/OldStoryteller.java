package ua.levelup.XML.Publish;

import ua.levelup.XML.Heroes.GoodGuy;

import java.util.Map;

public class OldStoryteller {
    private Map<String, GoodGuy> storyMap;

    public OldStoryteller() {
        this.storyMap = null;
    }

    public OldStoryteller(Map<String, GoodGuy> storyMap) {
        this.storyMap = storyMap;
    }

    public Map<String, GoodGuy> getStoryMap() {
        return storyMap;
    }

    public void setStoryMap(Map<String, GoodGuy> storyMap) {
        this.storyMap = storyMap;
    }

    public void tellAllTheStories() {
        int counter = 0;
        for (Map.Entry<String, GoodGuy> entry : this.storyMap.entrySet()) {
            System.out.println("Story # " + counter++ + "\n");
            System.out.println("Once upon a time...\n");
            entry.getValue().embarkOnQuest();
            System.out.println("And then they lived long and were very happy...\n");
        }
    }
}

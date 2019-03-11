package ua.levelup.Anno.Publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.levelup.XML.Heroes.GoodGuy;

import javax.annotation.Resource;
import java.util.Map;

@Service("oldMan")
public class OldStoryteller {

    private Map<String, GoodGuy> map;

    public OldStoryteller() {
        this.map = null;
    }

    public OldStoryteller(Map<String, GoodGuy> map) {
        this.map = map;
    }

    public Map<String, GoodGuy> getMap() {
        return map;
    }

    @Autowired
    public void setMap(Map<String, GoodGuy> map) {
        this.map = map;
    }

    public void tellAllTheStories() {
        int counter=0;
        for (Map.Entry<String, GoodGuy> entry : this.map.entrySet()) {
            System.out.println("Story # " + counter++ + "\n");
            System.out.println("Once upon a time...\n");
            entry.getValue().embarkOnQuest();
            System.out.println("And then they lived long and were very happy...\n");
        }
    }
}

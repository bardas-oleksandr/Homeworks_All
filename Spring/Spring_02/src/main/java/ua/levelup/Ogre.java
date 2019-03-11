package ua.levelup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("monster")
public class Ogre extends Monster {
    public Ogre(){
        super();
    }

    //Экземпляр бина будет создан даже если его не запрашивать
    @Autowired
    public Ogre(@Value("10")int power) {
        super(power);
        System.out.println("Ogre was created.\n");
    }

    @Override
    void doSomethingBad() {
        System.out.println("I am ogre. I will eat everyone in the village.\n");
    }
}

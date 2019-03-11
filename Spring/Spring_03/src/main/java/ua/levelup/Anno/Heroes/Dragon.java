package ua.levelup.Anno.Heroes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("bigDragon")
public class Dragon extends Monster {
    public Dragon(){
        super();
    }

    @Autowired
    public Dragon(@Value("10")int power){
        super(power);
    }

    @Override
    public void doSomethingBad() {
        System.out.println("I am dragon. I am gonna burn this village.\n");
    }

    @Override
    public void die() {
        System.out.println("Dragon is died. He lost all his heads.\n");
        this.setAlive(false);
    }
}

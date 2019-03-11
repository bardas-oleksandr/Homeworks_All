package ua.levelup.aspectj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("bigDragon")
public class Dragon extends AbstractWarrior {
    @Autowired
    public Dragon(@Value("10") int power) {
        super(power);
    }

    public void steal() {
        System.out.println("The dragon comes and steals the princess");
    }

    public void eat() {
        System.out.println("Dragon has eaten the knight");
    }
}

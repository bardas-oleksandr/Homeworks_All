package ua.levelup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("knight")
public class Knight extends Hero{
    public Knight(){
        super();
    }

    //Вообще по задумке при создании бина knight, он должен связываться с бином dragon
    //Но родитель бина knight - класс Hero имеет метод @Autowired setMonster
    //и в него передается бин ogre. Поэтому по итогу рыцарь все же дерется с огром.
    @Autowired
    public Knight(@Value("11")int power, @Value("#{dragon}")Monster monster) {
        super(power, monster);
        System.out.println("I am knight. My enemy is dragon\n");
    }

    @Override
    void makeTheFight() {
        this.getMonster().doSomethingBad();
        System.out.println("I am knight. And I am not gonna let you do it.\n");
        if(this.getMonster().getPower() < super.getPower()){
            System.out.println("The monster is dead.\n");
            this.getMonster().die();
        }else{
            System.out.println("Oops. The monster is bigger than i've expected.\n");
            this.die();
        }
    }
}

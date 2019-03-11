package Problem_01.Warrior;

import Problem_01.Armor.Hauberk;
import Problem_01.Weapon.Bow;

//Класс Тролль
public class Troll extends Warrior {
    public Troll(){
        super("TROLL", new Bow(), new Hauberk(),70,7);
    }

    public void act(){
        System.out.println("Acting like troll");
    }
}

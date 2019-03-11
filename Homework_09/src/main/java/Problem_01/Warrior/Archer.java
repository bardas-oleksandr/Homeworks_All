package Problem_01.Warrior;

import Problem_01.Armor.Hauberk;
import Problem_01.Weapon.Bow;

//Класс Лучник
public class Archer extends Warrior {
    public Archer(){
        super("ARCHER", new Bow(), new Hauberk(),70,7);
    }

    public void act(){
        System.out.println("Acting like archer");
    }
}
package Problem_01.Warrior;

import Problem_01.Armor.Armor;
import Problem_01.Weapon.Sword;

//Класс Орк
public class Orc extends Warrior {
    public Orc(){
        super("ORC", new Sword(), new Armor(),100,10);
    }

    public void act(){
        System.out.println("Acting like orc");
    }
}

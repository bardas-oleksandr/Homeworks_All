package Problem_01.Warrior;

import Problem_01.Armor.Shield;
import Problem_01.Weapon.Spear;

//Класс Пехотинец
public class Footman extends Warrior {
    public Footman(){
        super("FOOTMAN", new Spear(), new Shield(),80,6);
    }

    public void act(){
        System.out.println("Acting like footman");
    }
}
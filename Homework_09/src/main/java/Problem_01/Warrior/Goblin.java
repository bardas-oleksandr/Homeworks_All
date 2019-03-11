package Problem_01.Warrior;

import Problem_01.Armor.Shield;
import Problem_01.Weapon.Spear;

//Класс Гоблин
public class Goblin extends Warrior {
    public Goblin(){
        super("GOBLIN", new Spear(), new Shield(),80,6);
    }

    public void act(){
        System.out.println("Acting like goblin");
    }
}

package Problem_01.Warrior;

import Problem_01.Armor.Armor;
import Problem_01.Weapon.Sword;

//Класс Рыцарь
public class Knight extends Warrior {
    public Knight(){
        super("KNIGHT", new Sword(), new Armor(),100,10);
    }

    public void act(){
        System.out.println("Acting like knight");
    }
}
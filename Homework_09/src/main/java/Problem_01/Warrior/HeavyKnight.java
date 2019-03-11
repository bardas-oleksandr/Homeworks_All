package Problem_01.Warrior;

import Problem_01.Armor.Armor;
import Problem_01.Weapon.Mace;

//Класс Тяжело-воорожуенный рыцарь
public class HeavyKnight extends Warrior {
    public HeavyKnight(){
        super("HEAVY-ARMED KNIGHT", new Mace(), new Armor(),100,9);
    }

    public void act(){
        System.out.println("Acting like heavy-armed knight");
    }
}
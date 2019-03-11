package Problem_01.Warrior;

import Problem_01.Armor.Armor;
import Problem_01.Weapon.Mace;

//Класс Пещерный тролль
public class CaveTroll extends Warrior {
    public CaveTroll(){
        super("CAVE TROLL", new Mace(), new Armor(),100,9);
    }

    public void act(){
        System.out.println("Acting like cave troll");
    }
}

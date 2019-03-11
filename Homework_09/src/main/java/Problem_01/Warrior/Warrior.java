package Problem_01.Warrior;

import Problem_01.Armor.Armor;
import Problem_01.Weapon.Weapon;

abstract public class Warrior {
    private String name;
    private Weapon weapon;
    private Armor armor;
    private int health;
    private int velocity;

    public Warrior(String name, Weapon weapon, Armor armor, int health, int velocity){
        this.name=name;
        this.weapon=weapon;
        this.armor=armor;
        this.health=health;
        this.velocity=velocity;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder(this.name);
        res.append("\nHealth: " + this.health);
        res.append("\nVelocity: " + this.velocity);
        res.append("\nWeapon: " + this.weapon);
        res.append("\nArmor: " + this.armor);
        return res.toString();
    }

    abstract void act();    //Каждый тип воина может вести себя по-особому
}

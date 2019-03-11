package ua.levelup.XML.CreateAbstract;

public class Weapon {
    private String name;
    private int power;

    Weapon(String name, int power){
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString(){
        return "Weapon: " + this.name + "\tpower: " + this.power;
    }
}

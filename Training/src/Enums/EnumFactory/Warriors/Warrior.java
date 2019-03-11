package Enums.EnumFactory.Warriors;

abstract public class Warrior {
    private String name;

    public Warrior(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    abstract public void act();
}

package ua.levelup.Anno.Heroes;

public abstract class Monster extends LivingBeing {
    public Monster(){
        super();
    }

    public Monster(int power){
        super(power);
    }

    public abstract void doSomethingBad();
}

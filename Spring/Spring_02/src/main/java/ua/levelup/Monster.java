package ua.levelup;

abstract public class Monster extends LivingBeing{
    public Monster(){
        super();
    }

    public Monster(int power) {
        super(power);
    }

    abstract void doSomethingBad();
}

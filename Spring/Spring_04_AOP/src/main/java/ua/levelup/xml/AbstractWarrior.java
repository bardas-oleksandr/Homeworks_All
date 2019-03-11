package ua.levelup.xml;

public abstract class AbstractWarrior{
    private int power;

    public AbstractWarrior(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

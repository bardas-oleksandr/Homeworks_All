package ua.levelup.Anno.Heroes;

public abstract class LivingBeing {
    private boolean alive;
    private int power;

    public LivingBeing(){
        this.alive = true;
        this.power = 0;
    }

    public LivingBeing(int power){
        this.alive = true;
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract void die();
}

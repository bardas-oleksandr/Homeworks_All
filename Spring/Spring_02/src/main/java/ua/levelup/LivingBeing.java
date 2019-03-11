package ua.levelup;

public class LivingBeing {
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

    public void die() {
        this.alive = false;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

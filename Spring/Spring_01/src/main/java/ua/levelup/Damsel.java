package ua.levelup;

import java.util.Random;

public class Damsel {
    private int titsSize;

    public Damsel(){
        this.titsSize = new Random().nextInt(6);
    }

    public void speak(){
        System.out.println(this);
    }

    public void setTitsSize(int titsSize){
        this.titsSize = titsSize;
    }

    public void appear(){
        System.out.println("New damsel is here\n");
    }

    public void die(){
        System.out.println("I am dying\n");
    }

    @Override
    public String toString(){
        return "The size of my tits is " + this.titsSize + "\n";
    }
}

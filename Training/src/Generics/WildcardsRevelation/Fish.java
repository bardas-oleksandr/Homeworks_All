package Generics.WildcardsRevelation;

public class Fish extends Animal {
    private boolean saltWater;

    public Fish(String name, boolean saltWater){
        super(name, true);
        this.saltWater = saltWater;
    }

    @Override
    public String toString(){
        return super.toString() + "\tSalt water fish: " + this.saltWater;
    }

    public boolean isSaltWaterFish(){
        return this.saltWater;
    }
}

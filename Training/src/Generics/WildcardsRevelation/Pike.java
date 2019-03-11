package Generics.WildcardsRevelation;

public class Pike extends Fish {
    private boolean isHungry;

    public Pike(String name, boolean isHungry){
        super(name, false);
        this.isHungry = isHungry;
    }

    @Override
    public String toString(){
        return super.toString() + "\tIs hungry: " + this.isHungry;
    }

    public boolean isHungry() {
        return this.isHungry;
    }
}

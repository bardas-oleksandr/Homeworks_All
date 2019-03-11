package Enums.EnumFactory.Warriors;

public class Footman extends HumanWarrior {
    public Footman() {
        super("Footman");
    }

    @Override
    public void act() {
        System.out.println("Name: " + this.getName());
    }
}

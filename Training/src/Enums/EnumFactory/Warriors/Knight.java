package Enums.EnumFactory.Warriors;

public class Knight extends HumanWarrior {
    public Knight() {
        super("Knight");
    }

    @Override
    public void act() {
        System.out.println("Name: " + this.getName());
    }
}

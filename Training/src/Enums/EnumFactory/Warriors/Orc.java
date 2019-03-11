package Enums.EnumFactory.Warriors;

public class Orc extends OrcWarrior {
    public Orc() {
        super("Orc");
    }

    @Override
    public void act() {
        System.out.println("Name: " + this.getName());
    }
}

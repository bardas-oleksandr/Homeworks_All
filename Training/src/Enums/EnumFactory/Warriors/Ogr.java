package Enums.EnumFactory.Warriors;

public class Ogr extends OrcWarrior {
    public Ogr() {
        super("Ogr");
    }

    @Override
    public void act() {
        System.out.println("Name: " + this.getName());
    }
}

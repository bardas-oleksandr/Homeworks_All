package ua.levelup.XML.CreateAbstract;

public abstract class Archer implements Warrior {
    private Weapon weapon;

    @Override
    public void attack() {
        System.out.println(getWeapon());
    }
}

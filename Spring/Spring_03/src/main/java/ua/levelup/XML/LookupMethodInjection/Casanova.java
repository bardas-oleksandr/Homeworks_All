package ua.levelup.XML.LookupMethodInjection;

public class Casanova implements Guy {
    private Damsel damsel;

    public void setDamsel(Damsel damsel){
        this.damsel = damsel;
    }

    @Override
    public Damsel getDamsel() {
        return this.damsel;
    }

    @Override
    public void findDamsel() {
        System.out.println("Hello, lady. What is your name?");
        getDamsel().speak();
    }
}

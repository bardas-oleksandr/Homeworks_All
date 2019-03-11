package ua.levelup.XML.LookupMethodInjection;

import java.util.Random;

public class Damsel {
    private String name;
    private static final String[] names;
    static {
        names = new String[]{"Kathy","Lucie","Julia","Mary","Sophie", "Monica", "Mila", "Rachel"};
    }

    public Damsel(){
        this.name = this.names[new Random().nextInt(5)];
    }

    public void speak(){
        System.out.println("I am " + this.name + "\n");
    }
}

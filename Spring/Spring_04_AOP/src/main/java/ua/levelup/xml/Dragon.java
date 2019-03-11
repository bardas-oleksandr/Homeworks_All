package ua.levelup.xml;

public class Dragon extends AbstractWarrior {
    public Dragon(int power) {
        super(power);
    }

    public void steal() {
        System.out.println("The dragon comes and steals the princess");
    }

    public void eat() {
        System.out.println("Dragon has eaten the knight");
    }
}

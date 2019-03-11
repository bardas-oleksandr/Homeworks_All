package ua.levelup.Anno.Heroes;

public class Ogre extends Monster {
    public Ogre(){
        super();
    }

    public Ogre(int power){
        super(power);
    }

    @Override
    public void doSomethingBad() {
        System.out.println("I am ogre. I am gonna eat everyone in this village.\n");
    }

    @Override
    public void die() {
        System.out.println("The ogre is dead.\n");
        this.setAlive(false);
    }
}

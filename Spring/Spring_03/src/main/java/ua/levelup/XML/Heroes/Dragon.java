package ua.levelup.XML.Heroes;

public class Dragon extends Monster {
    public Dragon(){
        super();
    }

    public Dragon(int power){
        super(power);
    }

    @Override
    public void doSomethingBad() {
        System.out.println("I am dragon. I am gonna burn this village.\n");
    }

    @Override
    public void die() {
        System.out.println("Dragon is died. He lost all his heads.\n");
        this.setAlive(false);
    }
}

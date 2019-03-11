package ua.levelup.xml;

public class Knight extends AbstractWarrior {
    private Dragon dragon;

    public Knight(Dragon dragon, int power) {
        super(power);
        this.dragon = dragon;
    }

    public void enterTheCity() {
        System.out.println("The brave knight is entering the city");
    }

    public void embark(){
        if(this.dragon.getPower() < this.getPower()){
            System.out.println("The dragon is dead.");
        }else{
            System.out.println("Oops. Why didn't anybody said me that dragon has so many heads?");
            throw new DeadKnightException();
        }
    }
}

package Problem_01.Weapon;

public class Weapon {
    private String name;
    private int power;
    private int distance;
    private int velocity;

    public Weapon(String name, int power, int distance, int velocity){
        this.name=name;
        this.power=power;
        this.distance=distance;
        this.velocity=velocity;
    }

    @Override
    public String toString(){
        return new String(this.name +
                "\t\t\tpower: " + this.power +
                "\tdistance: " + this.distance +
                "\tvelocity: " + this.velocity );
    }
}

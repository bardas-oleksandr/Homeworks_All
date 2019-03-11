package Problem_01.Armor;

public class Armor {
    private String name;
    private int defenceLevel;

    public Armor(String name, int defenceLevel){
        this.name=name;
        this.defenceLevel=defenceLevel;
    }

    public Armor(){
        this.name= "Knight armor";
        this.defenceLevel=10;
    }

    @Override
    public String toString(){
        return new String(this.name +
        "\t\tdefence level: " + this.defenceLevel);
    }
}

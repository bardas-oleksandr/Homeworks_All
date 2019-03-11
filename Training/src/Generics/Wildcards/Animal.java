package Generics.Wildcards;

public class Animal {
    private String name;
    private boolean waterAnimal;

    public Animal(String name, boolean waterAnimal){
        this.name = name;
        this.waterAnimal = waterAnimal;
    }

    @Override
    public String toString(){
        return this.name + "\tWater animal: " + this.waterAnimal;
    }

    public boolean isWaterAnimal(){
        return this.waterAnimal;
    }
}

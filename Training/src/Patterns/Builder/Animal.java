package Patterns.Builder;

public class Animal {
    private String species;
    private int weight;
    private int power;

    public static Builder create(){
        return new Animal().new Builder();
    }

    //Нестатический Билдер
    class Builder{
        public Builder isCalled(String species){
            Animal.this.species = species;
            return this;
        }
        public Builder hasWeight(int weight){
            Animal.this.weight = weight;
            return this;
        }
        public Builder hasPower(int power){
            Animal.this.power = power;
            return this;
        }
        public Animal getAnimal(){
            return Animal.this;
        }
    }

    @Override
    public String toString(){
        return new String(this.species + "\tpower: " + this.power + "\tweight: " + this.weight);
    }
}

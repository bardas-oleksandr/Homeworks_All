package Patterns.Builder;

public class Fish {
    private String species;
    private int length;
    private int weight;

    private Fish(Builder instance){
        this.species = instance.species;
        this.length = instance.length;
        this.weight = instance.weight;
    }

    //Статический класс
    static class Builder{
        private String species;
        private int length;
        private int weight;

        public Builder isCalled(String species){
            this.species = species;
            return this;
        }

        public Builder hasLength(int length){
            this.length = length;
            return this;
        }

        public Builder hasWeight(int weight){
            this.weight = weight;
            return this;
        }

        public Fish create(){
            return new Fish(this);
        }
    }

}

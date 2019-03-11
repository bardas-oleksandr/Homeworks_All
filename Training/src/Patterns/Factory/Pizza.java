package Patterns.Factory;

public class Pizza {
    private String cheese;
    private int size;

    public Pizza(String cheese, int size){
        this.cheese = cheese;
        this.size = size;
    }

    @Override
    public String toString(){
        return new String("Cheese: " + this.cheese + "\tSize: " + this.size);
    }
}

package Serialization.Serialization_Standard;

import java.io.Serializable;

public class Animal implements Serializable {
    private int weight;
    private int power;
    private int age;

    public Animal(int weight, int power, int age){
        this.weight = weight;
        this.power = power;
        this.age = age;
    }

    @Override
    public String toString(){
        return "weight: " + weight + "\tpower: " + power + "\tage: " + age;
    }
}
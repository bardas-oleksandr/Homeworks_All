package Serialization.Extern;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Animal implements Externalizable {
    private int weight;
    private int power;
    private int age;

    //Для использования Externalizable обязательным является наличие конструктора по умолчанию
    //Это связано с тем что при чтении объекта будет вызван метод readExternal самого объекта
    //А для этого надо чтобы объект уже был как-то создан.
    public Animal(){
        this.weight = 0;
        this.power = 0;
        this.age = 0;
    }

    public Animal(int weight, int power, int age){
        this.weight = weight;
        this.power = power;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal() was called");
        out.writeInt(this.weight);
        out.writeInt(this.power);
        out.writeInt(this.age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal() was called");
        this.weight = in.readInt();
        this.power = in.readInt();
        this.age = in.readInt();
    }

    @Override
    public String toString(){
        return "weight: " + weight + "\tpower: " + power + "\tage: " + age;
    }
}

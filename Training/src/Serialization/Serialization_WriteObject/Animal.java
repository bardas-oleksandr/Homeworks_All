package Serialization.Serialization_WriteObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Animal implements Serializable {
    private int weight;
    private int power;
    private int age;

    //При переопределении стандартной сериализации Serializable, используется тот же принип что и
    //при использовании Externalizable. Поэтому обязательным является наличие конструктора по умолчанию.
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
    public String toString(){
        return "weight: " + weight + "\tpower: " + power + "\tage: " + age;
    }

    //@Override //Так писать нельзя так как формально мы ничего не переопределяем
    //Ведь Serilizable - это просто интерфейс-маркер, не имеющий методов
    private void writeObject(ObjectOutputStream stream) throws IOException{
        System.out.println("writeObject() was called");
        stream.writeInt(this.weight);
        stream.writeInt(this.power);
        stream.writeInt(this.age);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        System.out.println("readObject() was called");
        this.weight = stream.readInt();
        this.power = stream.readInt();
        this.age = stream.readInt();
    }
}
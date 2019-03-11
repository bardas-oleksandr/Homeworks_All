package Serialization.Serialization_Standard;

import Interfaces.IProblem;

import java.io.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Animal original = new Animal(200, 30, 20);
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("files/animal_1.txt"))){
            output.writeObject(original);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Animal clone = null;
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("files/animal_1.txt"))){
            clone = (Animal)input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        System.out.println(original);
        System.out.println(clone);
    }
}

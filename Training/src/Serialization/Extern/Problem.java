package Serialization.Extern;

import Interfaces.IProblem;

import java.io.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Animal first = new Animal(300, 100, 10);
        Animal second = null;

        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("files/animal.txt"))) {
            output.writeObject(first);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("files/animal.txt"))) {
            second = (Animal)input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(first);
        System.out.println(second);

//        try {
//            ObjectOutput output = new ObjectOutputStream(new FileOutputStream("data.txt"));
//            //output.writeInt(10);
//            output.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (ObjectInput input = new ObjectInputStream(new FileInputStream("data.txt"))) {
//            //int x = input.readInt();
//            input.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        byte[] arr = new String("Hello").getBytes();
//        try (ObjectInput input = new ObjectInputStream(new ByteArrayInputStream(arr))) {
//            char ch = input.readChar();
//            System.out.println("ch=" + ch);
//        }catch(IOException e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
    }
}

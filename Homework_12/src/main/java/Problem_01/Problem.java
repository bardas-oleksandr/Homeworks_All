package Problem_01;

import Interfaces.IProblem;

import java.util.Random;

public class Problem implements IProblem {
    public void solve() {
        final int COUNT = 5;

        System.out.println("====================INTEGER====================");
        GenericArray<Integer> first = new GenericArray<>();
        for(int i = 0; i < COUNT; i++){
            first.put(i);
        }
        for(int i = 0; i < COUNT; i++){
            System.out.println("GenericArray<Integer>[" + i + "]=" + first.get(i));
        }

        System.out.println("====================DOUBLE=====================");
        GenericArray<Double> second = new GenericArray<>();
        Random rnd = new Random();
        for(int i = 0; i < COUNT; i++){
            second.put(rnd.nextDouble());
        }
        for(int i = 0; i < COUNT; i++){
            System.out.println("GenericArray<Double>[" + i + "]=" + second.get(i));
        }

        System.out.println("====================STRING=====================");
        GenericArray<String> third = new GenericArray<>();
        third.put("This");
        third.put("is");
        third.put("String");
        third.put("array");
        third.put("!!!");
        for(int i = 0; i < COUNT; i++){
            System.out.println("GenericArray<String>[" + i + "]=" + third.get(i));
        }
    }
}

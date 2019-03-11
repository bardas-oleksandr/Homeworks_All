package Problem_03;

import Interfaces.IProblem;
import Problem_01.GenericArray;

import java.util.Random;

public class Problem implements IProblem {
    public void solve() {
        final int COUNT = 20;

//        NewGenericArray<Integer> second = new NewGenericArray<>();
//        Random rnd = new Random();
//        for(int i = 0; i < COUNT; i++){
//            second.put(rnd.nextInt(50));
//        }
//        System.out.println("GenericArray<Integer>");
//        for(int i = 0; i < COUNT; i++){
//            System.out.print(second.get(i) + "\t");
//        }
//        System.out.print("\n");
//
//        System.out.println("Maximum element: " + second.max());
//        System.out.println("Minimum element: " + second.min());
//        System.out.println("Sum of array elements: " + second.sum());
//
//        second.sort();
//        System.out.println("Sorted GenericArray<Integer>");
//        second.show();

        GenericArray<Integer> second = new GenericArray<>();
        Random rnd = new Random();
        for(int i = 0; i < COUNT; i++){
            second.put(rnd.nextInt(50));
        }
        System.out.println("GenericArray<Integer>");
        second.show();

        System.out.println("Maximum element: " + Processor.max(second));
        System.out.println("Minimum element: " + Processor.min(second));
        System.out.println("Sum of array elements: " + Processor.sum(second));

        Processor.sort(second);
        System.out.println("Sorted GenericArray<Integer>");
        second.show();
    }
}

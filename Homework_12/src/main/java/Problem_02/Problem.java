package Problem_02;

import Interfaces.IProblem;

import java.util.Random;

public class Problem implements IProblem {
    public void solve() {
        final int SIZE = 5;
        final int MAX = 100;
        final int MAX_THREADS_COUNT = 100;
        final int MIN_THREADS_COUNT = 0;

        System.out.print("Set threads count (1..100):");
        int threadsCount = Interfaces.IConsole.getIntegerBounded(MIN_THREADS_COUNT,MAX_THREADS_COUNT);

        System.out.println("====================INTEGER ARRAY=========================");
        Integer[][] arr = new Integer[SIZE][SIZE];
        Random rnd = new Random();
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                arr[i][j] = rnd.nextInt(MAX);
                System.out.print(arr[i][j] + "\t");
            }
            System.out.print("\n");
        }
        double result = calculate(arr, threadsCount);
        System.out.println("Sum of integer array is equal " + result);

        System.out.println("====================DOUBLE ARRAY==========================");
        Double[][] arr1 = new Double[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                arr1[i][j] = rnd.nextDouble();
                System.out.print(arr1[i][j] + "\t");
            }
            System.out.print("\n");
        }
        result = calculate(arr1, threadsCount);
        System.out.println("Sum of double array is equal " + result);
    }

    //Метод может находить сумму двухмерных зубатых массивов
    public final <T extends Number> double calculate(T[][] arr, int threadsCount){
        double result = 0;
        Adder<?>[] adders = new Adder<?>[threadsCount];
        for(int i = 0; i < threadsCount; i++){
            adders[i] = new Adder<T>(arr, i, threadsCount);
        }

        for(int i = 0; i < threadsCount; i++){
            try {
                adders[i].join();
                result += adders[i].getResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}

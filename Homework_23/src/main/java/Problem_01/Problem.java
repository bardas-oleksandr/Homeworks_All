package Problem_01;

import Interfaces.IProblem;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Problem implements IProblem {
    @Override
    public void solve() {
        final int SIZE = 100000000;
        final int MIN_THRESHOLD = 1000;
        final int MAX_THRESHOLD = 10000;
        final int STEP = 1000;
        int count = (MAX_THRESHOLD - MIN_THRESHOLD)/STEP + 1;

        double[] data = new double[SIZE];   //Данные для обработки
        Random rnd = new Random();
        System.out.println("Array initializing");
        for(int i = 0; i < SIZE; i++){
            data[i] = rnd.nextDouble();
        }

        Runtime runtime = Runtime.getRuntime();
        int processors = runtime.availableProcessors();

        long[][] results = new long[processors][count];   //Массив результатов продолжительности исполнения

        ForkJoinPool fjp = new ForkJoinPool();

        System.out.println("Calculating");
        for(int i = 0; i < processors; i++){
            for(int j = 0; j < count; j++){
                results[i][j] = - System.currentTimeMillis();
                Sum sum = new Sum(data,0,SIZE,MIN_THRESHOLD + j*STEP);
                double answer = fjp.invoke(sum);
                results[i][j] += System.currentTimeMillis();
            }
        }

        System.out.println("Results");
        for(int i = 0; i < processors; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(results[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
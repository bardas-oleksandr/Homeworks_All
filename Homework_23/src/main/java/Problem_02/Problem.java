package Problem_02;

import Interfaces.IProblem;

import java.util.Random;
import java.util.concurrent.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        final int N = 6;

        int[][] arrays = new int[N][N];

        Random rnd = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arrays[i][j] = 1 + rnd.nextInt(99);
                System.out.print(arrays[i][j] + "\t");
            }
            System.out.println();
        }

        int[] results = new int[N];

        Phaser phaser = new Phaser(1);
        //phaser.register();

        Semaphore semaphoreFirst = new Semaphore(N - 2);
        Semaphore semaphoreSecond = new Semaphore(N / 2);

        ExecutorService executor = Executors.newCachedThreadPool();

        Exchanger<Integer> exchanger = null;
        for (int i = 0; i < N; i++) {
            //Initializing exchanger for each pair of Processor-objects
            if (i % 2 == 0) {
                exchanger = new Exchanger<>();
            }
            executor.execute(new Processor(arrays, i, results, exchanger, semaphoreFirst, semaphoreSecond, phaser));

            //new Processor(arrays, i, results, exchanger, semaphoreFirst, semaphoreSecond, phaser);
        }

        executor.shutdown();

        phaser.arriveAndAwaitAdvance(); //Ждем результатов первой фазы
        //Теперь можем поискать три массива с максимальными суммами
        System.out.println("=======================PHASE 1===============================");
        System.out.println("Three arrays with the biggest total sum");
        final int COUNT = 3;
        showSelected(arrays, results, COUNT, true,0,1);
        phaser.arriveAndAwaitAdvance();    //Даем знать потокам что результаты первой фазы уже использованы и больше не нужны


        phaser.arriveAndAwaitAdvance(); //Ждем результатов второй фазы
        //Теперь можем поискать три массива с максимальными суммами
        System.out.println("=======================PHASE 2===============================");
        System.out.println("Three even arrays with the smallest average value");
        showSelected(arrays, results, COUNT, false,0,2);
        phaser.arriveAndAwaitAdvance();    //Даем знать потокам что результаты второй фазы уже использованы и больше не нужны

        phaser.arriveAndAwaitAdvance(); //Ждем результатов третьей фазы
        System.out.println("=======================PHASE 3===============================");
        System.out.println("One array with the smallest value");
        showSelected(arrays, results, 1, false,0,1);
        phaser.arriveAndAwaitAdvance();    //Даем знать потокам что результаты третьей фазы уже использованы и больше не нужны

        phaser.arriveAndAwaitAdvance(); //Ждем результатов четвертой фазы
        System.out.println("=======================PHASE 4===============================");
        System.out.println("ALL ARRAYS");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arrays[i][j] + "\t");
            }
            System.out.println();
        }
        phaser.arriveAndDeregister();

        for (int i = 0; i < N; i++) {
            try {
                executor.awaitTermination(24, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void showSelected(int[][] source, int[] markers, int count, boolean max, int start, int step) {
        int[] indices = new int[count];
        int koef = max ? 1 : -1;
        //Делаем три прохода. За каждый проход находим индекс одного массива для включения в итог
        for (int i = 0; i < count; i++) {
            int iExtreme = 0;
            //Определим начальный индекс экстремального элемента (берем первый попавшийся элемент, который еще не включен в итог)
            for(int j = start; j <= markers.length; j+=step){
                boolean newIndex = true;
                for(int y = 0; y < i; y++){
                    if(indices[y] == j){
                        newIndex = false;
                        break;
                    }
                }
                if(newIndex){
                    iExtreme = j;
                    break;
                }
            }

            for (int j = start; j < markers.length; j+=step) {
                if (markers[j] * koef > markers[iExtreme] * koef) {
                    boolean newIndex = true;
                    for (int y = 0; y < i; y++) {
                        if (indices[y] == j) {
                            newIndex = false;
                            break;
                        }
                    }
                    if (newIndex) {
                        iExtreme = j;
                    }
                }
            }
            indices[i] = iExtreme;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Array " + (i + 1));
            for (int j = 0; j < markers.length; j++) {
                System.out.print(source[indices[i]][j] + "\t");
            }
            System.out.println("\nResult: " + markers[indices[i]]);
        }
    }
}

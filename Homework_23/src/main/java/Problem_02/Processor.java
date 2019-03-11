package Problem_02;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

public class Processor extends Thread {
    private int[][] data;
    private int index;
    private int[] results;
    private Exchanger<Integer> exchanger;
    private Semaphore semaphoreFirst;
    private Semaphore semaphoreSecond;
    private Phaser phaser;

    Processor(int[][] data, int index, int[] results, Exchanger<Integer> exchanger,
              Semaphore semaphoreFirst, Semaphore semaphoreSecond, Phaser phaser) {
        super();
        this.data = data;
        this.index = index;
        this.results = results;
        this.exchanger = exchanger;
        this.semaphoreFirst = semaphoreFirst;
        this.semaphoreSecond = semaphoreSecond;
        this.phaser = phaser;
        this.phaser.register();
        //super.start();    //Если поток будет управлять Executor, он сам вызовет этот метод
    }

    @Override
    public void run() {
        //Phase I
        try {
            this.semaphoreFirst.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.results[this.index] = 0;
        for (int i = 0; i < this.data[this.index].length; i++) {
            this.results[index] += this.data[this.index][i];
        }

        this.semaphoreFirst.release();
        this.phaser.arriveAndAwaitAdvance();   //Даем сигнал о завершении первого этапа. Сигнал будет использован в основном потоке
        this.phaser.arriveAndAwaitAdvance();    //Ждем пока основной поток выберет из полученных результатов нужные ему

        //Phase II
        this.results[this.index] = 0;
        //Считаем только нечетные массивы
        if (index % 2 == 0) {
            for (int i = 0; i < this.data[this.index].length; i++) {
                this.results[index] += this.data[this.index][i];
            }
            this.results[index] /= this.data[this.index].length;
        }

        this.phaser.arriveAndAwaitAdvance();   //Даем сигнал о завершении второго этапа.
        this.phaser.arriveAndAwaitAdvance();    //Ждем пока основной поток выберет из полученных результатов нужные ему
        //Phase III
        try {
            this.semaphoreSecond.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.results[this.index] = 0;
        int imin = 0;
        for (int i = 0; i < this.data[this.index].length; i++) {
            if(this.data[this.index][i] < this.data[this.index][imin]){
                imin = i;
            }
        }
        this.results[index] = this.data[this.index][imin];

        this.semaphoreSecond.release();
        this.phaser.arriveAndAwaitAdvance();;   //Даем сигнал о завершении третьего этапа.
        this.phaser.arriveAndAwaitAdvance();    //Ждем пока основной поток выберет из полученных результатов нужные ему
        //Phase IV
        int next = -1;
        int prev = 0;
        for (int i = 0; i < this.data[this.index].length; i++) {
            if(this.index%2 != this.data[this.index][i]%2){
                if(next != 0){
                    try {
                        next = this.exchanger.exchange(this.data[this.index][i]);
                        if(next != 0){
                            prev = next;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.data[this.index][i] = prev;
            }
        }
        if(next != 0){
            try {
                this.exchanger.exchange(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        this.phaser.arriveAndAwaitAdvance();   //Даем сигнал о завершении четвертого этапа.
        this.phaser.arriveAndDeregister();
    }
}

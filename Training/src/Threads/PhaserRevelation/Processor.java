package Threads.PhaserRevelation;

import java.util.concurrent.Phaser;

public class Processor extends Thread {
    private Phaser phaser;
    private int[][] data;
    private int index;
    private int[] results;

    Processor(Phaser phaser, int[][] data, int index, int[] results){
        super();
        this.phaser = phaser;
        phaser.register();
        this.data = data;
        this.index = index;
        this.results = results;
        super.start();
    }

    @Override
    public void run(){
        for (int i = 0; i < this.data[this.index].length; i++) {
            this.results[this.index] += this.data[this.index][i];
        }
        phaser.arriveAndAwaitAdvance();

        phaser.arriveAndDeregister();
    }
}

//ЧТО ПОКАЗЫВАЕТ ПРИМЕР.
//Пример показывает что методы arrive() и arriveAndAwaitAdvance() ведут себя не так как хотелось бы.
//В этом примере нам нужно чтобы потоки класса Processor проходили первую фазу без остановки,
//а основной поток чтобы ожидал окончания первой фазы всех потоков класса Processor.
//Логично было бы использовать метод arrive() в потоках класса Processor и метод arriveAndAwaitAdvance()
//в основном потоке. Но так работать не будет.
//В ЧЕМ ПРИЧИНА ПРОБЛЕМЫ
//Phaser не различает фазы по номерам. Поэтому когде в потоке процессора сначала срабатывает
//метод arrive(), а потом метод arriveAndDeregister(), фазер считает что это два учасника процесса
//прошли одну и ту же фазу
//То есть ему без разницы какая именно фаза выполнена. Он просто считает их количество.
//И если кто-то через arriveAndAwaitAdvance() ждет чтобы все учасники процесса выполнили, например,
//фазу номер 1, то это ожидание может окончится раньше ожидаемого срока если какой-то из потоков
//выполнит фазу номер 2, 3 или любую другую.

package Threads.PhaserRevelation;

import Interfaces.IProblem;

import java.util.Random;
import java.util.concurrent.Phaser;

public class Problem implements IProblem {
    @Override
    public void solve() {
        final int N = 10;
        Phaser phaser = new Phaser(1);
        int[][] data = new int[N][N];
        Random rnd = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                data[i][j] = rnd.nextInt(100);
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }

        int[] results = new int[N];

        for (int i = 0; i < N; i++) {
            new Processor(phaser,data,i,results);
        }

        System.out.println("Waiting for results");
        phaser.arriveAndAwaitAdvance();
        System.out.println("=================RESULTS========================");
        for (int i = 0; i < N; i++) {
            System.out.println("Array " + (i + 1) + ": " + results[i]);
        }

        phaser.arriveAndDeregister();
    }
}

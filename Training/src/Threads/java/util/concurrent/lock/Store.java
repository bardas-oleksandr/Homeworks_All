package Threads.java.util.concurrent.lock;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Store {
    private int product = 0;
    ReentrantLock locker;
    Condition condition;

    Store() {
        locker = new ReentrantLock(); // создаем блокировку
        condition = locker.newCondition(); // получаем условие, связанное с блокировкой
    }

    public void get() {
        File file;

        Path path = null;

        locker.lock();  //Замыкаем замок
        System.out.println("Consumer has locked the lock");
        try {
            // пока нет доступных товаров на складе, ожидаем
            while (product < 1) {
                Thread.sleep(3000);
                System.out.println("Consumer has unlocked the lock");
                condition.await();  //Эта команда останавливает поток и снимает замок
                System.out.println("Consumer is working again");
            }

            product--;
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);

            // сигнализируем
            condition.signalAll();  //Эта команда будит всех кто ждет по команде await()
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();    //Снимает замок
        }
    }

    public void put() {

        locker.lock();
        System.out.println("Producer has locked the lock");
        try {
// пока на складе 3 товара, ждем освобождения места
            while (product >= 3) {
                System.out.println("Producer has unlocked the lock");
                condition.await();
                System.out.println("Producer is working again");
            }

            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }
}
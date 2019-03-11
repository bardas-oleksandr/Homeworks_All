package Threads.Semaphore;

import Interfaces.IProblem;

import java.util.concurrent.Semaphore;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Semaphore semaphore = new Semaphore(2,true);    //Первый параметр - количество разрешений для
        //выполнения потоков, второй - допуск к выполнению осуществляется согласно порядку "подхода".

        MyThread A = new MyThread(semaphore, "A");
        MyThread B = new MyThread(semaphore, "B");
        MyThread C = new MyThread(semaphore, "C");
        MyThread D = new MyThread(semaphore, "D");

        while(A.isAlive() || B.isAlive() || C.isAlive() || D.isAlive());
    }
}

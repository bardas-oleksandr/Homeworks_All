//ЧТО ПОКАЗЫВАЕТ ЭТОТ ПРИМЕР
//В цклом этот пример повторяет часть примера из package ThreadsGeneralization,
//но также он показывает применение метода interrupt()

package Threads.WaitNotify;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Locker locker = new Locker();
        Process1 p1 = new Process1(locker);
        Process2 p2 = new Process2(locker, p1);
        p1.setProcess(p2);
    }
}

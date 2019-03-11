package Threads.ThreadsBasics;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        System.out.println("EXAMPLE #1");

        Thread thread = Thread.currentThread();
        System.out.println(thread);

        thread.setName("My thread");
        thread.setPriority(Thread.NORM_PRIORITY + 5);

        System.out.println(thread);

        try {
            for (int n = 5; n > 0; n--) {
                System.out.println(n);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException е){
            System.out. println( "Глaвный поток исполнения прерван");
        }


        //Пример по синхронизации
        Q q = new Q();
        new Producer(q);
        new Consumer(q);

        System.out.println("Press Control-C to stop.");

    }
}

//1. Реализовать программу которая будет считать сумму массива в нескольких потоках.
//   Количество потоков (call) и количество элементов массива (N)- вводимые числа.
//   Элементы массива должны генерироваться.
//   Реализовать в программе таймер подсчета времени выполнения.
//   Путем экспериментов определить, оптимальное количество потоков для подсчета суммы.
//  (Оптимальным считается количество, при котором достигается минимальное время просчета).

package Problem_01;

import Dispatcher.Locker;
import java.util.Random;

public class Processor extends Thread {
    private Locker locker;

    public Processor(Locker locker){
        super();
        this.locker = locker;
        super.start();
    }

    @Override
    public void run(){
        final int KOEF = 100000; //Коэффициент для перевода наносекунд в число, удобное для восприятия (0.1 милисекунды)

        //Исходные данные
        System.out.println("Set array dimension (100..300000000): ");
        int N = Interfaces.IConsole.getIntegerBounded(100, 30000000);
        System.out.println("Set maximum threads count (1..100): ");
        int call = Interfaces.IConsole.getIntegerBounded(1, 100);
        System.out.println("Let's go");

        this.locker.Notifying(false);   //Будим диспетчер, которому надо запустить терминатора
        this.locker.Waiting(false);     //Сами ждем пока диспетчер нас не разбудит

        Integer[] mas = new Integer[N]; //Генерируем массив для подсчета
        Random rnd = new Random();
        final int PERIOD = 100000;   //Через каждые 100000 сгенерированных чисел будем проверять не сработал ли терминатор
        for(int i = 1; i <= N/PERIOD+1; i++){
            if(!isInterrupted()){   //Если терминатор не сработал
                int rest = (i*PERIOD) > N? N-((i-1)*PERIOD): PERIOD;
                for(int j = 0; j < rest; j++){
                    mas[(i-1)*PERIOD + j] = new Integer(rnd.nextInt(10));
                }
            }else{
                break;
            }
        }
        label:
        {
            if (!isInterrupted()) {
                long[] results = new long[call];  //Сюда будем записывать продолжительность суммирования для разных вариантов количества потоков
                int imin = 0;

                for (int i = 1; i <= call; i++) { //Для каждого варианта количества потоков от 1 до call проводим отдельный эксперимент на одном и том же массива
                    if (!isInterrupted()) {
                        results[i - 1] = -System.nanoTime();  //Момент начала подсчета суммы массива для варианта i-1
                        Double result = new Double(0);
                        Adder<Integer>[] adders = new Adder[i];  //Массив потоков-сумматоров
                        for (int j = 0; j < i; j++) {
                            adders[j] = new Adder<Integer>(mas, i, j, Thread.NORM_PRIORITY);    //Приоритет выставим на один ниже нормального. За счет этого я расчитываю на то что все потоки для подсчета суммы будут выброшены одновременно
                        }
                        for (int j = 0; j < i; j++) { //Надо дождаться завершения всех потоков
                            try {
                                adders[j].join();
                                result += adders[j].getResult();
                            } catch (InterruptedException e) {
                                break label;
                            }
                        }
                        results[i - 1] += System.nanoTime();  //Момент окончания подсчета суммы массива для варианта i-1
                        if (results[i - 1] < results[imin]) {
                            imin = i - 1;
                        }

                        //Показываем результат по текущему опыту
                        System.out.println("Threads count = " + i);
                        System.out.println("Sum = " + result);
                        System.out.println("Time = " + results[i - 1] / KOEF);
                        System.out.println("============================");
                    } else {
                        break;
                    }

                }
                if (!isInterrupted()) {
                    //Показываем наилучший результат
                    System.out.println("\nOptimal threads count is " + (imin + 1));
                    System.out.println("Minimum time = " + results[imin] / KOEF);
                }
            }
        }
    }
}
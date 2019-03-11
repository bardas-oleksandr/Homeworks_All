//1. Есть массив. В многопоточном режиме подсчитать его сумму.
// Реализовать run интерфейса Runnable с помощью лямбда выражений.
// (Или переделать старую задачу)
//2. Разработать обобщенный функциональный интерфейс, который позволит
// с его помощью выполнять подсчет МАХ и MIN значения массива,
// среднего арифметического и т.п.. Реализовать ее с помощью ссылок на методы.

import Interfaces.IProblem;
import Interfaces.IService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int choice = 0;
        final int PROBLEMS_COUNT = 2;
        do{
            IService.clearConsole();
            System.out.println("MENU");
            for(int i = 1; i <= PROBLEMS_COUNT; i++){
                System.out.println(i + " - Problem #" + i);
            }
            System.out.println("0 - Exit");
            System.out.print("Your choice:");

            choice = IService.getIntegerBounded(0, PROBLEMS_COUNT);

            if(choice != 0){
                IProblem problem = Interfaces.ICreator.create(choice);
                problem.solve();
                System.out.print("Press enter to continue");
                try {
                    char str = (char) System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }while(choice != 0);
        System.out.println("Buy-buy!");
    }
}

//1. Задать два стека, поменять информацию местами.
//2. Определить класс Set на основе множества целых чисел, n = размер. Создать методы для определения
// пересечения и объединения множеств.
//3. Выполнить попарное суммирование произвольного конечного ряда чисел следующим образом:
//на первом этапе суммируются попарно рядом стоящие числа,
//на втором этапе суммируются результаты первого этапа и т.д. до тех пор, пока не останется одно число.

import Interfaces.IProblem;
import Interfaces.IService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int choice = 0;
        final int PROBLEMS_COUNT = 3;
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

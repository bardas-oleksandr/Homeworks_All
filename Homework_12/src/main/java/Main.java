//1. Написать класс, который умеет хранить в себе массив любых типов данных (Integer, Long, String etc.).
//Реализовать метод, который возвращает любой элемент массива по индексу.

//2. Перепишите Вашу задачу по подсчету суммы массива чисел в многопоточном режиме в Generic форму.

//3. Для первой задачи подумайте, как можно дополнительно реализовать 3 метода:
//- метод возвращает отсортированный массив
//- метод возвращает сумму элементов массива
//- метод возвращает МАХ элемент
//- метод возвращает MIN элемент

import Interfaces.IProblem;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int choice = 0;
        final int PROBLEMS_COUNT = 3;
        do{
            Interfaces.IConsole.clearConsole();
            System.out.println("MENU");
            for(int i = 1; i <= PROBLEMS_COUNT; i++){
                System.out.println(i + " - Problem #" + i);
            }
            System.out.println("0 - Exit");
            System.out.print("Your choice:");

            choice = Interfaces.IConsole.getIntegerBounded(0, PROBLEMS_COUNT);

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

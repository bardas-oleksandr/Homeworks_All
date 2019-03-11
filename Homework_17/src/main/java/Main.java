//1. Сгенерируйте коллекцию. Разделите ее на N частей и вычислите среднее арифметическое каждой части,
//максимальное и минимальное значение и выведите все в новую коллекцию. Коллекцию отсортируйте,
// передав собственный компаратор.  Используйте сплитератор для этой задачи.
//2. Попробуйте 1 задачу решить с помощью многопоточного подхода, но для анализа используйте
// колонки, а не строки.
//3. Для цветочного киоска, реализуйте систему показа ТОП10 цветов, которые приносят
// максимальную прибыль киоску, согласно истории продаж. Прибыльность определяется
// по формуле (Цена продажи - Цена закупки)*Количество продаж. Попробуйте переопределить
// компаратор по этой логике. Ну и ТОП цветов от которых стоит избавиться.

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

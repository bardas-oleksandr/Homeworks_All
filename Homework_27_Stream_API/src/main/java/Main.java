//1. Есть конечный stream строк (предложений):
//        1.1. Найти строку максимальной длины в потоке.
//        1.2. Определите все символьные строки максимальной длины (если их несколько одинаковой длины)
//        1.3. Определить сколько раз встречается нужное нам слово.

import Interfaces.IProblem;
import Interfaces.IService;

public class Main {
    public static void main(String[] args) {
        int choice;
        final int PROBLEMS_COUNT = 1;
        do{
            IService.clearConsole();
            System.out.println("MENU");
            System.out.println("1 - Problem #1");
            System.out.println("0 - EXIT");
            System.out.print("Your choice:");
            choice = IService.getIntegerBounded(0, PROBLEMS_COUNT);
            if(choice != 0){
                IProblem problem = Interfaces.ICreator.create(choice);
                if(problem != null){
                    problem.solve();
                }
                IService.pressEnterToContinue();
            }
        }while(choice != 0);
        System.out.println("Buy-buy!");
    }
}

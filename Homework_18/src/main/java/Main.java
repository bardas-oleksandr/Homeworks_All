//1. Properties - цены в киоске по умолчанию вынести во внешний файл, читать оттуда.
//2. Optional  - обернуть свойства классов Цветов Optional.
//3. Observerable - использовать систему уведомлений в нужном вам процессе
// (например при изменении цены цветов при расчете от цены поставки)
//4. Создать задачу, которая будет вам запускать JOB переоценки цветов
// в файле Properties зависимости от даты и времени суток.
// Логику придумайте сами. Используйте Timer и TimerTask

import Interfaces.IProblem;
import Interfaces.IService;

public class Main {
    public static void main(String[] args) {
        int choice;
        final int PROBLEMS_COUNT = 1;
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
                IService.pressEnterToContinue();
            }
        }while(choice != 0);
        System.out.println("Buy-buy!");
    }
}

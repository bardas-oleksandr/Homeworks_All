//С помощью Enum попробуйте создать:
// 1. Фабрика (factory)
// 2. Синглтон
// 3. Статический утилитарный класс (utility class)

import Interfaces.IConsole;
import Interfaces.IProblem;
import Problem_01.Warrior.Warrior;
import Problem_01.WarriorFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final int PROBLEM_COUNT = 3;
        int choice;
        do{
            IConsole.clearConsole();
            System.out.println("MENU");
            System.out.println("1 - pattern \"Abstract Factory\"");
            System.out.println("2 - pattern \"Singleton\"");
            System.out.println("3 - static utility class");
            System.out.println("0 - exit");
            System.out.print("Your choice:");
            do{
                choice = IConsole.getIntegerBounded(0,PROBLEM_COUNT);
            }while(choice < 0 || choice > PROBLEM_COUNT);
            if (choice != 0) {
                Interfaces.IProblem problem = Interfaces.ICreator.create(choice);
                problem.solve();
                System.out.print("Press Enter to continue");
                System.in.read();
            }
        }while(choice != 0);
        System.out.print("Buy-buy!");
    }
}

//1. Создать метод, который через Reflection выведет информацию о классе:
// имя класса, модификаторы доступа, перечень конструкторов полей, методов и
// доступных аннотаций, если есть.
//2. Создать свою аннотацию с логическим свойством change и числовым maxValue.
// Пометить ей несколько private переменных класса (например цены цветов).
// Через Reflection найти эти помеченные поля и проверить если change==true то
// изменить значение на заданное, иначе нет. Проверять чтобы присваиваемое
// значение было не более чем  maxValue. Через Reflection вызвать методы которые
// используют эти поля для расчетов (например расчет прибыли) до изменения и после.
// Показать разницу.
//3. С помощью Reflection попробуйте наполнить данными массив

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final int PROBLEMS_COUNT = 3;
        int choice = 0;
        do {
            Interfaces.IConsole.cleanConsole();
            System.out.println("MENU");
            System.out.println("1 - problem #1");
            System.out.println("2 - problem #2");
            System.out.println("3 - problem #3");
            System.out.println("0 - exit");
            System.out.print("Your choice:");
            do {
                choice = Interfaces.IConsole.getInteger();
                if (choice < 0 || choice > PROBLEMS_COUNT) {
                    System.out.println("Incorrect input. Integer from the range 0.." + PROBLEMS_COUNT + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (choice < 0 || choice > PROBLEMS_COUNT);
            if (choice != 0) {
                Interfaces.IProblem problem = Interfaces.ICreator.create(choice);
                problem.solve();
                System.out.print("Press enter to continue");
                char str = (char) System.in.read();
            }
        } while (choice != 0);
        System.out.println("Buy-buy!");
    }
}
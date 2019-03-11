//1. В цветочном магазине сделать систему сохранения цен на цветы в файл и чтение их из файла при проведении расчетов.
//   Те кто не делал его - просто создайте класс Flower в котором будет 3 свойства: код, наименование и цена.
//   Создайте массив объектов этого класса. Сделайте методы добавления, удаления и редактирования данных и
//   сохраняйте их в файл. При последующих запусках программы, ищите этот файл и из него загружайте уже
//   существующие данные.
//2. Создайте повторяющуюся аннотацию @DefFlower c 3 внутренними методами: код, наименование и цена.
//   Пометьте объявление массива из задания 1 несколькими такими аннотациями. Реализуйте следующую логику:
//   если файл не найден или он пустой - вытяните информацию из списка аннотаций @DefFlower и занесите ее
//   в массив объектов класса Flower.
//3. Для знакомых с языком С/C++ создайте dll в котором будет реализована функция подсчета суммы массива.
//   Массив передается в функцию и возвращается результат. Можете реализовать этот механизм в рамках магазина,
//   сымитировав работу кассового аппарата. В него вы передаете массив покупок с их количеством - обратно он
//   возвращает чек с суммой.

import Interfaces.*;
import Shop.FlowerShop;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final int MODES_COUNT = 3;
        int choice = 0;
        FlowerShop shop = new FlowerShop();
        do {
            Interfaces.IConsole.cleanConsole();
            System.out.println("CHOOSE THE ENTRANCE MODE");
            System.out.println("1 - Buyer");        //Вход для покупателя
            System.out.println("2 - Owner");        //Вход для собственника магазина
            System.out.println("3 - Provider");     //Вход для поставщика
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                choice = Interfaces.IConsole.getInteger();
                if (choice < 0 || choice > MODES_COUNT) {
                    System.out.println("Incorrect input. Integer from the range 0.." + MODES_COUNT + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (choice < 0 || choice > MODES_COUNT);
            if (choice != 0) {
                IProcess process = ICreator.create(choice, shop);
                process.start();
            }
        } while (choice != 0);
        System.out.println("Buy-buy!");
    }
}
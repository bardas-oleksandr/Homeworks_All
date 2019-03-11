//2. Создать свою аннотацию с логическим свойством change и числовым maxValue.
// Пометить ей несколько private переменных класса (например цены цветов).
// Через Reflection найти эти помеченные поля и проверить если change==true то
// изменить значение на заданное, иначе нет. Проверять чтобы присваиваемое
// значение было не более чем  maxValue. Через Reflection вызвать методы которые
// используют эти поля для расчетов (например расчет прибыли) до изменения и после.
// Показать разницу.

package Problem_02;

import Interfaces.IProblem;
import Problem_02.Flowers.*;
import Problem_02.Shop.FlowerStack;
import Problem_02.Shop.FlowerStore;
import Problem_02.ShopInterfaces.*;
import Problem_02.ShopExceptions.*;
import Problem_02.Shop.FlowerShop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Problem implements IProblem {
    public void solve() {
        //Часть 1. Подготовка данных
        System.out.println("=========================STEP 1========================================");
        //Формируем магазин
        FlowerShop shop = new FlowerShop();
        IProvider provider = shop;
        IOwner owner = shop;
        IVendor vendor = shop;
        Logger logger = new Logger();

        //Поставка цветов по завышенной себестоимости
        try {
            provider.deliverFlowers(Tulip.class, 5, 1.05, "orange");
        } catch (CountException e) {
            e.printStackTrace();
        } catch (PriceException e) {
            e.printStackTrace();
        } catch (LogException e) {
            logger.addLog(e.getMessage());
        }

        //Устанавливаем завышенную цену цветов
        try {
            owner.setPrice(Tulip.class, 1.9, "orange");
        } catch (PriceException e) {
            e.printStackTrace();
        } catch (LogException e) {
            logger.addLog(e.getMessage());
        }

        shop.showAll(); //Показываем что получилось
        System.out.println("\n\n");

        //Часть 2. Смена цены цветов с помощью рефлексии
        System.out.println("=========================STEP 2========================================");
        //а.)Спуск с уровня магазина до уровня хранилища
        Class classShop = shop.getClass();
        Field[] fieldsShop = classShop.getDeclaredFields(); //Получаем список всех полей класса FlowerShop
        Field fieldFlowerStore = null;
        for (Field field : fieldsShop) {   //Ищем среди всех полей класса FlowerShop, поле типа FlowerStore
            if (field.getType() == FlowerStore.class) {
                fieldFlowerStore = field;
                fieldFlowerStore.setAccessible(true);
                break;
            }
        }
        FlowerStore store = null;
        try {
            store = (FlowerStore) fieldFlowerStore.get(shop);    //Получаем доступ к приватному полю класса FlowerShop
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //б.)Спуск с уровня хранилища до уровня стэка конкретного вида цветов
        Class classFlowerStore = fieldFlowerStore.getType();
        Field[] fieldsStore = classFlowerStore.getDeclaredFields(); //Получаем список всех полей класса FlowerStore
        Field fieldStacksCount = null;
        Field fieldPRoot = null;
        for (Field field : fieldsStore) {   //Ищем среди всех полей класса FlowerStore, поле типа FlowerStack и поле типа int
            if (field.getType() == int.class) {
                fieldStacksCount = field;
                fieldStacksCount.setAccessible(true);
            } else if (field.getType() == FlowerStack.class) {
                fieldPRoot = field;
                fieldPRoot.setAccessible(true);
            }
        }
        int stacksCount = 0;
        FlowerStack pRoot = null;
        try {
            stacksCount = (int) fieldStacksCount.get(store); //Получаем доступ к приватному полю класса FlowerStore
            pRoot = (FlowerStack) fieldPRoot.get(store); //Получаем доступ к приватному полю класса FlowerStore
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //в.)Спуск с уровня стэка до уровня цветка
        Class classFlowerStack = fieldPRoot.getType();
        Class toFind = null;
        try {

            //[LProblem_02.Flowers.Flower; - означает массив объектов типа Flower.
            //Символы "[L" в начале и ";" - означают поиск класса массива заданого типа
            //(см. D:\Programming\Java\02_Homeworks\JNI\Accessing Java Fields)
            toFind = Class.forName("[L" + "Problem_02.Flowers.Flower" + ";");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < stacksCount; i++) { //Перебираем все стэки
            Field[] fieldsStack = classFlowerStack.getDeclaredFields();
            Field fieldStack = null;
            Field fieldFlowersCount = null;
            Field fieldPNext = null;
            for (Field field : fieldsStack) {   //Ищем среди всех полей класса FlowerStack, поле типа Flower[] и поле типа int
                if (field.getType() == int.class) {
                    fieldFlowersCount = field;
                    fieldFlowersCount.setAccessible(true);
                } else if (field.getType() == toFind) {
                    fieldStack = field;
                    fieldStack.setAccessible(true);
                } else if (field.getType() == fieldPRoot.getType()){
                    fieldPNext = field;
                    fieldPNext.setAccessible(true);
                }
            }
            int flowersCount = 0;
            Flower[] stack = null;
            try {
                flowersCount = (int) fieldFlowersCount.get(pRoot); //Получаем доступ к приватному полю класса FlowerStack
                stack = (Flower[]) fieldStack.get(pRoot); //Получаем доступ к приватному полю класса FlowerStack
                pRoot = (FlowerStack) fieldPNext.get(pRoot); //Получаем доступ к приватному полю класса FlowerStack
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            //г.) Анализируем каждый цветок и меняем цены, если надо
            Class<?> classFlower = Flower.class;
            double value1 = 0;
            double value2 = 0;
            Field field1 = null;    //Это нужно чтобы не задавать вручную цену для каждого цветка
            Field field2 = null;    //Это нужно чтобы не задавать вручную цену для каждого цветка
            for(int j = 0; j < flowersCount; j++){
                Flower flower = stack[j];
                Field[] fieldFlower = classFlower.getDeclaredFields();
                for(Field field: fieldFlower){
                    ChangePrice anno = field.getAnnotation(ChangePrice.class);
                    if(anno != null && anno.change() == true){
                        field.setAccessible(true);
                        try {
                            if(j == 0){   //Цену будем задавать только для первого цветка на стэке
                                if(field1 == null){ //Смысл этого условия - при первом заходе инициализировать field1, при втором - field2
                                    field1 = field;
                                }else{
                                    field2 = field;
                                }
                                System.out.print("Set new " + field.getName() + " (maxValue = " + anno.maxValue() + "):");
                                double value = 0;
                                do{ //Пользователь задает новое значение
                                    value = Interfaces.IConsole.getDouble();
                                    if(value < 0 || value > anno.maxValue()){
                                        System.out.print("Incorrect input! Double value from the range 0.." + anno.maxValue() + " is expected. Try again:");
                                    }
                                }while(value < 0 || value > anno.maxValue());
                                if(field.getName() == field1.getName()){    //Полю field1 будет соответствовать значение value1, полю field2 - value2
                                    value1=value;
                                }else{
                                    value2=value;
                                }
                            }
                            if(field.getName() == field1.getName()){    //После выполнения всех проверок можем установить новое значение
                                field.set(flower, value1);
                            }else{
                                field.set(flower, value2);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        shop.showAll(); //Показываем что получилось
        System.out.println("\n\n");

        //Часть 3. Вызов с помощью рефлексии методов для формирования букета и его продажи
        System.out.println("=========================STEP 3========================================");
        try {
            Method adder = classShop.getDeclaredMethod("addToBouquet", Class.class, String.class);
            Method seller = classShop.getDeclaredMethod("sellBouquet");
            Method show = classShop.getDeclaredMethod("showAll");

                for (int i = 0; i < 5; i++) {
                    Object[] args = new Object[]{Tulip.class, "orange"};
                    try {
                        adder.invoke(shop, args);   //Вызов метода добавления цветка в букет
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) { //Это исключение, генерируемое вызываемыми методами
                        logger.addLog(e.getMessage());
                    }
                }

            try {
                seller.invoke(shop);   //Вызов метода продажи букета
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) { //Это исключение, генерируемое вызываемыми методами
                logger.addLog(e.getMessage());
            }

            try {
                show.invoke(shop);   //Вызов метода "Показать все"
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) { //Это исключение, генерируемое вызываемыми методами
                logger.addLog(e.getMessage());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

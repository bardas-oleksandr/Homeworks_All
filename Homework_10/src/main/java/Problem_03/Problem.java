//3. С помощью Reflection попробуйте наполнить данными массив

package Problem_03;

import Problem_03.Flowers.*;
import Interfaces.IProblem;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class Problem implements IProblem {
    public void solve() {
        final int LENGTH = 10;
        final int MAX_VAL = 10;

        //1 ситуация. Заполняем данными массив-локальную переменную
        System.out.println("==========CHANGING LOCAL VARIABLE==========================================");
        int[] data = (int[]) Array.newInstance(int.class, LENGTH);
        Random rnd = new Random();
        for (int i = 0; i < data.length; i++) {
            Array.set(data, i, rnd.nextInt(MAX_VAL));
            System.out.println("array[" + (i + 1) + "]=" + Array.get(data, i));
        }

        //2 Ситуация. Заполняем данными массив - приватное поле класса
        //Есть класс Bouquet
        //Кто-то его спроектировал так, что мы не имеем метода для заполнения одного из приватных полей этого класса - массива цветов
        //Задача - заполнить массив-приватное поле без использования методов класса.
        System.out.println("==========CHANGING PRIVATE FIELD==========================================");
        Bouquet bouquet = new Bouquet();
        System.out.println("==========BEFORE CHANGE===================================================");
        System.out.println(bouquet);

        //1-й способ. Через рефлексию меняем существующую ссылку на массив ссылкой на новый массив
        Class c = bouquet.getClass();
        Field[] fields = c.getDeclaredFields(); //Находим все поля, абстрагируясь от конкретного имени поля
        for (Field field : fields) {    //Среди всех полей класса, найдем поле типа Flower[]
            Class<?> c1 = field.getType();
            Class<?> c2 = null;
            try {
                c2 = Class.forName("[LProblem_03.Flowers.Flower;");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (c1.getName() == c2.getName()) {
                field.setAccessible(true);  //Разрешение на доступ к приватному полю
                Flower[] tmp = new Flower[3];   //Подготовим массив цветов
                for (int i = 0; i < 3; i++) {
                    tmp[i] = new RoseFlower("red", 1.5);
                }
                try {
                    field.set(bouquet, tmp);    //Изменим значение приватного поля
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("==========AFTER CHANGE (FIRST METHOD)=====================================");
        System.out.println(bouquet);

        //2-й способ. Через рефлексию получаем ссылку на массив и изменяем элементы массива
        Class c_1 = bouquet.getClass();
        Field[] fields_1 = c_1.getDeclaredFields(); //Находим все поля, абстрагируясь от конкретного имени поля
        for (Field field : fields_1) {    //Среди всех полей класса, найдем поле типа Flower[]
            Class<?> c1 = field.getType();
            Class<?> c2 = null;
            try {
                //[LProblem_03.Flowers.Flower; - означает массив объектов типа Flower.
                //Символы "[L" в начале и ";" - означают поиск класса массива заданого типа
                //(см. D:\Programming\Java\02_Homeworks\JNI\Accessing Java Fields)
                c2 = Class.forName("[L" + "Problem_03.Flowers.Flower" + ";");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (c1.getName() == c2.getName()) {
                field.setAccessible(true);  //Разрешение на доступ к приватному полю
                Flower[] temp = null;
                try {
                    temp = (Flower[]) field.get(bouquet);    //Получаем значение поля
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < temp.length; i++) {   //Меняем каждый элемент в отдельности
                    temp[i] = new Tulip("orange", 0.5);
                }
            }
        }
        System.out.println("==========AFTER CHANGE (SECOND METHOD)====================================");
        System.out.println(bouquet);

        //3-й способ. Через рефлексию получаем доступ к приватному методу заполнения массива
        bouquet.clear();
        Class c_2 = bouquet.getClass();
        try {
            Method method = c_2.getDeclaredMethod("addFlower", Flower.class);
            method.setAccessible(true);
            for(int i = 0; i < 3; i++){
                Object[] args = new Object[]{new Gladiolus("white", 0.9)};
                try {
                    method.invoke(bouquet, args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("==========AFTER CHANGE (THIRD METHOD)=====================================");
        System.out.println(bouquet);
    }
}

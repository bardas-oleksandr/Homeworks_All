package Enums.Basics;

import Interfaces.IProblem;

import java.lang.Enum;

public class Problem implements IProblem {
    public void solve() {
        Fish fish;
        fish = Fish.Pike;

        System.out.println(fish.say());

        switch (fish) {
            //case Fish.Pike:   //Полное имя будет ошибкой
            case Pike:
                System.out.println("I caught a pike");
                break;
            case Perch:
                System.out.println("I caught a perch");
                break;
            case Zander:
                System.out.println("I caught zander");
                break;
        }

        Fish allFish[] = Fish.values();
        for (Fish smallFish : allFish) {
            System.out.println(smallFish);
        }

        for (Fish smallFish : Fish.values()) {
            System.out.println(smallFish);
        }

        Fish bigFish = Fish.valueOf("Pike");
        System.out.println(bigFish.say());

        //Fish unknownFish = Fish.valueOf("Sturgeon");  //Это приведет к ошибке

        for (Fish smallFish : Fish.values()) {
            System.out.println(smallFish);
        }

        System.out.println(fish.ordinal());

        System.out.println(fish.compareTo(Fish.Zander));

        //System.out.println(fish.compareTo(Frog.Green)); //С помощью compareTo нельзя сравнивать объекты разных перечислений

        System.out.println("fish.Pike.equals(Frog.Red) = " + fish.Pike.equals(Frog.Red));   //С помощью equals можно сравнивать объекты разных перечислений

        //Сравнение enum
        //1. С точки зрения "правильности" - использование метода equals и оператора "==" равнозначны
        Fish first = Fish.Pike;
        Fish second = Fish.Catfish;
        if (first == second) {

        }
        if (first.equals(second)) {

        }
        //2. Но использование "==" имеет преимущества
        //2.1. Легче читается
        //2.2. Работает немного быстрее
        //2.3. Позволяет избежать NullPointerException
        Fish third = null;
        if (third == second) {

        }
        try {
            third.equals(second);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        //2.4. Использование "==" обеспечивает более высокую безпасность.
        Frog fourth = Frog.Green;
        //Тут мы сравниваем Enum разных классов.
        try {
            fourth.equals(first);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Оператор "==" нам не разрешит сравнить разные типы
//        if(fourth == first){
//
//        }

        //ПРЕОБРАЗОВАНИЕ STRING В ENUM
        Enum.valueOf(Fish.class, "Pike");
    }
}

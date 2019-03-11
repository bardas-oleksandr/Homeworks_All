package Generics.Wildcards;

import Interfaces.IProblem;

import java.util.ArrayList;

public class Problem implements IProblem {
    @Override
    public void solve() {
        //ЧТО ПОКАЗЫВАЕТ ПРИМЕР
        //Пример показывает основные моменты применения Wildcards.
        //Для того чтобы понять Wildcards полностью - смотри пример WildcardsRevelation
        //КУДА СМОТРЕТЬ - смотреть описание методов класса Problem

        ArrayList<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Lion",false));
        animalList.add(new Animal("Tyger",false));
        animalList.add(new Animal("White bear",false));

        ArrayList<Fish> fishList = new ArrayList<>();
        fishList.add(new Fish("Murena",true));
        fishList.add(new Fish("Barracuda",true));
        fishList.add(new Fish("Perch",false));

        ArrayList<Pike> pikeList = new ArrayList<>();
        pikeList.add(new Pike("Pike", true));
        pikeList.add(new Pike("Pike", true));
        pikeList.add(new Pike("Pike", true));

        System.out.println("====================ArrayList<Fish> list=============================");
        //testFish(animalList); //Не компилируется
        testFish(fishList);
        //testFish(pikeList);   //Не компилируется. ArrayList<Pike> нельзя неявно привести к ArrayList<Fish>

        System.out.println("====================ArrayList<? extends Fish> list===================");
        //testExtendsFish(animalList); //Не компилируется
        testExtendsFish(fishList);
        testExtendsFish(pikeList);

        System.out.println("====================ArrayList<? super Fish> list=====================");
        testSuperFish(animalList);
        testSuperFish(fishList);
        //testSuperFish(pikeList); //Не компилируется
    }

    //Элементы списка ArrayList<? extends Fish> list будут рассматриваться как объекты типа Fish
    //При этом не получится неявно привести ArrayList, параметризированный потомком класса Fish к
    //ArrayList<Fish>
    private void testFish(ArrayList<Fish> list){
        list.forEach(System.out::println);

        //Доступны все методы класса Fish и его родителей
        list.get(0).isWaterAnimal();
        list.get(0).isSaltWaterFish();
    }

    //Элементы списка ArrayList<? extends Fish> list будут рассматриваться как объекты типа Fish
    //При этом можно в качестве параметра передать любой ArrayList, параметризированный потомком Fish
    private void testExtendsFish(ArrayList<? extends Fish> list){
        list.forEach(System.out::println);

        //Доступны все методы класса Fish и его родителей
        list.get(0).isWaterAnimal();
        list.get(0).isSaltWaterFish();
    }

    //Элементы списка ArrayList<? super Fish> list будут рассматриваться как объекты типа Object
    private void testSuperFish(ArrayList<? super Fish> list){
        list.forEach(System.out::println);

        //Доступны методы только класса Object
        //list.get(0).isWaterAnimal(); //Нам недоступны никаие методы, кроме етодов класса Object
        //Так как мы указали что ArrayList может содержать объекты, любого из классов вверх по иерархии,
        //то это могут быть даже объекты класса Object
    }
}

package Enums.EnumFactory;

import Enums.EnumFactory.Warriors.Warrior;
import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Factory factory = Factory.HUMAN;    //Можно создать объект enum
        Warrior Vasya = factory.createFootman();

        factory = Factory.ORC;
        Warrior Petya = factory.createFootman();

        Vasya.act();
        Petya.act();

        Warrior Kolya = Factory.HUMAN.createFootman();  //Можно просто пользоваться методами без сохранения объекта

        Kolya.act();
    }
}

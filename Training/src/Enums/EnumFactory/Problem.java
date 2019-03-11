package Enums.EnumFactory;

import Enums.EnumFactory.Warriors.Warrior;
import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Factory factory = Factory.HUMAN;    //����� ������� ������ enum
        Warrior Vasya = factory.createFootman();

        factory = Factory.ORC;
        Warrior Petya = factory.createFootman();

        Vasya.act();
        Petya.act();

        Warrior Kolya = Factory.HUMAN.createFootman();  //����� ������ ������������ �������� ��� ���������� �������

        Kolya.act();
    }
}

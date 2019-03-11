package Problem_01;

import Interfaces.IProblem;
import Problem_01.Warrior.Warrior;

import java.util.Random;

public class Problem implements IProblem {
    public void solve() {
        //Создаем массив воинов-людей и массив воинов-орков
        Warrior[] human = new Warrior[5];
        Warrior[] orcs = new Warrior[5];

        //Инициализируем массивы
        GroupFactory.HUMAN.createGroup(human);
        GroupFactory.ORCS.createGroup(orcs);

        System.out.println("======================HUMAN SQUAD======================");
        show(human);
        System.out.println("======================ORCS SQUAD=======================");
        show(orcs);

    }

    public void show(Warrior[] group){
        for(Warrior warrior: group){
            System.out.println(warrior);
            System.out.println("----------------------------------------------------");
        }
    }
}

package Problem_03;

import Interfaces.IProblem;

public class Problem implements IProblem {
    public void solve() {
        //Мы имеем доступ к методам перечисления непосредственно через имя перечисления.
        System.out.println("data = " + StaticUtility.getData());

        //Мы можем изменять данные в статических полях перечисления
        StaticUtility.setData(10);

        System.out.println("data = " + StaticUtility.getData());
    }
}
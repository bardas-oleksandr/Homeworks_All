package Patterns.SingletonEnum;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        SingletonEnum.INSTANCE.setData(10);

        System.out.println(SingletonEnum.INSTANCE.getData());
    }
}

package Patterns.Singleton;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Singleton.instance().setData(10);
        System.out.println(Singleton.instance().getData());
    }
}

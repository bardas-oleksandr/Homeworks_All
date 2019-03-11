package Generics.GenericInterface;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        MyInteger obj = new MyInteger();

        DemoClass<MyInteger> a = new DemoClass<MyInteger>(obj);
        a.show();

        MyDouble obj_1 = new MyDouble();
        DemoClass<MyDouble> b = new DemoClass<MyDouble>(obj_1);
        b.show();
    }
}

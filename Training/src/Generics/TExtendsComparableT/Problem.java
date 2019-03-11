package Generics.TExtendsComparableT;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        MyShort obj = new MyShort();
        doNothing(obj);
    }

    public static <T extends MyComparable<T>> void doNothing(T obj){
        System.out.println("Yes, it is.");
        obj.myIntegerMethod();
    }

}

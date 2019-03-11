package Polymorphism.PolymorphismAndParameters;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Heir luke = new Heir();

        method(luke);
    }

    public void method(Parent obj){
        obj.doNoting();
    }
}

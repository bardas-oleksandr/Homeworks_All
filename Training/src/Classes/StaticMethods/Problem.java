package Classes.StaticMethods;

import Interfaces.IProblem;

public class Problem implements IProblem {

    @Override
    public void solve() {
        Parent person = new Heir();
        System.out.println(person.speak());

        MyClass my = new MyClass();
        my.func();
    }
}

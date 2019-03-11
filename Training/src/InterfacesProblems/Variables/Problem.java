package InterfacesProblems.Variables;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        System.out.println(Foo.x);
    }
}

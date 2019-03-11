package InterfacesProblems;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        IParentInterface first = new Implementor();
        first.method();
    }
}

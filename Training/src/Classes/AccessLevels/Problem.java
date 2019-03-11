package Classes.AccessLevels;

import Classes.AccessLevels.SomePackage.Parent;
import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Parent parent = new Parent();

        parent.x = 1;   //Извне класса доступной является только public-переменная

    }
}

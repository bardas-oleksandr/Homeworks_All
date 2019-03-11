package Problem_02;

import Interfaces.IProblem;

public class Problem implements IProblem {
    public void solve() {
        //Единственный экземпляр класса Singleton
        System.out.println(Singleton.Instance);

        //Этот экземпляр может хранить в себе то же что и экземпляр обычного класса - для примера поле типа int
        System.out.println("data = " + Singleton.Instance.getData());

        //Мы можем изменять эти данные так же как и при работе с обычными классами
        Singleton.Instance.setData(10);
        System.out.println("data = " + Singleton.Instance.getData());
    }

}

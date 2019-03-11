package JavaLang.ClassVoid;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        MyGen my = new MyGen();
        my.doNothing(); //Мы вызвали метод, который согласно интерефейсу что-то возвращает. Но этот возвращаемый тип был параметризирован
        //типом Void
    }
}

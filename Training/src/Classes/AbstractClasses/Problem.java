package Classes.AbstractClasses;

import Interfaces.IProblem;

public class Problem implements IProblem{
    @Override
    public void solve() {
        AbstractFather obj = new Heir(10);

        System.out.println(obj.getX());
    }

    //В С++ в качестве аргумента можно использовать или ссылку или указатель на объект абстрактного класса. Но не сам
    //объект абстрактного класса. В Java - все с чем мы имеем дело - это по сути указатели. Поэтому тут тоже можно
    //использовать в качестве аргумента метода тип абстрактного класса.
    void method(AbstractFather obj){
        System.out.println("It is OK");
    }
}

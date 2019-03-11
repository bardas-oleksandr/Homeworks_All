package Polymorphism.InheritanceAndReferences;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Parent object = new Heir(1,1);
        object.staticMethod();  //Тут полиморфизм не работает

        Parent[] data_1 = new Parent[2];
        Parent[] data_2 = new Heir[2];

        data_1[0] = new Parent(1);
        data_1[1] = new Heir(1,2);
        data_1[0].parentMethod();
        data_1[1].parentMethod();
        //data_1[1].heirMethod();   //Через ссылку типа Parent на объект типа Heir, нет доступа к методам из класса Heir
        ((Heir)data_1[1]).heirMethod(); //Для получения доступа нужно приведени типов

        try{
            data_2[0] = new Parent(1);
        }
        catch(ArrayStoreException e){
            System.out.println("Нельзя по ссылке типа наследника записать объект типа родителя");
        }
        data_2[1] = new Heir(1,2);
        data_2[1].parentMethod();
        //data_2[1].heirMethod();   //Вроде бы мы создавали массив ссылок типа Heir, но сам массив типа Parent, поэтому доступа все так же нет
        ((Heir)data_2[1]).heirMethod(); //Для получения доступа нужно приведени типов
    }
}

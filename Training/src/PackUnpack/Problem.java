package PackUnpack;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Integer x = 10; //Пример автоупаковки
        int y = x;  //Пример автораспаковки
        System.out.println(function(2));    //Происходит следующее
                                                //1. Упаковка при вызове метода function(2) - упаковуем 2 в Integer x
                                                //2. Распаковка при вычислении x*2
                                                //3. Упаковка при возвращени из метода function(2) значения Integer
                                                //4. Распаковка при передаче аргумента методу System.out.println

        x++;    //Тут происходит распаковка, инкремент, а затем упаковка. Из-за того что в Java  нельзя перегружать операторы

        x=384;
        System.out.println(x.byteValue());
    }

    Integer function(Integer x){
        return x*2;
    }
}

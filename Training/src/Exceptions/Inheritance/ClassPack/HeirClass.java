package Exceptions.Inheritance.ClassPack;

import Exceptions.Inheritance.Heir.HeirException;

public class HeirClass extends ParentClass {

    //Этот метод в родительском классе бросал исключение ParentException, являющееся тоже родительским для исключения HeirException
    //Таким образом происходит сужение, что допустимо
    @Override
    public void BothThrows() throws HeirException {
        System.out.println("Yes, it is");
    }

    //Этот метод в родительском классе бросал исключение ParentException, являющееся тоже дочерним для исключения Exception
    //Таким образом происходит расширение, что НЕдопустимо
//    @Override
//    public void BothThrows() throws Exception{
//        System.out.println("Yes, it is");
//    }
}

package Exceptions.Inheritance.Heir;

import Exceptions.Inheritance.ParentException;

public class HeirException extends ParentException {
    HeirException(int x, int x1){
        //super(x); //Этот конструктор не могу использовать из-за его уровня доступа
        super(x, x1);
    }

}

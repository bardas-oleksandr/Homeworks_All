package Exceptions.Inheritance;

public class ParentException extends Exception {
    int xPackage;
    protected int xProtected;

    ParentException(){
        xPackage=0;
        xProtected=0;
    }

    ParentException(int x){
        xPackage=x;
        xProtected=0;
    }

    protected ParentException(int x, int y){
        xPackage=x;
        xProtected=y;
    }
}

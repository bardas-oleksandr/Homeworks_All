package Generics.MetaSymbol_Revelation;

public class GenericClass<T> {
    T obj;

    GenericClass(T other){
        this.obj = other;
    }

    @Override
    public String toString(){
        return obj.toString();
    }
}

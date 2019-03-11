package Generics.MetaSymbol;

public class Coord<T extends Point2D> {
    public T point;

    public Coord(T obj){
        this.point = obj;
    }

    public Coord<T> getMe(){
        return this;
    }
}

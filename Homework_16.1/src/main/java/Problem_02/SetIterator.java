package Problem_02;

import java.util.Iterator;

public class SetIterator<E> implements Iterator<E> {
    private int index;
    private E[] arr;

    public SetIterator(E[] arr){
        this.index = 0;
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return (index < arr.length && arr[index] != null);
    }

    @Override
    public E next() {
        return arr[index++];
    }
}

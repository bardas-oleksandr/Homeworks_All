package Problem_01;

import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> list;

    public Stack(T[] arr){
        this.list = new LinkedList<>();
        for(T item: arr){
            this.list.addLast(item);
        }
    }

    public void push(T val){
        this.list.addFirst(val);
    }

    public T pop(){
        return this.list.removeFirst();
    }

    public T peek(){
        return this.list.getFirst();
    }

    @Override
    public String toString(){
        return this.list.toString();
    }

    public void exchange(Stack<T> other){
        LinkedList<T> tmp = this.list;
        this.list = other.list;
        other.list = tmp;
    }
}

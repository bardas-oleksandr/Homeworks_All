package Problem_01;

import java.lang.reflect.Array;

public class GenericArray<T> {
    private T[] arr;

    public GenericArray(T[] other){
        this.arr = other;
    }

    public GenericArray(){
        this.arr = null;
    }

    public void put(T obj){
        int length;
        if(this.arr != null){
            length = this.arr.length;
        }else{
            length = 0;
        }
        T[] tmp = (T[]) Array.newInstance(obj.getClass(), length + 1);
        for(int i = 0; i < length; i++){
            tmp[i] = this.arr[i];
        }
        tmp[length] = obj;
        this.arr = tmp;
    }

    public T get(int index){
        return this.arr[index];
    }

    public void set(int index, T val){
        this.arr[index] = val;
    }

    public int length(){
        if(this.arr != null){
            return this.arr.length;
        }
        return 0;
    }


    public void show(){
        for(T item: arr){
            System.out.print(item + "\t");
        }
        System.out.print("\n");
    }
}

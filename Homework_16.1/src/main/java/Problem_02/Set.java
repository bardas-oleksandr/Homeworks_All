package Problem_02;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Set<T> implements java.util.Set<T> {
    private T[] arr;
    private int size;

    public Set(T[] other){
        this.arr = (T[])Array.newInstance(other.getClass().getComponentType(), other.length + 16);
        this.size = other.length;
        for(int i = 0; i < this.size; i++){
            this.arr[i]= other[i];
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if(o != null){
            for(int i = 0; i < size; i++){
                if(this.arr[i].equals(o)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {  //Анонимный класс
            private int index = 0;
            @Override
            public boolean hasNext() {
                return (index < arr.length && arr[index] != null);
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        T[] result = null;
        if(this.size != 0){
            result = (T[]) Array.newInstance(this.arr.getClass().getComponentType(), this.size);
            for(int i = 0; i < this.size; i++){
                result[i] = this.arr[i];
            }
        }
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] r = a.length >= this.size ? a:
                (T1[])Array.newInstance(a.getClass().getComponentType(), this.size);
        Iterator<T> iterator = iterator();

        for(int i = 0; i < r.length; i++){
            if(! iterator.hasNext()){   //fewer number of elements, then expected
                if(r == a){
                    r[i] = null;
                }else if(a.length < i){ //capacity of "a"-array is bigger then number of set elements
                    return Arrays.copyOf(r, i);
                }else{
                    System.arraycopy(r,0,a,0,i);
                    if(a.length > i){
                        a[i] = null;
                    }
                }
                return a;
            }
            a[i] = (T1)iterator.next();
        }
        return r;
    }

    @Override
    public boolean add(T val){
        if(!contains(val)){
            if(this.arr.length == size){
                T[] tmp = (T[])Array.newInstance(this.arr[0].getClass(), (int)(this.size*1.2) + 1);
                for(int i = 0; i < size; i++){
                    tmp[i]=this.arr[i];
                }
                this.arr = tmp;
            }
            this.arr[size++]=val;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(o != null){
            for(int i = 0; i < size; i++){
                if(this.arr[i].equals(o)){
                    for(int j = i; j < size-1; j++){
                        this.arr[j] = this.arr[j+1];
                    }
                    this.arr[--this.size]=null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            if(!contains(obj)){
                return false;
            }
        }
        return true;
    }

    //Метод для объединения множеств
    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator<?> iterator = c.iterator();
        boolean flag = false;
        while(iterator.hasNext()){
            if(add((T)iterator.next())){
                flag = true;    //Если хотя бы один элемент был добавлен, наш метод вернет true
            }
        }
        return flag;
    }

    //Метод для пересечения множеств
    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        boolean flag = false;
        for(int i = 0; i < this.size; i++){
            if(! c.contains(this.arr[i])){
                remove(this.arr[i]);
                flag = true;    //Если хотя бы один элемент был исключен, наш метод вернет true
                i--;
            }
        }
        return flag;
    }

    //Метод для вычитания множеств
    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        boolean flag = false;
        while(iterator.hasNext()){
            if(remove(iterator.next())){
                flag = true;    //Если хотя бы один элемент был удален, наш метод вернет true
            }
        }
        return flag;
    }

    @Override
    public void clear() {
        this.arr = (T[])Array.newInstance(this.arr.getClass().getComponentType(), 16);
        this.size = 0;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < this.size; i++){
            result.append(this.arr[i] + "\t");
        }
        return new String(result);
    }
}
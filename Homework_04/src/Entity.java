public class Entity<T> {
    private T value;
    public Entity() {
        this.value = (T)null;
    }
    public T getValue(){
        return this.value;
    }
    public void setValue(T value){
        this.value = value;
    }
}
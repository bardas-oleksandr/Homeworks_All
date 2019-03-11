public class Pair {
    private int value;
    private int priority;
    private final static int max = 10;
    public Pair(){
        this(0,0);
    }
    public Pair(int value, int priority){
        this.value=value;
        this.priority=priority;
    }
    public void setValue(int value){
        this.value=value;
    }
    public void setPriority(int priority){
        this.priority=priority;
    }
    public int getValue(){
        return value;
    }
    public int getPriority(){
        return priority;
    }
}

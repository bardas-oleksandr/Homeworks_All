public class Stack {
    private int[] stack;
    private int size;
    private int tos;
    public Stack() throws SizeLessThenZero{
        this(0);
    }
    public Stack(int size) throws SizeLessThenZero {
        if(size < 0){
            throw new SizeLessThenZero();
        }
        this.size = size;
        if(this.size != 0){
            this.stack = new int[this.size];
        }
        else{
            this.stack=null;
        }
        this.tos = -1;
    }
    public void push(int value){
        // Если стэк заполнен, расширяем стэк на один элемент
        if(tos == size-1) {
            int tmp[] = new int[size + 1];
            for (int i = 0; i < size; i++) {
                tmp[i] = stack[i];
            }
            stack = tmp;
            size++;
        }
        stack[++tos] = value;
    }
    public int pop() throws StackIsEmpty{
        if(tos == -1){
            throw new StackIsEmpty();
        }
        tos--;
        return stack[tos+1];
    }
    public int top() throws StackIsEmpty{
        if(tos == -1){
            throw new StackIsEmpty();
        }
        return stack[tos];
    }
}

public class Queue {
    private int queue[];
    private int size;
    private int last;
    public Queue()throws SizeLessThenZero{
        this(0);
    }
    public Queue(int size) throws SizeLessThenZero{
        if(size < 0){
            throw new SizeLessThenZero();
        }
        this.size = size;
        if(this.size != 0) {
            this.queue = new int[this.size];
        }
        else{
            this.queue=null;
        }
        this.last = -1;
    }
    public void enqueue(int value){
        //Если очередь заполнена, расширяем очередь на один элемент
        if(last == size-1){
            int tmp[] = new int[size+1];
            for(int i=0; i < size; i++) {
                tmp[i] = queue[i];
            }
            queue = tmp;
            size++;
        }
        queue[++last] = value;
    }
    public int dequeue() throws QueueIsEmpty{
        if(last == -1){
            throw new QueueIsEmpty();
        }
        int result = queue[0];
        for(int i = 0; i < last; i++){
            queue[i]=queue[i+1];
        }
        last--;
        return result;
    }
    public void show(){
        if(last == -1){
            System.out.println("Queue is empty!");
        }
        else {
            for (int i = last; i >= 0; i--) {
                System.out.print(queue[i] + "\t");
            }
            System.out.print("\n");
        }
    }
}

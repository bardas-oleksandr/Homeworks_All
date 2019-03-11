public class Problem_02 extends Problem{
    private Queue queue;
    public Problem_02(){
        try{
            queue = new Queue();
        }
        catch(SizeLessThenZero e){
            System.out.println(e.getMessage());
        }
    }
    public void show(){
        //Демонстрация работы с безразмерной очередью
        final int size = 10;
        for(int i = 0; i < size; i++){
            queue.enqueue(i);
            queue.show();
        }
        for(int i = 0; i < size+1; i++){
            queue.show();
            try{
                System.out.println(queue.dequeue()+" is moving out");
            }
            catch(QueueIsEmpty e){
                System.out.println(e.getMessage());
            }
        }
    }
}

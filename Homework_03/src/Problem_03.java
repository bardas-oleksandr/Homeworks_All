public class Problem_03 extends Problem{
    private PriorityQueue queue;
    public Problem_03(){
        try{
            queue = new PriorityQueue();
        }
        catch(SizeLessThenZero e){
            System.out.println(e.getMessage());
        }
    }
    public void show(){
        //Демонстрация работы очереди с приоритетом
        final int size = 10;
        for(int i = 0; i < size; i++){
            queue.enqueue(new Pair(ConsoleInterface.NextRandom(20),ConsoleInterface.NextRandom(10)));
            queue.show();
        }
        for(int i = 0; i < size+1; i++){
            queue.show();
            try{
                Pair movedOut = queue.dequeue();
                System.out.println("(" + movedOut.getValue() + ";" + movedOut.getPriority() + ") is moving out");
            }
            catch(QueueIsEmpty e){
                System.out.println(e.getMessage());
            }
        }
    }
}

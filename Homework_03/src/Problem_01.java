public class Problem_01 extends Problem{
    private Stack stack;
    public Problem_01(){
        try{
            stack = new Stack();
        }
        catch(SizeLessThenZero e){
            System.out.println(e.getMessage());
        }
    }
    public void show(){
        //Демонстрация работы с безразмерным стэком
        final int size = 10;
        for(int i = 0; i < size; i++){
            stack.push(ConsoleInterface.NextRandom(10));
        }
        for(int i = 0; i < size+1; i++){
            try{
                System.out.print(stack.pop()+"\t");
            }
            catch(StackIsEmpty e){
                System.out.println(e.getMessage());
            }
        }
    }
}

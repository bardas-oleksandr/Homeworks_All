import java.io.IOException;
import Dispatcher.Dispatcher;
import Dispatcher.Locker;

public class Main {
    public static void main(String[] args) throws IOException{
        final int PROBLEM_COUNT = 2;
        int choice = 0;
        do{
            Interfaces.IConsole.cleanConsole();
            System.out.println("MENU");
            for(int i =0; i< PROBLEM_COUNT; i++){
                System.out.println((i+1) + " - problem #" + (i+1));
            }
            System.out.println("0 - exit");
            System.out.print("Your choice:");
            do{
                choice = Interfaces.IConsole.getIntegerBounded(0, PROBLEM_COUNT);
            }while(choice < 0 || choice > PROBLEM_COUNT);
            if(choice != 0){
                Dispatcher dispatcher = new Dispatcher(choice, new Locker(true));
                try{
                    dispatcher.join();
                }
                catch(InterruptedException e){
                    System.out.println("Dispatcher was interrupted");
                }
            }
        }while(choice!=0);
        System.out.println("Buy-buy!");
    }
}

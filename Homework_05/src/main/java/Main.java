import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        int choice = 0;
        do{
            Interfaces.IConsole.cleanConsole();
            System.out.println("MENU");
            System.out.println("1 - problem #1");
            System.out.println("2 - problem #2");
            System.out.println("3 - problem #3");
            System.out.println("0 - exit");
            System.out.print("Your choice:");
            do{
                choice = Interfaces.IConsole.getInteger();
                if(choice < 0 || choice > 3){
                    System.out.print("Incorrect input! Integer from the range 0..3 is expected. Try again: ");
                }
            }while(choice < 0 || choice > 3);
            if(choice != 0){
                Interfaces.IProblem problem = Interfaces.Creator.create(choice);
                problem.solve();

                System.out.print("Press enter to continue");
                char str = (char)System.in.read();
            }
        }while(choice!=0);
        System.out.println("Buy-buy!");
    }
}

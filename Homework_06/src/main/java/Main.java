import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int choice = 0;
        do{
            Interfaces.IConsole.cleanConsole();
            System.out.println("MENU");
            System.out.println("1 - show simulation");
            System.out.println("0 - exit");
            System.out.print("Your choice:");
            do{
                choice = Interfaces.IConsole.getInteger();
                if(choice != 0 && choice != 1){
                    System.out.println("Incorrect input. Integer from the range 0..1 is expected.");
                    System.out.print("Try again: ");
                }
            }while(choice != 0 && choice != 1);
            if(choice != 0){
                Interfaces.IProblem problem = new Problem();
                problem.solve();
                System.out.print("Press enter to continue");
                char str = (char)System.in.read();
            }
        }while(choice != 0);
        System.out.println("Buy-buy!");
    }
}

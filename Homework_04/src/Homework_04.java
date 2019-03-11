import java.util.Scanner;

public class Homework_04 {
    public static void main(String args[]){
        int choice;
        do{
            IConsole.cleanConsole();
            System.out.println("MENU");
            System.out.println("1 - problem #1");
            System.out.println("2 - problem #2");
            System.out.println("3 - problem #3");
            System.out.println("4 - problem #4");
            System.out.println("5 - problem #5");
            System.out.println("0 - exit");
            System.out.print("Your choice:");
            do{
                choice = IConsole.getInteger();
                if(choice < 0 || choice > 5){
                    System.out.println("Incorrect input. Integer from the range 0..4 is expected.");
                    System.out.print("Try again:");
                }
            }while(choice < 0 || choice > 5);
            if(choice != 0) {
                IWhatToDo problem = Creator.createProblem(choice);
                problem.solve();

                System.out.print("Press any key");
                Scanner in = new Scanner(System.in);
                String tmp = in.nextLine();
            }
        }while(choice >= 1 && choice <= 5);
        System.out.println("Buy-buy!");
    }
}

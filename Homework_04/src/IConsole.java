import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public interface IConsole {
    static int getInteger(){
        Scanner scan = new Scanner(System.in);
        while(true){
            try{
                return scan.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Incorrect input. Integer is expected.");
                System.out.print("Try again:");
                scan.nextLine();    //Removing incorrect input
            }
        }
    }

    static void cleanConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException ex) {}
    }
}

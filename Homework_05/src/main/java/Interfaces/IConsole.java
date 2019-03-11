package Interfaces;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface IConsole {
    static int getInteger(){
        Scanner scan = new Scanner(System.in);
        do{
            try{
                return scan.nextInt();
            }
            catch(InputMismatchException e){
                System.out.print("Incorrect input! Integer is expected. Try again: ");
                scan.nextLine();
            }
        }while(true);
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

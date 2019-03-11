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

    static int nextIntegerBounded(int lower, int higher){
        int result = getInteger();
        if(result < lower || result > higher){
            throw new IllegalArgumentException();
        }
        return result;
    }

    static int getIntegerBounded(int lower, int higher){
        do{
            try{
                return nextIntegerBounded(lower, higher);
            }
            catch(IllegalArgumentException e){
                System.out.print("Incorrect input! Integer from the range " + lower + ".." + higher + " is expected. Try again: ");
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

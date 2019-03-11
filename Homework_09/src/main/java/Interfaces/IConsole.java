package Interfaces;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface IConsole {
    static int getInteger(){
        Scanner scan = new Scanner(System.in);
        while(true){
            try{
                return scan.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Incorrect input! Integer is expected. Try again:");
                scan.nextLine();
            }
        }
    }

    static int nextIntegerBounded(int lower, int higher){
        int result = getInteger();
        if(result < lower || result > higher){
            throw new IllegalArgumentException();
        }
        return result;
    }

    static int getIntegerBounded(int lower, int higher){
        while(true){
            try{
                return nextIntegerBounded(lower, higher);
            }
            catch(IllegalArgumentException e){
                System.out.println("Incorrect input! Integer from the range " + lower + ".." + higher + " is expected. Try again:");
            }
        }
    }

    static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException ex) {}
    }
}

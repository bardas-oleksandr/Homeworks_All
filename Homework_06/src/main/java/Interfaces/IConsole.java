package Interfaces;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface IConsole {
    static void cleanConsole(){
        try{
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch(IOException | InterruptedException e){}
    }

    static int getInteger(){
        Scanner scan = new Scanner(System.in);
        while(true){
            try{
                return scan.nextInt();
            }
            catch(InputMismatchException e){
                System.out.print("Incorrect input! Integer is expected. Try again: ");
                scan.nextLine();
            }
        }
    }

    static double round(double value, int precision){
        int mult=1;
        for(int i =0; i < precision; i++){
            mult*=10;
        }
        int intPart = (int)value;
        int fractionPart;
        value -= (int)value;
        value *= mult;
        value += 0.5;
        fractionPart = (int)value;
        return intPart + ((double)fractionPart)/mult;
    }
}

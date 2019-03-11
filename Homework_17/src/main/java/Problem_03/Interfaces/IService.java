package Problem_03.Interfaces;

import java.io.IOException;
import java.io.ObjectInput;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public interface IService {
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

    static int getIntegerBounded(int min, int max){
        int result = 0;
        do{
            result = getInteger();
            if(result < min || result > max){
                System.out.print("Incorrect input! Integer from the range " + min + ".." + max + " is expected. Try again:");
            }
        }while(result < min || result > max);
        return result;
    }

    static double getDouble(){
        Scanner scan = new Scanner(System.in);
        while(true){
            try{
                return scan.nextDouble();
            }
            catch(InputMismatchException e){
                System.out.print("Incorrect input! Double value is expected. Try again: ");
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

    static String generateColor() {
        Random rnd = new Random();
        int number = rnd.nextInt(3);
        switch (number) {
            case 0: return "yellow";
            case 1: return "red";
            case 2: return "white";
            default: return "orange";
        }
    }

    static double generateDouble(int bound){
        Random rnd = new Random();
        return (double)rnd.nextInt(bound) + ((double)rnd.nextInt(100))/100;
    }
}

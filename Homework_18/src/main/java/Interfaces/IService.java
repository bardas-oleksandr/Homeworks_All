package Interfaces;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface IService {
    static int getInteger(){
        Scanner scan = new Scanner(System.in);
        while(true){
            try{
                return scan.nextInt();
            }catch(InputMismatchException e){
                System.out.print("Incorrect input! Integer is expected. Tru again:");
                scan.nextLine();
            }
        }
    }

    static void clearConsole(){
        try{
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else{
                Runtime.getRuntime().exec("clear");
            }
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    static int getIntegerBounded(int min, int max){
        int result;
        do{
            result = getInteger();
            if(result < min || result > max){
                System.out.print("Incorrect input! Integer from the range " + min + ".." + max + " is expected. Try again:");
            }
        }while(result < min || result > max);
        return result;
    }

    static <T extends Number> void initArray(T[][] arr, IRandomizer<T> getter, int max){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = getter.nextValue(max);
            }
        }
    }

    static <T> void showArray(T[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    static <T extends Number> double min(T[][] arr){
        int imin = 0, jmin = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j].doubleValue() < arr[imin][jmin].doubleValue()){
                    imin = i;
                    jmin = j;
                }
            }
        }
        return arr[imin][jmin].doubleValue();
    }

    static <T extends Number> double max(T[][] arr){
        int imax = 0, jmax = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j].doubleValue() > arr[imax][jmax].doubleValue()){
                    imax = i;
                    jmax = j;
                }
            }
        }
        return arr[imax][jmax].doubleValue();
    }

    static <T extends Number> double average(T[][] arr){
        double result = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result += arr[i][j].doubleValue();
                count++;
            }
        }
        return result/count;
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

    static Date getDate(){
        System.out.print("hour:");
        int hour = getIntegerBounded(0,23);
        System.out.print("minute:");
        int minute = getIntegerBounded(0,59);
        Calendar day = Calendar.getInstance();
        day.set(Calendar.HOUR, hour);
        day.set(Calendar.MINUTE, minute);
        day.set(Calendar.SECOND, 0);
        return new Date(day.getTimeInMillis());
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

    static void pressEnterToContinue(){
        System.out.print("Press enter to continue");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

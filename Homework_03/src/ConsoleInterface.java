//Класс сервисных методов для работы с консолью
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class ConsoleInterface {
    public final static int getInteger(){
        Scanner in = new Scanner(System.in);
        while(true){
            try{
                return in.nextInt();
            }
            catch(java.util.InputMismatchException e){
                System.out.print("Incorrect input! Integer is expected! Try again:");
                in.nextLine();
            }
        }
    }
    public final static void cleanConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException ex) {}
    }
    public final static int NextRandom(int max){
        Random rnd = new Random();
        return rnd.nextInt(max);
    }
}

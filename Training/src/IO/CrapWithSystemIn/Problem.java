//ЧТО ПОКАЗЫВАЕТ ПРИМЕР.
//Поток ввода-вывода через консоль нельзя закрывать и использовать через try
//с ресурсами

package IO.CrapWithSystemIn;

import Interfaces.IProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem implements IProblem {
    @Override
    public void solve() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Write something for BufferedReader:");
            reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("BufferedReader is still opened");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Write something for BufferedReader:");
            reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("BufferedReader is closed");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Write something for BufferedReader:");
            reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

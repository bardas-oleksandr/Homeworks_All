package Dispatcher;

import java.io.IOException;

//Класс потока, который будет висеть во время работы основных задач и ждать запроса на экстренное завершение программы
public class Terminator extends Thread {
    char symb;

    public Terminator(){
        super();
        this.symb = 0;
        super.start();
    }

    public void run(){
        System.out.println("Your program is working now");
        System.out.println("If you want to terminate it, press Enter");
        while(this.symb != '\n'){   //Терминатор будет работать до первого Enter
            try{
                this.symb = (char)System.in.read();
            }
            catch(IOException e){
                System.out.println("Something bad has happened");
            }
        }
    }
}

package Dispatcher;

import java.io.IOException;

public class Dispatcher extends Thread {
    private Locker locker;
    private int processorNumber;

    public Dispatcher(int processorNumber, Locker locker){
        super();
        this.locker = locker;
        this.processorNumber = processorNumber;
        super.start();
    }

    public void run(){
        Thread processor = Interfaces.Creator.create(processorNumber, locker);

        this.locker.Waiting(true);  //Диспетчер дойдет сюда и будет ждать пока процессор не снимет замок

        //Поток, который будет висеть во время работы основных задач и ждать запрос на экстренное завершение программы
        Terminator terminator = new Terminator();

        this.locker.Notifying(true);    //Снимаем замок для процессора, теперь и процессор и диспетчер могут работать вместе

        while(processor.isAlive()){   //Пока задача решается, мы проверяем не вызвал ли пользователь команду терминации задачи
            if(!terminator.isAlive()){
                processor.interrupt();
                break;
            }
        }
        try{
            System.out.print("Press enter to continue");
            if(!terminator.isAlive()){
                char str = (char)System.in.read();
            }
            terminator.join();
            processor.join();
        }
        catch(InterruptedException e){
            System.out.println("Proccess was interrupted, supposedly by user");
            System.out.println("Details: exception in run() method (class Dispatcher)");
        }
        catch(IOException e){
            System.out.println("Something bad has happened");
        }
    }
}

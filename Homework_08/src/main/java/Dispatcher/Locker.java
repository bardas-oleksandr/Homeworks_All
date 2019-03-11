package Dispatcher;

//Класс для взаимной блокировки потоков
public class Locker {
    private boolean processorWorks; //Если true - работает поток processor, иначе - поток dispatcher

    public Locker(boolean processorWorks){
        this.processorWorks = processorWorks;
    }

    public synchronized void Waiting(boolean dispatcherWaiting){
        while(this.processorWorks == dispatcherWaiting){
            try {
                wait();
            } catch(InterruptedException e) {
                System.out.println("Proccess was interrupted, supposedly by user");
                System.out.println("Details: exception in Waiting(boolean dispatcherWaiting) method (class Locker)");
            }
        }
    }

    public synchronized void Notifying(boolean notifyProccesor) {
        this.processorWorks = notifyProccesor;
        notify();
    }
}

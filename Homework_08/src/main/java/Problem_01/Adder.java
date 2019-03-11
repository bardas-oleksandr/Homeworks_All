package Problem_01;

public class Adder<T extends Number> extends Thread {
    private Double result;
    private T[]mas;
    private Integer call;
    private Integer number;

    public Adder(T[] mas, Integer call, Integer number, Integer priority){
        super();
        this.result = new Double(0);
        this.mas = mas;
        this.call = call;
        this.number = number;
        super.setPriority(priority);
        super.start();
    }

    //Чтобы не вычислять диапазоны для каждого потока и не возиться потом с проблемами округлений этих диапазонов, суммирование
    //например для первого потока (всего потоков n) выполняется так: mas[0]+mas[n]+mas[n*2]+mas[n*3]+....
    public void run(){
        int tmp = this.number;
        while(tmp < mas.length){
            //System.out.println("Count=" + call + "\t Number =" + number); //Это можно раскомментировать для проверки работы потоков
            this.result += this.mas[tmp].doubleValue();
            tmp += this.call;
        }
    }

    public Double getResult(){
        return this.result;
    }
}

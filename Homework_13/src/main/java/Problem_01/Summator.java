package Problem_01;

public class Summator<T extends Number> extends Thread {
    private T[][] arr;
    private double result;
    private int number;
    private int threadsCount;
    private Runnable method;

    Summator(T[][] arr, int number, int count, IRunBody<T> runBody) {
        super();
        this.arr = arr;
        this.result = 0;
        this.number = number;
        this.threadsCount = count;
        //Тело метода run определяем на основе параметра RunBodyInterface<T> runBody
        this.method = () -> {
            this.result = runBody.run(this.arr, this.number, this.threadsCount);
        };
        super.start();
    }

    @Override
    public void run(){
        this.method.run();
    }

    public double getResult() {
        return this.result;
    }
}

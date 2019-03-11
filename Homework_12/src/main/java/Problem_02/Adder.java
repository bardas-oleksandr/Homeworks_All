package Problem_02;

public class Adder <T extends Number> extends Thread {
    private T[][] arr;
    private double result;
    private int number;
    private int threadsCount;

    Adder(T[][] arr, int number, int count){
        super();
        this.arr = arr;
        this.result = 0;
        this.number = number;
        this.threadsCount = count;
        super.start();
    }

    @Override
    public void run(){
        int current = this.number;
        for(int i = 0; i < arr.length; i++){
            while(current < arr[i].length){
                this.result += arr[i][current].doubleValue();
                current += this.threadsCount;
            }
            current -= arr[i].length;
        }
    }

    public double getResult(){
        return this.result;
    }
}

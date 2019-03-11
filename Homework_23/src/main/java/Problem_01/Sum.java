package Problem_01;

import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask<Double> {
    private double[] data;
    private int start;
    private int end;
    private int threshold;

    Sum(double[] data, int start, int end, int threshold) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Double compute() {
        double result = 0;
        if (this.end - this.start <= this.threshold) {
            result = this.data[start];
            for (int i = start; i < end; i++) {
                result += this.data[i];
            }
        } else {
            int mid = (this.start + this.end) / 2;
            result = new Sum(this.data, this.start, mid, this.threshold).invoke() +
                    new Sum(this.data, mid, this.end, this.threshold).invoke();
        }
        return result;
    }
}

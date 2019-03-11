package Problem_01;

import Interfaces.IProblem;
import Interfaces.IRandomizer;
import Interfaces.IService;

import java.util.Random;

public class Problem implements IProblem {
    public void solve() {
        //Тело метода run
        //Создаем ссылку на интерефейс обощенного типа - чтобы не создавать отдельный экземпляр под каждый тип (Integer, Double)
        IRunBody runBody = (arr, number, count) -> {
            double result = 0;
            int current = number;
            for (int i = 0; i < arr.length; i++) {
                while (current < arr[i].length) {
                    result += arr[i][current].doubleValue();
                    current += count;
                }
                current -= arr[i].length;
            }
            return result;
        };

        final int SIZE = 5;
        final int MAX = 100;
        final int MAX_THREADS_COUNT = 100;
        final int MIN_THREADS_COUNT = 0;

        System.out.print("Set threads count (1..100):");
        int threadsCount = IService.getIntegerBounded(MIN_THREADS_COUNT, MAX_THREADS_COUNT);

        System.out.println("====================INTEGER ARRAY=========================");
        Integer[][] arr = new Integer[SIZE][SIZE];
        IRandomizer<Integer> getterInt = (max) -> {
            Random rnd = new Random();
            return rnd.nextInt(max);
        };
        IService.initArray(arr, getterInt, MAX);   //Передаем инициализиатору ссылку на метод, которым надо инициализировать массив
        IService.showArray(arr);

        double result = calculate(arr, threadsCount, runBody);
        System.out.println("Sum of integer array is equal " + result);

        System.out.println("====================DOUBLE ARRAY==========================");
        Double[][] arr1 = new Double[SIZE][SIZE];
        IRandomizer<Double> getterDouble = (max) -> {
            Random rnd = new Random();
            return max * rnd.nextDouble();
        };
        IService.initArray(arr1, getterDouble, MAX);   //Передаем инициализиатору ссылку на метод, которым надо инициализировать массив
        IService.showArray(arr1);

        result = calculate(arr1, threadsCount, runBody);
        System.out.println("Sum of double array is equal " + result);
    }

    //Метод может находить сумму двухмерных зубчатых массивов
    public final <T extends Number> double calculate(T[][] arr, int threadsCount, IRunBody<T> runBody) {
        double result = 0;
        Summator<?>[] summators = new Summator<?>[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            summators[i] = new Summator<T>(arr, i, threadsCount, runBody);
        }
        for (int i = 0; i < threadsCount; i++) {
            try {
                summators[i].join();
                result += summators[i].getResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

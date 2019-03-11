package Problem_02;

import Interfaces.*;

import java.util.Random;

public class Problem implements IProblem {
    @Override
    public void solve() {
        final int SIZE = 5;
        final int MAX = 100;

        System.out.println("===============================Integer array================================================");
        Integer[][] arr1 = new Integer[SIZE][SIZE];
        IRandomizer<Integer> getterInt = (max) -> {
            Random rnd = new Random();
            return rnd.nextInt(max);
        };
        IService.initArray(arr1, getterInt, MAX);   //Передаем инициализиатору ссылку на метод, которым надо инициализировать массив
        IService.showArray(arr1);
        System.out.println("Minimum value: " + getParam(IService::min, arr1));
        System.out.println("Maximum value: " + getParam(IService::max, arr1));
        System.out.println("Average value: " + getParam(IService::average, arr1));

        System.out.println("===============================Double array=================================================");
        Double[][] arr2 = new Double[SIZE][SIZE];
        IRandomizer<Double> getterDouble = (max) -> {
            Random rnd = new Random();
            return max * rnd.nextDouble();
        };
        IService.initArray(arr2, getterDouble, MAX);   //Передаем инициализиатору ссылку на метод, которым надо инициализировать массив
        IService.showArray(arr2);
        System.out.println("Minimum value: " + getParam(IService::min, arr2));
        System.out.println("Maximum value: " + getParam(IService::max, arr2));
        System.out.println("Average value: " + getParam(IService::average, arr2));
    }

    public <T> double getParam(IStatistic<T> getter, T[][] arr) {
        return getter.getParameter(arr);
    }
}

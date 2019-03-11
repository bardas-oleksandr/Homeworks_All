//2. Реализуйте сходную задачу по умножению 2-х матриц. Размер матриц МхN вводимые величины.

package Problem_02;

import Dispatcher.Locker;
import java.util.Random;

public class Processor extends Thread {
    private Locker locker;

    public Processor(Locker locker){
        super();
        this.locker = locker;
        super.start();
    }

    @Override
    public void run(){
        final int koef = 100000; //Коэффициент для перевода наносекунд в число, удобное для восприятия
        final int maxDim = 500;

        //Исходные данные
        System.out.println("Set 1-st matrix rows count (1.." + maxDim + "): "); //Количество строк первой матрицы
        int L = Interfaces.IConsole.getIntegerBounded(1, maxDim);
        System.out.println("Set 2-nd matrix columns count (1.." + maxDim + "): "); //Количество столбцов второй матрицы
        int N = Interfaces.IConsole.getIntegerBounded(1, maxDim);
        System.out.println("Set common parameter of matrixes (1.." + maxDim + "): ");  //Количество столбцов первой матрицы, оно же количество строк второй
        int M = Interfaces.IConsole.getIntegerBounded(1, maxDim);
        System.out.println("Set threads count (1..100): ");  //Количество потоков, которое будет исследоваться (от 1 до call)
        int call = Interfaces.IConsole.getIntegerBounded(1, 50);
        System.out.println("Do you want to see all matrixes after multiplication? (1-Yes, 0-No): ");  //Нужно ли показать матрицы после расчета
        int show = Interfaces.IConsole.getIntegerBounded(0, 1);
        System.out.println("Let's go");

        this.locker.Notifying(false);
        this.locker.Waiting(false);

        Integer[][] matrix1 = new Integer[L][M]; //Генерируем матрицы
        Integer[][] matrix2 = new Integer[M][N];
        Integer[][] matrix3 = new Integer[L][N];
        if(!isInterrupted()){
            generateMatrix(matrix1, 10);
            generateMatrix(matrix2, 10);
            generateEmptyMatrix(matrix3);
        }
        label:{
            if(!isInterrupted()){
                long[] results = new long[call];  //Сюда будем записывать продолжительность умножения для разных вариантов количества потоков
                int imin=0;
                for(int i = 1; i <= call; i++){ //Для каждого варианта количества потоков от 1 до call проводим отдельный эксперимент на одном и том же массива
                    results[i-1] = -System.nanoTime();  //Момент начала нахождения произведения матриц
                    Multiplicator[] multiplicators = new Multiplicator[i];  //Массив потоков
                    for(int j = 0; j < i; j++){
                        multiplicators[j] = new Multiplicator(matrix1, matrix2, matrix3, i, j, Thread.NORM_PRIORITY);    //Приоритет выставим на один ниже нормального. За счет этого я расчитываю на то что все потоки для подсчета суммы будут выброшены одновременно
                    }
                    for(int j = 0; j < i; j++){ //Надо дождаться завершения всех потоков
                        try{
                            multiplicators[j].join();
                        }
                        catch(InterruptedException e){  //Это исключение прилетает в результате действия терминатора.
                            break label;
                        }
                    }
                    results[i-1] += System.nanoTime();  //Момент окончания нахождения произведения матриц
                    if(results[i-1] < results[imin]){
                        imin = i-1;
                    }
                    //Показываем результат по текущему опыту
                    System.out.println("Threads count = " + i);
                    System.out.println("Time = " + results[i-1]/koef);
                    System.out.println("============================");
                }
                if(!isInterrupted()){
                    //Показываем наилучший результат
                    System.out.println("\nOptimal threads count is " + (imin + 1));
                    System.out.println("Minimum time = " + results[imin]/koef);
                    if(show == 1){
                        System.out.println("=====================MATRIX 1=====================");
                        show(matrix1);
                        System.out.println("=====================MATRIX 2=====================");
                        show(matrix2);
                        System.out.println("=====================MATRIX 3=====================");
                        show(matrix3);
                    }
                }
            }
        }
    }

    private void generateMatrix(Integer[][] matrix, int max){
        Random rnd = new Random();
        for(int i = 0; i< matrix.length; i++){
            if(!isInterrupted()){
                for(int j = 0; j< matrix[0].length; j++){
                    matrix[i][j] = new Integer(rnd.nextInt(max));
                }
            }else{
                break;
            }
        }
    }

    private void generateEmptyMatrix(Integer[][] matrix){
        for(int i = 0; i< matrix.length; i++){
            if(!isInterrupted()){
                for(int j = 0; j< matrix[0].length; j++){
                    matrix[i][j] = new Integer(0);
                }
            }else{
                break;
            }
        }
    }

    private void show(Integer[][] matrix){
        for(int i = 0; i< matrix.length; i++){
            for(int j = 0; j< matrix[0].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
}
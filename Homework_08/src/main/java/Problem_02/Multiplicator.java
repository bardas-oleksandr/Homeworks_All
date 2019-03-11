package Problem_02;

public class Multiplicator extends Thread {
    private Integer[][]matrix1;
    private Integer[][]matrix2;
    private Integer[][]matrix3;
    private Integer call;
    private Integer number;

    public Multiplicator(Integer[][] matrix1, Integer[][]matrix2, Integer[][]matrix3, Integer call, Integer number, Integer priority){
        super();
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.matrix3 = matrix3;
        this.call = call;
        this.number = number;
        super.setPriority(priority);
        super.start();
    }

    public void run(){
        int tmp = this.number;
        while(tmp < matrix3.length * matrix3[0].length){    //Число matrix3.length * matrix3[0].length - это количество элементов в матрице, которая является результатом произведения
            //System.out.println("Count=" + call + "\t Number =" + number); //Это можно раскомментировать для проверки работы потоков
            int i = tmp/matrix3[0].length;  //Номер строки элемента под номером tmp
            int j = tmp%matrix3[0].length;  //Номер столбца элемента под номером tmp
            for(int r=0; r < matrix1[0].length ; r++){  //Число matrix1[0].length - общий параметр матриц matrix1 и matrix1
                matrix3[i][j] += matrix1[i][r] * matrix2[r][j];
            }
            tmp += this.call;
        }
    }
}

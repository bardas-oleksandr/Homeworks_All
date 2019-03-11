package IO;

import Interfaces.IProblem;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Problem implements IProblem {
    @Override
    public void solve() {
//        //Чтение символов
//        System.out.print("Write something (a  - to stop): ");
//        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            int x;
//            while ((x = b.read()) != -1) {
//                if (x == 'a') {
//                    break;
//                }
//                System.out.println((char)x);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //Чтение строк
//        System.out.print("Write something (stop - to stop): ");
//        b = new BufferedReader(new InputStreamReader(System.in));   //Помещаем в b новый поток ввода
//        try {
//            String x = null;
//            do {
//                x = b.readLine();
//                System.out.println(x);
//            } while (!x.equals("stop"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //Запись данных, выводимых на консоль
//        int x;
//        x = 'A';
//        System.out.write(x);
//
//        PrintWriter pw = new PrintWriter(System.out, true); //true означает что освобождение потока осуществляется автоматически
//        pw.println("Hello!");
//        pw.println(12345);
//        pw.print("Hello");  //Непонятно куда этот поток выводится, почему-то не на экран
//
//        //Ввод-вывод в файлы
//        FileOutputStream output;
//        try {
//            output = new FileOutputStream("data.txt");
//            try {
//                output.write('H');
//                output.write('U');
//                output.write('L');
//                output.write('I');
//                output.write('O');
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        FileInputStream input;
        try {
            input = new FileInputStream("data.txt");
            int w;
            while ((w = input.read()) != -1) {
                PrintWriter writer = new PrintWriter(System.out, true);
                writer.println((char) w);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Скопируем файл
        try (FileInputStream source = new FileInputStream("src/source.txt");
             FileOutputStream target = new FileOutputStream("src/target.txt")) {
            int value = 0;
            while ((value = source.read()) != -1) {
                target.write(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ;

        //Скопируем файл через буферизированный поток
        try (BufferedInputStream bufInput = new BufferedInputStream(new FileInputStream("src/source.txt"));
                BufferedOutputStream bufOutput = new BufferedOutputStream(new FileOutputStream("src/target.txt"))){
            byte[] buffer = new byte[1024];
            int len;
            while((len = bufInput.read(buffer)) > 0){
                bufOutput.write(buffer,0,len);
            }
        }catch(Exception e){
            System.out.println("Вот же бля");
        }

        try (InputStream input1 = new FileInputStream("src/test1.txt")) {
            byte[] buff = new byte[2];
            int len;
            while ((len = input1.read(buff, 0, 2)) > 0) {
                System.out.println(new String(buff));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("=============================================");
        byte[] buff = "Yes, it is".getBytes();
        ByteArrayInputStream bin = new ByteArrayInputStream(buff);
        try (BufferedInputStream in = new BufferedInputStream(bin)) {
            int c;
            int count = 0;
            while ((c = in.read()) > 0) {
                System.out.println((char) c);
                if (c == 's' && count == 1) {
                    in.reset();
                    count++;
                }
                if (c == 's' && count == 0) {
                    count++;
                    in.mark(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

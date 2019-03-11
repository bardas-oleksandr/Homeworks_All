package JavaLang.Basics;

import Interfaces.IProblem;

import java.io.*;
import java.util.Properties;

public class Problem implements IProblem {
    @Override
    public void solve() {
        int x = Double.SIZE;
        System.out.println("Double.SIZE = " + x);
        x = Double.BYTES;
        System.out.println("Double.BYTES = " + x);
        x = Double.MAX_EXPONENT;
        System.out.println("Double.MAX_EXPONENT = " + x);
        x = Double.MIN_EXPONENT;
        System.out.println("Double.MIN_EXPONENT = " + x);
        double y = Double.MAX_VALUE;
        System.out.println("Double.MAX_VALUE = " + y);
        y = Double.MIN_VALUE;
        System.out.println("Double.MIN_VALUE = " + y);
        y = Double.MIN_NORMAL;
        System.out.println("Double.MIN_NORMAL = " + y);


        float z = Float.intBitsToFloat(2);
        System.out.println("z = Float.intBitsToFloat(2) = " + z);
        x = Float.floatToIntBits(z);
        System.out.println("Float.floatToIntBits(z) = " + x);

        Float.isInfinite(z);

        y = 0.0/ 0.0;
        System.out.println("NaN = " + y);

        System.out.println("Integer.highestOneBit(2) = " + Integer.highestOneBit(2));
        System.out.println("Integer.highestOneBit(64) = " + Integer.highestOneBit(64));
        System.out.println("Integer.highestOneBit(257) = " + Integer.highestOneBit(257));
        System.out.println("Integer.highestOneBit(724) = " + Integer.highestOneBit(724));
        System.out.println("Integer.highestOneBit(1030) = " + Integer.highestOneBit(1030));

        System.out.println("Integer.rotateLeft(2,1) = " + Integer.rotateLeft(2,1));
        System.out.println("Integer.rotateLeft(3,1) = " + Integer.rotateLeft(3,1));
        System.out.println("Integer.rotateLeft(3,2) = " + Integer.rotateLeft(3,2));
        System.out.println("Integer.rotateLeft(16,1) = " + Integer.rotateLeft(16,1));

        System.out.println("Integer.toBinaryString(255) = " + Integer.toBinaryString(255));
        System.out.println("Integer.toBinaryString(256) = " + Integer.toBinaryString(256));

        System.out.println("Character.digit('1', 10) = " + Character.digit('1', 10));
        char ch = 49;
        System.out.println("Character.digit(ch, 10) = " + Character.digit(ch, 10));
        System.out.println("Character.forDigit(1, 10) = " + Character.forDigit(1, 10));
        System.out.println("Character.forDigit(11, 10) = " + Character.forDigit(11, 10));


        Runtime r = Runtime.getRuntime();
        System.out.println("r.freeMemory() = " + r.freeMemory());
        r.gc();
        System.out.println("r.gc();");
        System.out.println("r.freeMemory() = " + r.freeMemory());
        System.out.println("r.totalMemory() = " + r.totalMemory());

        Process p = null;

        //Откроем Notepad
        try {
            p=r.exec("Notepad");
            p.waitFor();    //Наша программа убдет ждать пока мы закроем Notepad
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Откроем Notepad
        try {
            p=r.exec("Notepad");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Не понимаю в чем ошибка
//        try {
//            p.wait(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //Закроем Notepad
        p.destroy();

        //r.halt(1);

        //ProccessBuilder
        File file = new File("data.txt");
        ProcessBuilder.Redirect.to(file);   //Хер его знает что это я сделал
        System.out.println("Hello, dolly!");

        //Класс System
        Console console = System.console();
        System.out.println(console);

        System.gc();

        Properties prop = System.getProperties();
        System.out.println(prop);

        SecurityManager secMan = System.getSecurityManager();
        System.out.println(secMan);

        System.out.println(System.lineSeparator());

        System.out.println(System.getProperty("os.name"));

        Integer val = new Integer(10);
        ClassLoader classLoader = val.getClass().getClassLoader();
        System.out.println(classLoader);
        MyClass my = new MyClass();
        classLoader = my.getClass().getClassLoader();
        System.out.println(classLoader);

        System.out.println("Math.getExponent(4) = " + Math.getExponent(4));
        System.out.println("Math.getExponent(3) = " + Math.getExponent(3));

        PrintStream ps = null;
        try {
            ps = new PrintStream("newOut.txt");
            System.setOut(ps);  //Теперь sout выводит в файл
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello, dolly!");

        r.halt(1);
    }
}

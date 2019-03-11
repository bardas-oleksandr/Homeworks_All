package IO.Flushable;

import Interfaces.IProblem;

import java.io.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/test.txt"))){
            stream.write(48);   //Это еще не запишет в файл, а просто поместит данные в буфер
            stream.write(48);
            stream.write(48);
            stream.write(48);
            stream.flush(); //А это выведет весь буфер в файл
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

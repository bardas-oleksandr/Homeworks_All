package IO.NIO_Channel;

import Interfaces.IProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class Problem implements IProblem {
    @Override
    public void solve() {
        File file = new File("channel.txt");
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");  //rw - read, write
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try(FileChannel channel = randomAccessFile.getChannel()){
            ByteBuffer buffer = ByteBuffer.allocate(50);
            String line = "Very important message";
            buffer.put(line.getBytes());
            buffer.rewind();
            try {
                channel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            buffer.clear();
            line = "==INSERTED LINE==";
            buffer.put(line.getBytes());
            buffer.rewind();
            try {
                channel.position(5);    //Мы можем задать произвольную позицию текущего эелмента
                channel.write(buffer);  //И выполнить запись в файл с этой позиции. Произойдет замена текста в файле
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

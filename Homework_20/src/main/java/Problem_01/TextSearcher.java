package Problem_01;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextSearcher implements Runnable {
    private Path pathToSearch;
    private String text;
    private SynchronizedOutputStream output;

    TextSearcher(Path path, String text, SynchronizedOutputStream out){
        this.pathToSearch = path;
        this.text = text;
        this.output = out;
    }

    @Override
    public void run() {
        int textBytesCount = text.length() * 2;
        int index = 0;
        int count = 0;
        try (FileChannel channel = (FileChannel) Files.newByteChannel(pathToSearch)) {
            final int SIZE = 1024;
            ByteBuffer buffer = ByteBuffer.allocate(SIZE + textBytesCount);
            byte[] prev = new byte[0];  //Сюда будем записывать концовку каждого вычитываемого куска файла
            // (для учета того что искомый фрагмент может быть разбит на части при вычитке)
            while (channel.read(buffer) != -1) {
                buffer.rewind();
                byte[] arr = buffer.array();
                byte[] next = new byte[prev.length + arr.length];
                if(count == 0){
                    index -= new String(prev).length();
                }
                System.arraycopy(prev,0,next,0,prev.length);
                System.arraycopy(arr,0,next,prev.length,arr.length);
                if (next.length >= textBytesCount) {
                    String line = new String(next);
                    if (line.contains(text)) {
                        if(count == 0){
                            index += line.indexOf(text);    //Фиксируем номер символа только для первого появления искомого фрагмента в файле
                        }
                        //Считаем сколько раз встречается фрагмент в текущем куске
                        count += frequency(line, text);
                    }
                    prev = new byte[textBytesCount];
                    if(count == 0){
                        index += new String(arr).length();
                    }
                    System.arraycopy(next,next.length - textBytesCount,prev,0,textBytesCount);
                }
                buffer.clear();
            }
        }catch(IOException e){
            System.out.println("IOException while text searching has occured");
        }
        if (count > 0) {
            writeResults(pathToSearch, index, count);   //Записываем результат поиска если он был результативен
        }
    }

    private static int frequency(String where, String what){
        int count = 0;
        while(where.contains(what)){
            count++;
            int start = where.indexOf(what) + what.length();
            if(start < where.length()){
                where = where.substring(where.indexOf(what) + what.length());
            }else{
                break;
            }
        }
        return count;
    }

    private void writeResults(Path path, int index, int count){
        StringBuilder builder = new StringBuilder();
        builder.append(path.toAbsolutePath().getFileName().toString());
        builder.append("\nIndex of first appearance: ");
        builder.append(index);
        builder.append("\nAppearance frequency: ");
        builder.append(count);
        builder.append("\n");
        try {
            this.output.write(new String(builder).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

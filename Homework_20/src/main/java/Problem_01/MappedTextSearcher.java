package Problem_01;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class MappedTextSearcher implements Runnable {
    private Path pathToSearch;
    private String text;
    private SynchronizedOutputStream output;

    MappedTextSearcher(Path path, String text, SynchronizedOutputStream out){
        this.pathToSearch = path;
        this.text = text;
        this.output = out;
    }

    @Override
    public void run() {
        int index = 0;
        int count = 0;
        try (FileChannel channel = (FileChannel) Files.newByteChannel(pathToSearch)) {
            long size = channel.size();
            MappedByteBuffer buffer = channel.map(
                    FileChannel.MapMode.READ_ONLY,
                    0,size);
            buffer.rewind();
            if(size < Integer.MAX_VALUE){
                byte[] arr = new byte[(int)size];
                buffer.get(arr);
                String line = new String(arr);
                if (line.contains(text)) {
                    if(count == 0){
                        index = line.indexOf(text);    //Фиксируем номер символа только для первого появления искомого фрагмента в файле
                    }
                    //Считаем сколько раз встречается фрагмент в текущем куске
                    count += frequency(line, text);
                }
            }else{
                System.out.println("File is too big");
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

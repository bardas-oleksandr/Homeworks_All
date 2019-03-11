package IO.NIO_Path_Files;

import Interfaces.IProblem;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.NonReadableChannelException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Path path = Paths.get("src/text.txt");

        System.out.println("path.toString(): " + path);
        System.out.println("path.getName(0): " + path.getName(0));
        System.out.println("path.getName(1): " + path.getName(1));

        System.out.println("path.isAbsolute(): " + path.isAbsolute());
        path = path.toAbsolutePath();
        System.out.println("path.toAbsolutePath()");
        System.out.println("path.isAbsolute(): " + path.isAbsolute());
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println("path.getName(" + i + "): " + path.getName(i));
        }
        System.out.println(path.getFileName());


        FileSystem fileSystem = path.getFileSystem();
        System.out.println(fileSystem);

        path = Paths.get("deleteMe.txt");   //Эта команда еще не создает файл визически
        try {
            if (!Files.isRegularFile(path)) {
                Files.createFile(path); //Создаем файл физически
            }
            Files.deleteIfExists(path);

            if (Files.notExists(path)) {
                System.out.println("File was deleted");
            }

            Files.createFile(path);

            if (Files.exists(path)) {
                System.out.println("File was createded");
            }

            System.out.println(path.getFileName() + " is writable: " + Files.isWritable(path));
            System.out.println(path.getFileName() + " is directory: " + Files.isDirectory(path));
            System.out.println(path.getFileName() + " is excutable: " + Files.isExecutable(path));
            System.out.println(path.getFileName() + " is hidden: " + Files.isHidden(path));
            System.out.println(path.getFileName() + " is readable: " + Files.isReadable(path));
            System.out.println(path.getFileName() + " exists: " + Files.exists(path));
            System.out.println(path.getFileName() + " is regular file: " + Files.isRegularFile(path));  //TRUE
            path = Paths.get("unexisting.txt"); //Такого файла нет фактически
            System.out.println(path.getFileName() + " exists: " + Files.exists(path));
            System.out.println(path.getFileName() + " is regular file: " + Files.isRegularFile(path));  //FALSE

            System.out.println("============FILES.COPY==================================================================");
            path = Paths.get("deleteMe.txt");
            Path copy = Paths.get("deleteMe_copy.txt");
            Files.copy(path, copy, StandardCopyOption.REPLACE_EXISTING); //REPLACE_EXISTING - если файл в который копируем
            //уже существует, он будет заменен

            System.out.println("============FILES.MOVE==================================================================");
            Path dest = Paths.get("src/deleteMe.txt");
            Files.move(path, dest, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("============StandardOpenOption==========================================================");
            ByteBuffer buffer = ByteBuffer.allocate(50);
            String line = "Very important message";
            buffer.put(line.getBytes());
            buffer.rewind();

            System.out.println("============StandardOpenOption.WRITE====================================================");
            try (ByteChannel byteChannel = Files.newByteChannel(copy, StandardOpenOption.WRITE)) {
                try {
                    byteChannel.read(buffer);
                } catch (NonReadableChannelException e) {
                    System.out.println("NonReadableChannelException");
                }
                byteChannel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("============StandardOpenOption.APPEND===================================================");
            try (ByteChannel byteChannel = Files.newByteChannel(copy, StandardOpenOption.APPEND)) {
                byteChannel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("============StandardOpenOption.CREATE, TRUNCATE_EXISTING================================");
            try (ByteChannel byteChannel = Files.newByteChannel(copy, StandardOpenOption.CREATE,  //Создает новый файл (даже такой уже есть)
                    StandardOpenOption.WRITE,   //Для записи
                    StandardOpenOption.TRUNCATE_EXISTING)) {  //Предыдущая информация стирается
                buffer = ByteBuffer.allocate(50);
                buffer.put("Yes, it is".getBytes());
                buffer.rewind();
                byteChannel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("============StandardOpenOption.CREATE_NEW===============================================");
            try (ByteChannel byteChannel = Files.newByteChannel(copy, StandardOpenOption.CREATE_NEW)) {
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("============StandardOpenOption.DELETE_ON_CLOSE==========================================");
            //Файл будет удален после завершения программы
            try (ByteChannel byteChannel = Files.newByteChannel(copy, StandardOpenOption.DELETE_ON_CLOSE)) {
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("============BasicFileAttributes=========================================================");
            Path file = Paths.get("document.txt");
            BasicFileAttributes bfa = Files.readAttributes(file, BasicFileAttributes.class); //с. 781 Шилдта
            System.out.println("creationTime(): " + bfa.creationTime());
            System.out.println("lastAccessTime(): " + bfa.lastAccessTime());
            System.out.println("size(): " + bfa.size() + " bytes");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

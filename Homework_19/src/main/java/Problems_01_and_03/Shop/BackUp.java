package Problems_01_and_03.Shop;

import java.io.*;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BackUp extends TimerTask {
    private String path;
    private static String backUpPath = "src/main/resources/BackUp/";

    public BackUp(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        File source = new File(path);
        File[] files = null;    //Тут будут все архивируемые файлы
        if (source.isDirectory()) {
            files = source.listFiles((x) -> x.isFile());
        } else {
            files = new File[1];
            files[0] = source;
        }

        Calendar calendar = Calendar.getInstance();
        String archiveName = calendar.get(Calendar.YEAR) + "_" +
                calendar.get(Calendar.MONTH) + "_" +
                calendar.get(Calendar.DAY_OF_MONTH) + ".zip";
        archiveName = backUpPath + archiveName;
        try (ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(archiveName))) {
            for (File file : files) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipout.putNextEntry(zipEntry);
                zipout.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

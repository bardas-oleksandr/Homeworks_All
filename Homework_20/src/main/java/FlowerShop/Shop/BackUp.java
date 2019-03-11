package FlowerShop.Shop;

import java.io.*;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BackUp extends TimerTask {
    private String sourcePath;
    private String destPath;

    public BackUp(String sourcePath, String destPath) {
        this.sourcePath = sourcePath;
        this.destPath = destPath;
    }

    @Override
    public void run() {
        File source = new File(sourcePath);
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
        archiveName = destPath + archiveName;
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

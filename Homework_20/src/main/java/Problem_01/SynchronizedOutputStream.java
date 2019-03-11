package Problem_01;

import java.io.IOException;
import java.io.OutputStream;

class SynchronizedOutputStream {
    private OutputStream out;

    SynchronizedOutputStream(OutputStream out) {
        this.out = out;
    }

    synchronized void write(byte[] results) throws IOException {
        this.out.write(results);
//        //Это можно включить для демонстрации смысла синхронизации
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        this.out.write("=====================THE END=============================\n".getBytes());
    }
}

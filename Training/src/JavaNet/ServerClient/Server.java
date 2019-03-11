package JavaNet.ServerClient;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    Server(){
        super();
        super.start();
    }

    @Override
    public void run(){
        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту

            System.out.println("Waiting for a client...");
            Socket socket = ss.accept(); //Заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!\n");

            //Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            //Конвертируем потоки в другой тип, чтобы легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while(true) {
                line = in.readUTF(); //Ожидаем пока клиент пришлет строку текста.
                System.out.println("The dumb client just sent me this line: " + line);
                System.out.println("I'm sending it back...");
                out.writeUTF(line); //Отсылаем клиенту обратно ту самую строку текста.
                out.flush(); //Заставляем поток закончить передачу данных.
                System.out.println("Waiting for the next line...");
                System.out.println();
            }
        } catch(Exception x) { x.printStackTrace(); }
    }
}
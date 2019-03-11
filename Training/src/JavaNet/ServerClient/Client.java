package JavaNet.ServerClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.*;
import java.io.*;

public class Client extends Thread {
    Client(){
        super();
        super.start();
    }

    @Override
    public void run(){
        int serverPort = 6666; //Здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; //Это IP-адрес компьютера, где исполняется наша серверная программа.
        //Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetAddress ipAddress = InetAddress.getByName(address); //Создаем объект, отображающий заданный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address +
                    " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); //Создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");

            //Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            //Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            //Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String login = null;
            String password = null;

            while (true) {
                System.out.print("Login: ");
                login = keyboard.readLine();
                System.out.print("Password: ");
                password = keyboard.readLine();
                Message message = new Message(login,password);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String line = gson.toJson(message);

                System.out.println("Sending this line to the server...");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("The server was very polite. It sent me this : " + line);
                System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.\n");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }

    }
}

package JavaNet.Socket;

import Interfaces.IProblem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Problem implements IProblem {
    @Override
    public void solve() {
        try(Socket socket = new Socket("whois.internic.net", 43)) {
            System.out.println(socket.getInetAddress());
            System.out.println(socket.getPort());
            System.out.println(socket.getLocalAddress());

            try(InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream()) {
                byte[] buff = "ukr.net\n".getBytes();

                output.write(buff);
                int c;
                while((c = input.read()) != -1){
                    System.out.print((char)c);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

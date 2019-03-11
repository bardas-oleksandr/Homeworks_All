package JavaNet.URL;

import Interfaces.IProblem;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class Problem implements IProblem {
    @Override
    public void solve() {
        try {
            URL url = new URL("http://diit.edu.ua/");
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("File: " + url.getFile());
            System.out.println("Authority: " + url.getAuthority());
            System.out.println("Query: " + url.getQuery());
            System.out.println("UserInfo: " + url.getUserInfo());
            System.out.println("Ref: " + url.getRef());
            System.out.println("ExternalForm: " + url.toExternalForm());

            System.out.println("=================================URLConnection==============================");
            URLConnection connection = url.openConnection();
            long d = connection.getDate();
            if(d != 0){
                System.out.println("Date: " + new Date(d));
            }else{
                System.out.println("Date is inaccessible");
            }

            System.out.println("ContentType: " + connection.getContentType());

            d = connection.getExpiration();
            if(d != 0){
                System.out.println("Expiration date: " + new Date(d));
            }else{
                System.out.println("Expiration date is inaccessible");
            }

            d = connection.getLastModified();
            if(d != 0){
                System.out.println("Last modified date: " + new Date(d));
            }else{
                System.out.println("Last modified date is inaccessible");
            }

            System.out.println("=================================Content==============================");
            d = connection.getContentLengthLong();
            if(d != 0){
                InputStream input = connection.getInputStream();
                int c;
                while((c = input.read()) != -1){
                    System.out.print((char)c);
                }
            }else{
                System.out.println("Content is inaccessible");
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

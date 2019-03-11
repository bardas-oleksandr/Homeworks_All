package Flowers;

import ShopExceptions.DeserializationException;

import java.io.BufferedReader;
import java.io.IOException;

abstract public class Flower {
    private String color;
    private double price;
    private double costPrice;

    public Flower(){
        this.color = null;
        this.price = 0;
        this.costPrice = 0;
    }

    public Flower(String color, double price, double costPrice){
        this.color = color;
        this.price = price;
        this.costPrice = costPrice;
    }

    void setColor(String color){
        this.color = color;
    }

    public void setPrice(double price){ this.price = price; }

    void setCostPrice(double costPrice){
        this.costPrice = costPrice;
    }

    public String getColor(){
        return this.color;
    }

    public double getPrice(){
        return this.price;
    }

    public double getCostPrice(){
        return this.costPrice;
    }

    @Override
    public String toString(){
        return new String( "Price: " + this.getPrice() + "$\t\tCost price: " + this.getCostPrice() + "$\tColor: " + this.getColor());
    }

    public String showForBuyer(){
        return new String( "Price: " + this.getPrice() + "$\t\tColor: " + this.getColor());
    }

    public String showForProvider(){
        return new String( "Costprice: " + this.getCostPrice() + "$\t\tColor: " + this.getColor());
    }

    public void parseJSON(BufferedReader reader) throws DeserializationException, IOException {
        String line = reader.readLine().trim();
        if(line.equals("{")) {
            //Определяем параметр this.color
            line = reader.readLine();
            String caption = "\"color\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            this.color = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы

            //Определяем параметр this.price
            line = reader.readLine();
            caption = "\"price\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            line = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы
            this.price = Double.parseDouble(line);

            //Определяем параметр this.color
            line = reader.readLine();
            caption = "\"costPrice\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            this.costPrice = Double.parseDouble(line);
        }else{
            throw new DeserializationException("Error during JSON-deserialization of Flower object");
        }
    }

    public void parseXML(BufferedReader reader) throws DeserializationException, IOException {
        String line = reader.readLine().trim();
        if(line.equals("<flower>")) {
            //Определяем параметр this.color
            line = reader.readLine();
            String begin = "<color>";
            String end = "</color>";
            this.color = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы

            //Определяем параметр this.price
            line = reader.readLine();
            begin = "<price>";
            end = "</price>";
            line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы
            this.price = Double.parseDouble(line);

            //Определяем параметр this.color
            line = reader.readLine();
            begin = "<costPrice>";
            end = "</costPrice>";
            line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы
            this.costPrice = Double.parseDouble(line);
        }else{
            throw new DeserializationException("Error during XML-deserialization of Flower object");
        }
    }
}

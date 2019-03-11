package Flowers;

//import Shop.DataFormat;

import java.io.*;

abstract public class Flower implements Serializable {  //Все наследники тоже будут автоматически Serializable
    private String color;
    private double price;
    private double costPrice;
    //transient private DataFormat format;

    public Flower() {
        this.color = null;
        this.price = 0;
        this.costPrice = 0;
        //this.format = null;
    }

//    public Flower(String color, double price, double costPrice, DataFormat format) {
//        this.color = color;
//        this.price = price;
//        this.costPrice = costPrice;
//        this.format = format;
//    }

    public Flower(String color, double price, double costPrice) {
        this.color = color;
        this.price = price;
        this.costPrice = costPrice;
    }

//    public void setDataFormat(DataFormat format) {
//        this.format = format;
//    }

    void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getColor() {
        return this.color;
    }

    public double getPrice() {
        return this.price;
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    @Override
    public String toString() {
        return new String("Price: " + this.getPrice() + "$\t\tCost price: " + this.getCostPrice() + "$\tColor: " + this.getColor());
    }

    public String showForBuyer() {
        return new String("Price: " + this.getPrice() + "$\t\tColor: " + this.getColor());
    }

    public String showForProvider() {
        return new String("Costprice: " + this.getCostPrice() + "$\t\tColor: " + this.getColor());
    }

//    @Override
//    public void writeExternal(ObjectOutput out) throws IOException {
//        switch (this.format) {
//            case XML:
//                this.toXML(out);
//                break;
//            case JSON:
//                this.toJSON(out);
//                break;
//        }
//    }

//    private void toXML(ObjectOutput out) throws IOException {
//        out.writeChars("\t\t\t\t\t<color>" + this.color + "</color>\n");
//        out.writeChars("\t\t\t\t\t<price>" + this.price + "</price>\n");
//        out.writeChars("\t\t\t\t\t<costPrice>" + this.costPrice + "</costPrice>\n");
//    }

//    private void toJSON(ObjectOutput out) throws IOException {
//        out.writeChars("\t\t\t\t\t\"color\":" + this.color + ",\n");
//        out.writeChars("\t\t\t\t\t\"price\":" + this.price + ",\n");
//        out.writeChars("\t\t\t\t\t\"costPrice\":" + this.costPrice + "\n");
//    }

//    @Override
//    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        String line = in.readLine();    //Из первой строки узнаем какой формат кодировки используется
//        if (line != null) {
//            line = line.trim(); //Уберем пробелы и знаки табуляции
//            if (line.indexOf("<color>") >= 0) {
//                this.parseXML(in, line);  //входной поток уходит без первой строки - <flower>
//            } else if (line.indexOf("\"color\"") >= 0) {
//                this.parseJSON(in, line);  //входной поток уходит без первой строки - {
//            } else {
//                throw new IOException("Error during deserialization of Flower object");
//            }
//        }
//    }

//    public void parseJSON(ObjectInput in, String line) throws IOException {
//        //Определяем параметр this.color
//        String caption = "\"color\":";
//        line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
//        this.color = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы
//        //Определяем параметр this.price
//        line = in.readLine();
//        caption = "\"price\":";
//        line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
//        line = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы
//        this.price = Double.parseDouble(line);
//        //Определяем параметр this.color
//        line = in.readLine();
//        caption = "\"costPrice\":";
//        line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
//        this.costPrice = Double.parseDouble(line);
//        //Читаем замыкающий символ
//        line = in.readLine().trim();
//        if(!line.equals("}") && !line.equals("},")){
//            throw new IOException("Missing \"}\" symbol in JSON-object");
//        }
//    }

//    public void parseXML(ObjectInput in, String line) throws IOException {
//        //Определяем параметр this.color
//        String begin = "<color>";
//        String end = "</color>";
//        this.color = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы
//        //Определяем параметр this.price
//        line = in.readLine();
//        begin = "<price>";
//        end = "</price>";
//        line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы
//        this.price = Double.parseDouble(line);
//        //Определяем параметр this.color
//        line = in.readLine();
//        begin = "<costPrice>";
//        end = "</costPrice>";
//        line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы
//        this.costPrice = Double.parseDouble(line);
//        //Читаем замыкающий тег
//        line = in.readLine().trim();
//        if(!line.equals("</flower>")){
//            throw new IOException("Missing </flower> in XML-object");
//        }
//    }
}

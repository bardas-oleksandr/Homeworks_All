package Problem_03.Shop;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class PriceLogger implements Observer, Serializable {
    private String loggerPath;

    public PriceLogger(String path){
        this.loggerPath = path;
    }

    @Override
    public void update(Observable o, Object arg) {
        try(FileWriter writer = new FileWriter(this.loggerPath, true)) {
            String name = ((ProdListParameters)o).name();
            String color = ((ProdListParameters)o).color();
            Double price = ((ProdListParameters)o).price();
            Date date = new Date();
            writer.append(date.toString());
            writer.append("\t");
            writer.append(name);
            writer.append("\t");
            if(name.length() < 6){
                writer.append("\t");
            }
            writer.append(color);
            if(color.length() < 6){
                writer.append("\t");
            }
            writer.append("\t- ");
            writer.append(price.toString());
            writer.append("$\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void currentPrices(){
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(this.loggerPath), StandardCharsets.UTF_8))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e){
            System.out.println("Some crap has happened. We can't show you any results.");
        }
    }
}

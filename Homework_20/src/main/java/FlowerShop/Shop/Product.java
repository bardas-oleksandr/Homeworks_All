package FlowerShop.Shop;

import java.io.*;

public class Product implements Serializable{
    private double costPrice;

    public Product() {
        this.costPrice = 0;
    }

    public Product(double costPrice){
        this.costPrice = costPrice;
    }

    double costPrice() {
        return this.costPrice;
    }

    @Override
    public boolean equals(Object other){
        return other != null &&
                other instanceof Product &&
                this.costPrice == ((Product)other).costPrice;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(ShopBundle.getString(ShopBundle.COST_PRICE));
        result.append(this.costPrice);
        result.append("$");
        return new String(result);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException{
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
    }
}

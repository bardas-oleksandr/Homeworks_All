package Problems_01_and_03.Shop;

import Interfaces.IService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

//Класс "Покупка"
public class Purchase<T extends ProductList<?>> implements Serializable {
    private ArrayList<T> purchaseList;
    private double totalPrice;

    public Purchase() {
        this.purchaseList = new ArrayList<>();
        this.totalPrice = 0;
    }

    ArrayList<T> getPurchaseList() {
        return this.purchaseList;
    }

    double clear() {
        this.purchaseList = new ArrayList<>();
        double value = this.totalPrice;
        this.totalPrice = 0;
        return value;
    }

    void add(T productList) {
        this.purchaseList.add(productList);
        this.totalPrice += productList.price()*productList.size();
    }

    double getTotalPrice() {
        return this.totalPrice;
    }

    @Override
    public String toString() {
        if (this.purchaseList != null && this.purchaseList.size() > 0) {
            StringBuilder result = new StringBuilder();
            for(T productList: this.purchaseList){
                result.append(productList);
                result.append("\t" + ShopBundle.getString(ShopBundle.SUMMARY_PRICE));
                result.append(productList.price()*productList.size());
                result.append("$\n");
            }
            result.append(ShopBundle.getString(ShopBundle.TOTAL_PRICE));
            result.append(IService.round(this.totalPrice, 2));
            result.append("$");
            return result.toString();
        } else {
            return ShopBundle.getString(ShopBundle.PURCHASE_EMPTY);
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
    }
}
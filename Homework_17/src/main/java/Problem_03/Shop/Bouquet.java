package Problem_03.Shop;

import Problem_03.Flowers.Flower;
import Interfaces.IService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Bouquet implements Serializable {
    private ArrayList<Flower> flowerSet;
    private double totalPrice;

    public Bouquet() {
        this.flowerSet = new ArrayList<>();
        this.totalPrice = 0;
    }

    public ArrayList<Flower> getBouquet() {
        return this.flowerSet;
    }

    public double clear() {
        this.flowerSet = new ArrayList<>();
        double value = this.totalPrice;
        this.totalPrice = 0;
        return value;
    }

    public void addFlower(Flower flower) {
        this.flowerSet.add(flower);
        this.totalPrice += flower.getPrice();
    }

    private void ExcludeByIndex(int index) {   //Удалить цветок по номеру в массиве. Метод приватный так как предполагается его использовать только внутри класса
        Flower flower = this.flowerSet.remove(index);
        this.totalPrice -= flower.getPrice();
    }

    public double getPrice() {
        return this.totalPrice;
    }

    @Override
    public String toString() {
        if (this.flowerSet != null && this.flowerSet.size() > 0) {
            StringBuilder tmp = new StringBuilder(this.flowerSet.get(0).showForBuyer());
            tmp.append("\t\tAmount: " + this.flowerSet.size() + "\n");
            tmp.append("Total price: " + IService.round(this.totalPrice, 2) + "$"); //Возникли сложности с количеством знаков после запятой
            return tmp.toString();
        } else {
            return "Bouquet is empty";
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
    }
}
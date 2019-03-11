package Shop;

import Flowers.Flower;

public class Bouquet {
    private Flower[] flowerSet;
    private double totalPrice;

    Bouquet() {
        this.flowerSet = null;
        this.totalPrice = 0;
    }

    Flower[] getBouquet() {
        return this.flowerSet;
    }

    void clear() {
        this.flowerSet = null;
        this.totalPrice = 0;
    }

    void addFlower(Flower flower) {
        boolean newFlower = true;
        if(this.flowerSet != null){
            for (Flower item : this.flowerSet) {   //Проверим не пытается ли кто-то добавить в букет один и тот же цветок два раза
                if (item == flower) {
                    newFlower = false;
                    break;
                }
            }
        }
        if (newFlower) {
            int size;
            if(this.flowerSet == null){
                size = 1;
                this.flowerSet = new Flower[size];
            }else{
                size = this.flowerSet.length + 1;
                Flower[] tmp = new Flower[size];
                System.arraycopy(this.flowerSet,0,tmp,0,this.flowerSet.length);
                this.flowerSet = tmp;
            }
            this.flowerSet[size - 1] = flower;
            this.totalPrice += this.flowerSet[size - 1].getPrice();
        }
    }

    public boolean excludeFlower(Class type) {  //Удалить из букета цветы определенного типа
        boolean flag = false;
        if(this.flowerSet != null){
            for (int i = 0; i < this.flowerSet.length; i++) {
                if (this.flowerSet[i].getClass().getName().equals(type.getName())) {
                    ExcludeByIndex(i--);    //i-- нужно потому что объекты смещаются на один назад при удалении.
                    if(this.flowerSet == null){
                        break;
                    }
                    flag = true;
                }
            }
        }
        return flag;
    }

    private void ExcludeByIndex(int index) {   //Удалить цветок по номеру в массиве. Метод приватный так как предполагается его использовать только внутри класса
        this.totalPrice -= this.flowerSet[index].getPrice();
        if(this.flowerSet.length > 1){
            Flower[] tmp = new Flower[this.flowerSet.length - 1];
            for (int i = 0; i < index; i++) {
                tmp[i] = this.flowerSet[i];
            }
            for (int i = index + 1; i < this.flowerSet.length; i++) {
                tmp[i - 1] = this.flowerSet[i];
            }
            this.flowerSet = tmp;
        }else{
            this.clear();
        }
    }

    public double getPrice() {
        return this.totalPrice;
    }

    @Override
    public String toString() {
        if (this.flowerSet != null) {
            StringBuilder tmp = new StringBuilder(this.flowerSet[0].showForBuyer());
            tmp.append("\t\tAmount: " + this.flowerSet.length + "\n");
            tmp.append("Total price: " + Interfaces.IConsole.round(this.totalPrice, 2) + "$"); //Возникли сложности с количеством знаков после запятой
            return tmp.toString();
        } else {
            return "Bouquet is empty";
        }
    }
}
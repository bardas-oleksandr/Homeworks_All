package Problem_03;

import Problem_03.Flowers.Flower;

public class Bouquet {
    private Flower[] flowerSet;
    private double totalPrice;

    public Bouquet() {
        this.flowerSet = null;
        this.totalPrice = 0;
    }

    public Flower[] getBouquet() {
        return this.flowerSet;
    }

    public void clear() {
        this.flowerSet = null;
        this.totalPrice = 0;
    }

    private void addFlower(Flower flower) {
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
            }else{
                size = this.flowerSet.length + 1;
            }
            Flower[] tmp = new Flower[size];
            for (int i = 0; i < size - 1; i++) {
                tmp[i] = this.flowerSet[i];
            }
            this.flowerSet = tmp;
            this.flowerSet[size - 1] = flower;
            this.totalPrice += this.flowerSet[size - 1].getPrice();
        }
    }

    public boolean excludeFlower(Class type) {  //Удалить из букета цветы определенного типа
        boolean flag = false;
        if(this.flowerSet != null){
            for (int i = 0; i < this.flowerSet.length; i++) {
                if (this.flowerSet[i].getClass().getName() == type.getName()) {
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
            String result;
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < this.flowerSet.length; i++) {
                tmp.append(this.flowerSet[i]);
                tmp.append("\n");
            }
            tmp.append("Total price: " + Interfaces.IConsole.round(this.totalPrice, 2) + "$"); //Возникли сложности с количеством знаков после запятой
            return result = tmp.toString();
        } else {
            return "Bouquet is empty";
        }
    }
}
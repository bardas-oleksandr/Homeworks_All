package Store;

import Flowers.Flower;

public class Bouquet {
    private Flower[] flowerSet;
    private int count;  //Текущее количество цветов. Фактический размер массива может быть больше
    private double totalPrice;

    public Bouquet(){
        this.flowerSet = new Flower[3]; //Начальный размер задаем на три цветка. При этом массив фактически пуст
        this.count = 0;
        this.totalPrice = 0;
    }

    public Flower[] getBouquet(){
        return this.flowerSet;
    }

    public int getCount(){
        return this.count;
    }

    public void clear(){
        this.flowerSet = new Flower[3];
        this.count = 0;
        this.totalPrice = 0;
    }

    public void addFlower(Flower flower){
        boolean newFlower = true;
        for(Flower item: this.flowerSet){   //Проверим не пытается ли кто-то добавить в букет один и тот же цветок два раза
            if(item == flower){
                newFlower = false;
                break;
            }
        }
        if(newFlower){
            if(this.flowerSet == null || this.count == this.flowerSet.length){
                Flower[] tmp = new Flower[flowerSet.length * 2 + 1];
                for(int i = 0; i < flowerSet.length; i++){
                    tmp[i] = this.flowerSet[i];
                }
                this.flowerSet = tmp;
            }
            this.flowerSet[this.count] = flower;
            this.totalPrice += this.flowerSet[this.count++].getPrice();
        }
    }

    public boolean excludeFlower(Flower flower){  //Удалить из букета цветы определенного типа
        boolean flag = false;
        for(int i = 0; i < this.count; i++){
            if(this.flowerSet[i].getClass() == flower.getClass()){
                ExcludeByIndex(i--);    //i-- нужно потому что объекты смещаются на один назад при удалении.
                flag = true;
            }
        }
        return flag;
    }

    private void ExcludeByIndex(int index){   //Удалить цветок по номеру в массиве. Метод приватный так как предполагается его использовать только внутри класса
        this.totalPrice -= this.flowerSet[index].getPrice();
        for(int i = index; i < this.count - 1; i++){
            this.flowerSet[i] = this.flowerSet[i+1];
        }
        this.flowerSet[--this.count] = null;
    }

    public double getPrice(){
        return this.totalPrice;
    }

    @Override
    public String toString(){
        if(this.count>0){
            String result;
            StringBuilder tmp = new StringBuilder();
            for(int i = 0; i < this.count; i++){
                tmp.append(this.flowerSet[i]);
                tmp.append("\n");
            }
            tmp.append("Total price: " + Interfaces.IConsole.round(this.totalPrice,2) + "$"); //Возникли сложности с количеством знаков после запятой
            return result = tmp.toString();
        }else {
            return "Bouquet is empty";
        }
    }
}
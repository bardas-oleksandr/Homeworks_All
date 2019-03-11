package Problem_01;

public class Bouquet {
    private Flower[] flowerSet;
    private int count;  //Текущее количество цветов. Фактический размер массива может быть больше
    private double totalPrice;

    Bouquet(){
        this.flowerSet = new Flower[3]; //Начальный размер задаем на три цветка. При этом массив фактически пуст
        this.count = 0;
        this.totalPrice = 0;
    }

    Flower[] getBouquet(){
        return this.flowerSet;
    }

    int getCount(){
        return this.count;
    }

    void clear(){
        this.flowerSet = new Flower[3];
        this.count = 0;
        this.totalPrice = 0;
    }

    void setBouquet(Flower[] newSet){
        for(Flower flower: newSet){
            addFlower(flower);
        }
    }

    void addFlower(Flower flower){
        boolean newFlower = true;
        for(Flower item: this.flowerSet){
            if(item == flower){
                newFlower = false;
                break;
            }
        }
        if(newFlower){
            if(this.count == this.flowerSet.length || this.flowerSet == null){
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

    boolean excludeFlower(Flower flower){  //Удалить из букета цветы определенного типа
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

    double getPrice(){
        return this.totalPrice;
    }

    @Override
    public String toString(){
        String result;
        StringBuilder tmp = new StringBuilder();
        for(int i = 0; i < this.count; i++){
            tmp.append(this.flowerSet[i]);
            tmp.append("\n");
        }
        tmp.append("Total price: " + ((int)totalPrice + ((double)Math.round((totalPrice - (int)totalPrice)*100))/100) + "$\n"); //Возникли сложности с количеством знаков после запятой
        return result = tmp.toString();
    }
}

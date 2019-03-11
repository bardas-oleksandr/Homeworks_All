package Store;

import Flowers.Flower;
import Store.FlowerStack;

public class FlowerStore extends Bouquet {
    private FlowerStack pRoot;
    private int stacksCount;

    public FlowerStore(){
        this.pRoot = null;
        this.stacksCount = 0;
    }

    private void addStack(){
        this.pRoot = new FlowerStack(this.pRoot);
        this.stacksCount++;
    }

    private FlowerStack findStack(Class flowerType, String color){ //Метод ищет стек для цветов заданного типа и заданного цвета. Если такого нет - возвращает null
        FlowerStack pCurr = this.pRoot;
        while(pCurr != null && (pCurr.getFlower().getClass() != flowerType || pCurr.getFlower().getColor().equals(color) == false)){
            pCurr = pCurr.getPNext();
        }
        return pCurr;
    }

    private int findStackIndex(Class flowerType, String color){ //Метод ищет стек для цветов заданного типа и заданного цвета. Если такого нет - возвращает null
        FlowerStack pCurr = this.pRoot;
        int index = 0;
        while(pCurr != null && (pCurr.getFlower().getClass() != flowerType || pCurr.getFlower().getColor().equals(color) == false)){
            pCurr = pCurr.getPNext();
            index++;
        }
        if(pCurr == null){
            return -1;
        }else{
            return index;
        }
    }

    private void excludeStack(int index) throws NullPointerException{
        if(this.pRoot != null){
            if(index == 0){
                this.pRoot = this.pRoot.getPNext();
            }else{
                FlowerStack pCurr = this.pRoot;
                for(int i = 0; i < index - 1; i++){
                    if(pCurr == null){
                        throw new NullPointerException();
                    }
                    pCurr = pCurr.getPNext();
                }
                if(pCurr == null || pCurr.getPNext() == null){
                    throw new NullPointerException();
                }
                pCurr.setPNext(pCurr.getPNext().getPNext());
            }
            this.stacksCount--;
        }
        else{   //Если связный список пустой, удалять нечего
            throw new NullPointerException();
        }
    }

    public void putOnStack(Class flowerType, int count, double costPrice, String color){
        if(count > 0){
            FlowerStack pCurr = this.findStack(flowerType, color);
            if(pCurr == null){
                this.addStack();
                pCurr = this.pRoot;
            }
            pCurr.addFlowers(flowerType, count, costPrice, color);
        }
    }

    public void setPrice(Class flowerType, double price, String color){
        FlowerStack pCurr = this.pRoot;
        while(pCurr != null && (pCurr.getFlower().getClass() != flowerType || pCurr.getFlower().getColor().equals(color) == false)){
            pCurr = pCurr.getPNext();
        }
        if(pCurr != null){
            pCurr.setPrice(price, color);
        }
    }

    public int getStacksCount(){
        return this.stacksCount;
    }

    public FlowerStack getPRoot(){
        return this.pRoot;
    }

    public boolean addToBouquet(Bouquet bouquet, Class flowerType, String color){
        FlowerStack pCurr = this.findStack(flowerType, color);
        if(pCurr != null && pCurr.getCount() > 0){//Если цветок запрашиваемого типа есть в хранилище
            int index = -1;
            if(pCurr.getCount() == 1){  //Если стэк освобождается, запомним его индекс, чтобы потом удалить
                index = this.findStackIndex(flowerType, color);
            }
            Flower flower=pCurr.excludeFlower();    //Извлекаем цветок со стека
            bouquet.addFlower(flower);  //Помещаем цветок в букет
            if(index!=-1){
                this.excludeStack(index);   //Удаление освободившегосяя стэка
            }
            return true;
        }else{//Если цветок запрашиваемого типа отсутствует в хранилище
            return false;
        }
    }

    public int howMany(Class flowerType, String color){
        FlowerStack pCurr = this.findStack(flowerType, color);
        if(pCurr != null){
            return pCurr.getCount();
        }else{
            return 0;
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        if(this.pRoot != null){
            FlowerStack pCurr = this.pRoot;
            for(int i = 0; i < this.stacksCount; i++){
                result.append(pCurr.toString());
                pCurr = pCurr.getPNext();
            }
        }else{
            result.append("Flower store is empty");
        }
        return new String(result);
    }
}

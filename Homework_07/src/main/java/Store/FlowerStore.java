package Store;

import Flowers.Flower;
import ShopExceptions.CountException;
import ShopExceptions.NoFlowersException;
import ShopExceptions.PriceException;

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

    private int findStackIndex(Class flowerType, String color){ //Метод ищет стек для цветов заданного типа и заданного цвета. Если такого нет - возвращает -1
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

    public FlowerStack findStackByIndex(int index){ //Метод ищет стек с заданным индексом. Если такого нет - возвращает null
        FlowerStack pCurr = this.pRoot;
        for(int i=0; i<index; i++){
            if(pCurr!=null){
                pCurr = pCurr.getPNext();
            }else{
                break;
            }

        }
        return pCurr;
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
            throw new NullPointerException("Attempt to delete flower stack from the empty flower store was made");
        }
    }

    public void putOnStack(Class flowerType, int count, double costPrice, String color) throws PriceException, CountException {
        if(costPrice <= 0){
            throw new PriceException(flowerType, color, costPrice);
        }
        if(count <= 0){
            throw new CountException(flowerType, color, count);
        }
        FlowerStack pCurr = this.findStack(flowerType, color);
        if(pCurr == null){
            this.addStack();
            pCurr = this.pRoot;
        }
        pCurr.addFlowers(flowerType, count, costPrice, color);
    }

    public void setPrice(Class flowerType, double price, String color) throws PriceException{
        if(price <= 0){
            throw new PriceException(flowerType, color, price);
        }
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

    public void addToBouquet(Bouquet bouquet, Class flowerType, String color) throws PriceException, NoFlowersException{
        try{
            FlowerStack pCurr = this.findStack(flowerType, color);
            if(pCurr.getCount() > 0){//Тут может прилететь исключение (Если pCurr==null)
                if(pCurr.getFlower().getPrice() <= 0){
                    throw new PriceException(flowerType, color, pCurr.getFlower().getPrice());
                }
                int index = -1;
                if(pCurr.getCount() == 1){  //Если стэк освобождается, запомним его индекс, чтобы потом удалить
                    index = this.findStackIndex(flowerType, color);
                }
                Flower flower=pCurr.excludeFlower();    //Извлекаем цветок со стека
                bouquet.addFlower(flower);  //Помещаем цветок в букет
                if(index!=-1){
                    this.excludeStack(index);   //Удаление освободившегося стэка
                }
            }else{//Если цветок запрашиваемого типа отсутствует в хранилище
                throw new NoFlowersException(flowerType, color);     //Бросаем исключение о том что запрашиваемых цветов нет на складе (стэк есть а цветов на нем нет). Логика программы вообще-то такого не допустит, но пусть проверка будет
            }
        }
        catch(NullPointerException e){  //Исключение может прилететь из метода getFlower() и выражения (pCurr.getCount() > 0).
            throw new NoFlowersException(flowerType, color);    //Бросаем исключение о том что запрашиваемых цветов нет на складе (нет стэка, нет и цветов)
        }
    }

    public int howMany(Class flowerType, String color) throws NoFlowersException{
        FlowerStack pCurr = this.findStack(flowerType, color);
        if(pCurr != null && pCurr.getCount() >= 0){
            return pCurr.getCount();
        }else{
            throw new NoFlowersException(flowerType, color);
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

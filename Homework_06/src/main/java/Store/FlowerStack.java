package Store;

import Flowers.Carnation;
import Flowers.Gladiolus;
import Flowers.RoseFlower;
import Flowers.Tulip;
import Flowers.Flower;

public class FlowerStack {
    private Flower[] store;
    private int count;
    private FlowerStack pNext;

    public FlowerStack(FlowerStack pNext){
        this.pNext = pNext;
        this.store = null;
        this.count = 0;
    }

    public void addFlower(Flower flower){
        if(this.store.length == this.count){
            Flower[] tmp = new Flower[(int)((double)this.store.length * 1.5 + 1)];
            for(int i = 0; i < this.count; i++){
                tmp[i] = this.store[i];
            }
            this.store = tmp;
        }
        this.store[this.count++] = flower;
    }

    public void addFlowers(Class flowerType, int count, double costPrice, String color){
        if(this.store == null){
            if(flowerType == Carnation.class){
                this.store = new Carnation[(int)(count * 1.5) + 1];
            }else if(flowerType == Gladiolus.class){
                this.store = new Gladiolus[(int)(count * 1.5) + 1];
            }else if(flowerType == RoseFlower.class){
                this.store = new RoseFlower[(int)(count * 1.5) + 1];
            }else{
                this.store = new Tulip[(int)(count * 1.5) + 1];
            }
        }else{
            if(this.store.length < this.count + count){
                Flower[] tmp = new Flower[(int)((double)(this.count + count) * 1.5 + 1)];
                for(int i = 0; i < this.count; i++){
                    tmp[i] = this.store[i];
                }
                this.store = tmp;
            }
        }
        if(flowerType == Carnation.class){
            for(int i =0; i<count; i++){
                this.addFlower(new Carnation(color, costPrice));
            }
        }else if(flowerType == Gladiolus.class){
            for(int i =0; i<count; i++){
                this.addFlower(new Gladiolus(color, costPrice));
            }
        }else if(flowerType == RoseFlower.class){
            for(int i =0; i<count; i++){
                this.addFlower(new RoseFlower(color, costPrice));
            }
        }else{
            for(int i =0; i<count; i++){
                this.addFlower(new Tulip(color, costPrice));
            }
        }
    }

    public Flower excludeFlower() throws NullPointerException{
        if(this.count > 0){
            Flower flower = getFlower();
            this.count--;
            return flower;
        }
        throw new NullPointerException();
    }

    public void setPNext(FlowerStack pNext){
        this.pNext = pNext;
    }

    public FlowerStack getPNext(){
        return this.pNext;
    }

    public Flower getFlower() throws NullPointerException{
        if(this.count > 0){
            return this.store[this.count-1];
        }
        throw new NullPointerException();
    }

    public Flower getFlower(int index) throws NullPointerException{
        if(index <= this.count - 1){
            return this.store[index];
        }
        throw new NullPointerException();
    }

    public int getCount(){
        return this.count;
    }

    public Flower[] getStore(){
        return this.store;
    }

    public void setPrice(double price, String color){
        for(int i = 0; i < this.count; i++){
            if(this.store[i].getColor() == color){
                this.store[i].setPrice(price);
            }
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder("-----------------------------------------------------------------------\n");
        for(int i = 0; i<this.count; i++){
            result.append(this.store[i].toString());
            result.append("\n");
        }
        return new String(result);
    }
}

package Problem_02.Shop;

import Problem_02.Flowers.Carnation;
import Problem_02.Flowers.Gladiolus;
import Problem_02.Flowers.RoseFlower;
import Problem_02.Flowers.Tulip;
import Problem_02.Flowers.Flower;

public class FlowerStack {
    private Flower[] stack;
    private int count;
    private FlowerStack pNext;

    public FlowerStack(FlowerStack pNext){
        this.pNext = pNext;
        this.stack = null;
        this.count = 0;
    }

    public void addFlower(Flower flower){
        if(this.stack == null){
            throw new NullPointerException("Attempt to put flower on unexisting stack was made");   //Попытка добавить цветок на несуществующий стэк
        }
        if(this.stack.length == this.count){
            Flower[] tmp = new Flower[(int)((double)this.stack.length * 1.5 + 1)];
            for(int i = 0; i < this.count; i++){
                tmp[i] = this.stack[i];
            }
            this.stack = tmp;
        }
        this.stack[this.count++] = flower;
    }

    public void addFlowers(Class flowerType, int count, double costPrice, String color){
        try{
            if(this.stack.length < this.count + count){
                Flower[] tmp = new Flower[(int)((double)(this.count + count) * 1.5 + 1)];
                for(int i = 0; i < this.count; i++){
                    tmp[i] = this.stack[i];
                }
                this.stack = tmp;
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
            }else if(flowerType == Tulip.class){
                for(int i =0; i<count; i++){
                    this.addFlower(new Tulip(color, costPrice));
                }
            }
        }
        catch(NullPointerException e){  //Исключение прилетит если была попытка добавить цветок на несуществующий стэк
            //1. Устраняем причину исключительной ситуации (создаем стэк)
            if(flowerType == Carnation.class){
                this.stack = new Carnation[(int)(count * 1.5) + 1];
            }else if(flowerType == Gladiolus.class){
                this.stack = new Gladiolus[(int)(count * 1.5) + 1];
            }else if(flowerType == RoseFlower.class){
                this.stack = new RoseFlower[(int)(count * 1.5) + 1];
            }else if(flowerType == Tulip.class){
                this.stack = new Tulip[(int)(count * 1.5) + 1];
            }
            //2. Рекурсивно перезапускаем метод
            this.addFlowers(flowerType, count, costPrice, color);
        }
    }

    public Flower excludeFlower(){
        if(this.count > 0){
            Flower flower = getFlower();
            this.count--;
            return flower;
        }
        throw new NullPointerException("Attempt to exclude flower from the empty stack was made");   //Была предпринята попытка удалить цветок с пустого стэка
    }

    public void setPNext(FlowerStack pNext){
        this.pNext = pNext;
    }

    public FlowerStack getPNext(){
        return this.pNext;
    }

    public Flower getFlower(){
        if(this.count > 0){
            return this.stack[this.count-1];
        }
        throw new NullPointerException("Attempt to get flower from empty stack was made");  //Попытка получить цветок с пустого стэка
    }

    public Flower getFlower(int index) throws NullPointerException{
        if(index <= this.count - 1){
            return this.stack[index];
        }
        throw new NullPointerException("Index of the requested flower doesn't correspond to the actual flowers count"); //Индекс запрашиваемого цветка не соответствует фактическому количеству цветов на стэке
    }

    public int getCount(){
        return this.count;
    }

    public Flower[] getStack(){
        return this.stack;
    }

    public void setPrice(double price, String color){
        for(int i = 0; i < this.count; i++){
            if(this.stack[i].getColor() == color){
                this.stack[i].setPrice(price);
            }
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder("-----------------------------------------------------------------------\n");
        for(int i = 0; i<this.count; i++){
            result.append(this.stack[i].toString());
            if(i != this.count - 1){
                result.append("\n");
            }
        }
        return new String(result);
    }
}

package Shop;

import Annotations.AnnotationContainer;
import Annotations.DefFlower;
import Flowers.*;
import ShopExceptions.*;

import java.io.*;
import java.lang.reflect.Field;

public class FlowerStore extends Bouquet implements Serializable {
    @DefFlower(flowerType = RoseFlower.class, color = "yellow", price = 1.8)
    @DefFlower(flowerType = RoseFlower.class, color = "white", price = 1.7)
    @DefFlower(flowerType = RoseFlower.class, color = "red", price = 2.0)
    @DefFlower(flowerType = Tulip.class, color = "yellow", price = 1.4)
    @DefFlower(flowerType = Tulip.class, color = "orange", price = 1.5)
    @DefFlower(flowerType = Tulip.class, color = "red", price = 1.3)
    @DefFlower(flowerType = Carnation.class, color = "red", price = 1.3)
    @DefFlower(flowerType = Gladiolus.class, color = "white", price = 1.5)
    private FlowerStack[] flowerStacks; //Вектор стэков (некоторые элементы могут быть неинициализированы)
    private int stacksCount;    //Количество инициализированных стэков (это могут быть и стэки без цветов)

    //Признак того что хранилище было инициализировано не из файла, а на основе аннотаций.
    private boolean initializedByAnno;

    public FlowerStore() {
        this.flowerStacks = null;
        this.stacksCount = 0;
        this.initializedByAnno = false;
    }

    //Возвращает вектор стэков
    public FlowerStack[] getFlowerStacks() {
        return flowerStacks;
    }

    //Возвращает текущее количество стэков в хранилище
    public int getStacksCount() {
        return this.stacksCount;
    }

    //Метод добавления в хранилище цветов заданного класса и цвета
    public void putOnStack(Class flowerType, String color, double costPrice, int count) throws PriceException, CountException {
        if (costPrice <= 0) {
            throw new PriceException(flowerType, color, costPrice);
        }
        if (count <= 0) {
            throw new CountException(flowerType, color, count);
        }
        FlowerStack stack = findStack(flowerType, color);    //Находим стэк, на который нужно положить цветы
        if (stack == null) {  //Если цветы такого вида и цвета еще не хранятся в магазине
            if (this.flowerStacks == null || this.stacksCount == this.flowerStacks.length) {   //Если нет свободных стэков, добавляем новые стэки
                expandStore();
            }
            stack = this.flowerStacks[this.stacksCount++] = new FlowerStack();
        }
        stack.addFlowers(flowerType, color, costPrice, count);
    }

    //Метод добавления готового массива цветов в хранилище
    public void putOnStack(Flower[] flowers) {
        FlowerStack stack = findStack(flowers[0].getClass(), flowers[0].getColor());    //Находим стэк, на который нужно положить цветы
        if (stack == null) {  //Если цветы такого вида и цвета еще не хранятся в магазине
            if (this.flowerStacks == null || this.stacksCount == this.flowerStacks.length) {   //Если нет свободных стэков, добавляем новые стэки
                expandStore();
            }
            stack = this.flowerStacks[this.stacksCount++] = new FlowerStack();
        }
        stack.addFlowers(flowers);
    }

    //Метод установки цены цветов заданного вида и цвета
    public void setPrice(Class flowerType, String color, double price) throws PriceException {
        if (price <= 0) {
            throw new PriceException(flowerType, color, price);
        }
        FlowerStack tmp = findStack(flowerType, color);
        if (tmp != null) {
            tmp.setPrice(price);
        }
    }

    //Метод добавления к текущему заказу цветов со стэка (цветы убираются со стэка и переносятся к заказу)
    public void addToBouquet(Bouquet bouquet, Class flowerType, String color) throws PriceException, NoFlowersException {
        try {
            FlowerStack tmp = this.findStack(flowerType, color);
            if (tmp.getCount() > 0) {//Тут может прилететь исключение NullPointerException (Если pCurr==null)
                if (tmp.getPrice() <= 0) {
                    throw new PriceException(flowerType, color, tmp.getPrice());  //Фактически логика программы не допустит такого события
                }
                Flower flower = tmp.excludeFlower();    //Извлекаем цветок со стэка
                bouquet.addFlower(flower);  //Помещаем цветок в букет
                if (tmp.getCount() == 0) {
                    int index = this.findStackIndex(flowerType, color);
                    rearrangeStore(index);  //Перестройка хранилище после освобождения стэка
                    throw new NoFlowersException(flowerType, color);
                }
            } else {//Если цветок запрашиваемого типа отсутствует в хранилище
                throw new NoFlowersException(flowerType, color);     //Бросаем исключение о том что запрашиваемых цветов нет на складе (стэк есть а цветов на нем нет). Логика программы вообще-то такого не допустит, но пусть проверка будет
            }
        } catch (NullPointerException e) {  //Исключение может прилететь из метода getFlower() и выражения (pCurr.getCount() > 0).
            throw new NoFlowersException(flowerType, color);    //Бросаем исключение о том что запрашиваемых цветов нет на складе (нет стэка, нет и цветов)
        }
    }

    //Возвращает количество цветов заданного класса и заданного цвета
    public int howMany(Class flowerType, String color) throws NoFlowersException {
        FlowerStack tmp = this.findStack(flowerType, color);
        if (tmp != null && tmp.getCount() > 0) {
            return tmp.getCount();
        } else {
            throw new NoFlowersException(flowerType, color);
        }
    }

    //Возвращает стэк по заданному индексу покупателя
    //Этот индекс представляяет из себя сквозную нумерацию стэков, на которых есть цветы и у которых установлена цена
    public FlowerStack getStackByBuyersIndex(int index) {
        for (int i = 0; i < this.stacksCount; i++) {
            if (this.flowerStacks[i].getPrice() > 0 && this.flowerStacks[i].getCount() > 0) {
                if (index == 0) {
                    return this.flowerStacks[i];
                }
                index--;
            }
        }
        return null;
    }

    //Возвращает количество категорий цветов, готовых к продаже (для которых цена выше нуля)
    public int getReadyStacks() {
        int result = 0;
        for (int i = 0; i < this.stacksCount; i++) {
            if (this.flowerStacks[i].getPrice() > 0 && this.flowerStacks[i].getCount() > 0) {
                result++;
            }
        }
        return result;
    }

    //Метод отображения хранилища цветов глазами собственника (показаны все данные)
    @Override
    public String toString() {   //Полное отображение стэка во всех деталях
        StringBuilder result = new StringBuilder();
        if (this.stacksCount > 0) {
            for (int i = 0; i < this.stacksCount; i++) {
                result.append((i + 1) + " - ");
                result.append(this.flowerStacks[i].toString());
            }
        } else {
            result.append("Flower store is empty");
        }
        return new String(result);
    }

    //Метод отображения хранилища цветов глазами покупателя. Покупатель видит:
    //1. Цену цветов без себестоимости
    //2. Только те цветы, на которые задана цена
    public String showForBuyer() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        boolean flag = true;
        if (this.stacksCount > 0) {
            for (int i = 0; i < this.stacksCount; i++) {
                if (this.flowerStacks[i].getPrice() > 0) {  //Если цена на цветы задана
                    if (this.flowerStacks[i].getCount() > 0) {    //Если стэк не пустой
                        result.append((count + 1) + " - ");
                        count++;
                    } else {
                        result.append("\t");
                    }
                    result.append(this.flowerStacks[i].showForBuyer());
                    flag = false;
                }
            }
        }
        if (this.stacksCount == 0 || flag) {   //Если магазин полностью пуст или если ни для одного цветка не задана цена
            result.append("Flower store is empty");
        }
        return new String(result);
    }

    //Метод отображения хранилища цветов глазами поставщика. Поставщик видит:
    //1. Себестоимость цветов без цены для покупателя
    public String showForProvider() { //Стэк цветов отображается без цены для покупателя
        StringBuilder result = new StringBuilder();
        if (this.stacksCount > 0) {
            for (int i = 0; i < this.stacksCount; i++) {
                result.append(this.flowerStacks[i].showForProvider());
            }
        } else {
            result.append("Flower store is empty");
        }
        return new String(result);
    }

    //Увеличивает количество стэков цветов в хранилище
    private void expandStore() {
        int newLength;
        if (this.flowerStacks == null) {
            newLength = 5;
        } else {
            newLength = (int) ((double) this.flowerStacks.length * 1.2) + 1;
        }
        FlowerStack[] tmp = new FlowerStack[newLength];
        for (int i = 0; i < this.stacksCount; i++) {
            tmp[i] = this.flowerStacks[i];
        }
        this.flowerStacks = tmp;
    }

    //Метод расширения емкости массива стэков на 1
    private void expandCapacity() {
        if (this.flowerStacks == null) {
            this.flowerStacks = new FlowerStack[1];
        } else {
            FlowerStack[] tmp = new FlowerStack[this.flowerStacks.length + 1];
            for (int i = 0; i < this.flowerStacks.length; i++) {
                tmp[i] = this.flowerStacks[i];
            }
            this.flowerStacks = tmp;
        }
    }

    //Вовращает стэк, на котором хранятся цветы заданного класса и цвета
    private FlowerStack findStack(Class flowerType, String color) {
        for (int i = 0; i < this.stacksCount; i++) {
            if (this.flowerStacks[i].getFlowerType() == flowerType && this.flowerStacks[i].getColor().equals(color)) {
                return this.flowerStacks[i];
            }
        }
        return null;
    }

    //Удаляет стэк с заданным индексом
    private void excludeStack(int index) {
        if (this.stacksCount > index) {
            if (this.stacksCount > 1) {
                FlowerStack[] tmp = new FlowerStack[this.stacksCount - 1];
                for (int i = 0; i < index; i++) {
                    tmp[i] = this.flowerStacks[i];
                }
                for (int i = index; i < this.stacksCount - 1; i++) {
                    tmp[i] = this.flowerStacks[i + 1];
                }
                this.flowerStacks = tmp;
            } else {
                this.flowerStacks = null;
            }
            this.stacksCount--;
        }
    }

    //Освободившийся стэк помещается в конец списка
    public void rearrangeStore(int index) {
        if (this.stacksCount > index) {
            FlowerStack tmp = this.flowerStacks[index];
            this.flowerStacks[index] = this.flowerStacks[this.stacksCount - 1];
            this.flowerStacks[this.stacksCount - 1] = tmp;
        }
    }

    //Метод инициализирует хранилище на основе аннотаций DefFlower
    public void initByAnnotations() {
        this.initializedByAnno = true;
        Class c = this.getClass();
        //c = FlowerStore.class;    //Можно еще так
        Field field = null;
        try {
            field = c.getDeclaredField("flowerStacks");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        AnnotationContainer container = field.getDeclaredAnnotation(AnnotationContainer.class);
        if (container != null) {
            for (DefFlower item : container.value()) {
                this.addEmptyStack(item.flowerType(), item.color(), item.price());
            }
        }
    }

    public boolean isInitByAnno() {
        return this.initializedByAnno;
    }

    //Возвращает индекс стэка с заданными параметрами
    private int findStackIndex(Class flowerType, String color) {
        int index = 0;
        for (int i = 0; i < this.stacksCount; i++) {
            if (this.flowerStacks[i].getFlowerType().equals(flowerType) && this.flowerStacks[i].getColor().equals(color)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    //Метод добавление в хранилище пустого стэка определенного типа и цвета цветов
    private void addEmptyStack(Class flowerType, String color, double price) {
        FlowerStack stack = findStack(flowerType, color);    //Находим стэк, на который нужно положить цветы
        if (stack == null) {  //Если цветы такого вида и цвета еще не хранятся в магазине
            if (this.flowerStacks == null || this.stacksCount == this.flowerStacks.length) {   //Если нет свободных стэков, добавляем новые стэки
                expandStore();
            }
            stack = this.flowerStacks[this.stacksCount++] = new FlowerStack();
        }
        stack.initEmptyStack(flowerType, color, price);
    }
}

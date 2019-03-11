package Shop;

import Flowers.Flower;
import ShopExceptions.StackOverflowException;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FlowerStack implements Serializable {
    private Flower[] flowers; //Массив цветов стэка
    private int count;      //Текущее количество цветов на стэке
    private Class flowerType;   //Тип всех цветов, хранимых на стэке
    private String color;       //Цвет всех цветов, хранимых на стэке
    private double price;       //Цена всех цветов на стэке. Всегда одинакова для всех цветов стэка

    public FlowerStack() {
        this.flowers = null;
        this.count = 0;
        this.flowerType = null;
        this.color = null;
        this.price = 0;
    }

    public int getCount() {
        return this.count;
    }

    public int capacity() {
        if (this.flowers != null) {
            return this.flowers.length;
        } else {
            return 0;
        }
    }

    public Class getFlowerType() {
        return this.flowerType;
    }

    public String getColor() {
        return this.color;
    }

    public double getPrice() {
        return this.price;
    }

    //Метод возвращает крайний цветок стэка
    public Flower getFlower() {
        if (this.count > 0) {
            return this.flowers[this.count - 1];
        }
        throw new NullPointerException("Attempt to get flower from empty stack was made");  //Попытка получить цветок с пустого стэка
    }

    //Метод возвращает цветок стэка по индексу
    public Flower getFlower(int index) {
        if (this.count > 0) {
            return this.flowers[index];
        }
        throw new NullPointerException("Attempt to get flower from empty stack was made");  //Попытка получить цветок с пустого стэка
    }

    //Метод добавления цветка на стэк. Стэк должен быть предаврительно расширен
    private void addFlower(Flower flower) throws StackOverflowException {
        if (this.flowers == null) {
            throw new NullPointerException("Attempt to put flower on unexisting flowers stack was made");   //Попытка добавить цветок на несуществующий стэк
        }
        if (this.flowers.length == this.count) {
            throw new StackOverflowException(flower.getClass(), flower.getColor());
        }
        this.flowers[this.count++] = flower;
    }

    //Метод установки цены для каждого цветка на стэке
    public void setPrice(double price) {
        this.price = price;
        for (int i = 0; i < this.count; i++) {
            this.flowers[i].setPrice(price);
        }
    }

    //Метод расширения стэка
    private void expandCapacity(int newSize) {
        Flower[] tmp = new Flower[newSize];
        for (int i = 0; i < this.count; i++) {
            tmp[i] = this.flowers[i];
        }
        this.flowers = tmp;
    }

    //Метод расширения стека на 1
    private void expandCapacity() {
        if (this.flowers == null) {
            this.flowers = new Flower[1];
        } else {
            Flower[] tmp = new Flower[this.flowers.length + 1];
            for (int i = 0; i < this.flowers.length; i++) {
                tmp[i] = this.flowers[i];
            }
            this.flowers = tmp;
        }
    }

    //Метод добавления партии цветов заданного типа и цвета в хранилище цветов.
    //Предполагается использование метода поставщиком, поэтому цена цветов не задается
    public void addFlowers(Class flowerType, String color, double costPrice, int count) {
        try {
            if (this.flowers.length < this.count + count) {
                this.expandCapacity((int) ((double) (this.count + count) * 1.5 + 1));    //Расширяем стэк если его емкости недостаточно
            }
            try {
                //Constructor constructor = flowerType.getConstructor(String.class, double.class, double.class, DataFormat.class);
                Constructor constructor = flowerType.getConstructor(String.class, double.class, double.class);
                for (int i = 0; i < count; i++) {
                    try {
                        this.addFlower((Flower) constructor.newInstance(color, this.price, costPrice));
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (StackOverflowException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {  //Исключение прилетит если была попытка добавить цветок на несуществующий стэк
            //1. Устраняем причину исключительной ситуации (создаем стэк)
            expandCapacity((int) (count * 1.5) + 1);
            //2. Устанавливаем для стэка вид цветов и цвет
            this.flowerType = flowerType;
            this.color = color;
            //3. Рекурсивно перезапускаем метод
            this.addFlowers(flowerType, color, costPrice, count);
        }
    }

    //Метод добавления готового массива цветов в хранилище цветов
    //При добавлении цена цветов в массиве корректируется в соответствии с ценой, установленной для стэка
    public void addFlowers(Flower[] newFlowers) {
        for (int i = 0; i < newFlowers.length; i++) { //Выставлем цену для каждого нового цветка в соответствии с ценой, установленной для стэка в целом
            newFlowers[i].setPrice(this.price);
        }
        try {
            if (this.flowers.length < this.count + newFlowers.length) {
                this.expandCapacity((int) ((double) (this.count + newFlowers.length) * 1.5 + 1));    //Расширяем стэк если его емкости недостаточно
            }
            for (int i = 0; i < newFlowers.length; i++) {
                try {
                    this.addFlower(newFlowers[i]);
                } catch (StackOverflowException e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {  //Исключение прилетит если была попытка добавить цветок на несуществующий стэк
            //1. Устраняем причину исключительной ситуации (создаем стэк)
            expandCapacity((int) (count * 1.5) + 1);
            //2. Рекурсивно перезапускаем метод
            this.addFlowers(newFlowers);
        }
    }

    public Flower excludeFlower() {
        if (this.count > 0) {
            Flower flower = getFlower();
            this.flowers[--this.count] = null;
            return flower;
        }
        throw new NullPointerException("Attempt to exclude flower from the empty stack was made");   //Была предпринята попытка удалить цветок с пустого стэка
    }

    //Метод полного отображения стэка цветов
    public String toString() {
        if (this.flowerType != null) {
            String name = this.flowerType.getName();
            name = name.substring(8);
            StringBuilder str;
            if (name.length() > 6) {   //Для того чтобы все было ровно
                str = new StringBuilder(name + "\t\tColor: " + this.color);
            } else {
                str = new StringBuilder(name + "\t\t\tColor: " + this.color);
            }
            if (this.color.length() > 4) {   //Для того чтобы все было ровно
                str.append("\t\tPrice: " + this.price + "\t\tAmount: " + this.count + "\n");
            } else {
                str.append("\t\t\tPrice: " + this.price + "\t\tAmount: " + this.count + "\n");
            }

            for (int i = 0; i < this.count; i++) {
                str.append("\t" + this.flowers[i] + "\n");
            }
            str.append("------------------------------------------------------------------------\n");
            return new String(str);
        } else {
            return new String("The flowerstack is empty");
        }
    }

    //Метод отображения стэка цветов глазами покупателя (он не видит себестоимости цветов и видит только цветы с установленной ценой)
    public String showForBuyer() {
        if (this.count > 0) {
            String name = this.flowerType.getName();
            name = name.substring(8);
            StringBuilder str;
            if (name.length() > 6) {   //Для того чтобы все было ровно
                str = new StringBuilder(name + "\t\tColor: " + this.color);
            } else {
                str = new StringBuilder(name + "\t\t\tColor: " + this.color);
            }
            if (this.color.length() > 4) {   //Для того чтобы все было ровно
                str.append("\t\tPrice: " + this.price + "\t\tAmount: " + this.count + "\n");
            } else {
                str.append("\t\t\tPrice: " + this.price + "\t\tAmount: " + this.count + "\n");
            }
            return new String(str);
        }
        if (this.flowerType != null) {
            String name = this.flowerType.getName();
            name = name.substring(8);
            StringBuilder str;
            if (name.length() > 6) {   //Для того чтобы все было ровно
                str = new StringBuilder(name + "\t\tColor: " + this.color);
            } else {
                str = new StringBuilder(name + "\t\t\tColor: " + this.color);
            }
            if (this.color.length() > 4) {   //Для того чтобы все было ровно
                str.append("\t\tPrice: " + this.price + "\t\tTemporary are not available\n");
            } else {
                str.append("\t\t\tPrice: " + this.price + "\t\tTemporary are not available\n");
            }
            return new String(str);
        } else {
            return new String("The flowerstack is empty");
        }
    }

    //Метод отображения стэка цветов глазами поставщика (он не видит цены цветов дл покупателя)
    public String showForProvider() {
        if (this.flowerType != null) {
            String name = this.flowerType.getName();
            name = name.substring(8);
            StringBuilder str;
            if (name.length() > 6) {   //Для того чтобы все было ровно
                str = new StringBuilder(name + "\t\tColor: " + this.color);
            } else {
                str = new StringBuilder(name + "\t\t\tColor: " + this.color);
            }
            if (this.color.length() > 4) {   //Для того чтобы все было ровно
                str.append("\t\tAmount: " + this.count + "\n");
            } else {
                str.append("\t\t\tAmount: " + this.count + "\n");
            }
            return new String(str);
        } else {
            return new String("The flowerstack is empty");
        }
    }

    public void initEmptyStack(Class flowerType, String color, double price) {
        this.color = color;
        this.flowerType = flowerType;
        this.price = price;
    }
}
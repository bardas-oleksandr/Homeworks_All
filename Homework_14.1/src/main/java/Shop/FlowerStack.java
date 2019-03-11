package Shop;

import Flowers.Flower;
import ShopExceptions.DeserializationException;
import ShopExceptions.StackOverflowException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FlowerStack {
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

    public int capacity(){
        if(this.flowers != null){
            return this.flowers.length;
        }else{
            return 0;
        }
    }

    public Class getFlowerType(){
        return this.flowerType;
    }

    public String getColor(){
        return this.color;
    }

    public double getPrice(){
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
    private void expandStack(int newSize) {
        Flower[] tmp = new Flower[newSize];
        for (int i = 0; i < this.count; i++) {
            tmp[i] = this.flowers[i];
        }
        this.flowers = tmp;
    }

    //Метод добавления партии цветов заданного типа и цвета в хранилище цветов.
    //Предполагается использование метода поставщиком, поэтому цена цветов не задается
    public void addFlowers(Class flowerType, String color, double costPrice, int count) {
        try {
            if (this.flowers.length < this.count + count) {
                this.expandStack((int) ((double) (this.count + count) * 1.5 + 1));    //Расширяем стэк если его емкости недостаточно
            }
            try {
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
            expandStack((int) (count * 1.5) + 1);
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
        for(int i = 0; i < newFlowers.length; i++){ //Выставлем цену для каждого нового цветка в соответствии с ценой, установленной для стэка в целом
            newFlowers[i].setPrice(this.price);
        }
        try {
            if (this.flowers.length < this.count + newFlowers.length) {
                this.expandStack((int) ((double) (this.count + newFlowers.length) * 1.5 + 1));    //Расширяем стэк если его емкости недостаточно
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
            expandStack((int) (count * 1.5) + 1);
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
            if(name.length() > 6){   //Для того чтобы все было ровно
                str = new StringBuilder(name + "\t\tColor: " + this.color);
            }else{
                str = new StringBuilder(name+ "\t\t\tColor: " + this.color);
            }
            if(this.color.length() > 4){   //Для того чтобы все было ровно
                str.append("\t\tPrice: " + this.price + "\t\tAmount: " + this.count + "\n");
            }else{
                str.append("\t\t\tPrice: " + this.price + "\t\tAmount: " + this.count + "\n");
            }

            for(int i = 0; i < this.count; i++){
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
            if(name.length() > 6){   //Для того чтобы все было ровно
                str = new StringBuilder(name + "\t\tColor: " + this.color);
            }else{
                str = new StringBuilder(name+ "\t\t\tColor: " + this.color);
            }
            if(this.color.length() > 4){   //Для того чтобы все было ровно
                str.append("\t\tPrice: " + this.price + "\t\tAmount: " + this.count + "\n");
            }else{
                str.append("\t\t\tPrice: " + this.price + "\t\tAmount: " + this.count + "\n");
            }
            return new String(str);
        }if(this.flowerType != null){
            String name = this.flowerType.getName();
            name = name.substring(8);
            StringBuilder str;
            if(name.length() > 6){   //Для того чтобы все было ровно
                str = new StringBuilder(name + "\t\tColor: " + this.color);
            }else{
                str = new StringBuilder(name+ "\t\t\tColor: " + this.color);
            }
            if(this.color.length() > 4){   //Для того чтобы все было ровно
                str.append("\t\tPrice: " + this.price + "\t\tTemporary are not available\n");
            }else{
                str.append("\t\t\tPrice: " + this.price + "\t\tTemporary are not available\n");
            }
            return new String(str);
        }else {
            return new String("The flowerstack is empty");
        }
    }

    //Метод отображения стэка цветов глазами поставщика (он не видит цены цветов дл покупателя)
    public String showForProvider() {
        if (this.flowerType != null) {
            String name = this.flowerType.getName();
            name = name.substring(8);
            StringBuilder str;
            if(name.length() > 6){   //Для того чтобы все было ровно
                str = new StringBuilder(name + "\t\tColor: " + this.color);
            }else{
                str = new StringBuilder(name+ "\t\t\tColor: " + this.color);
            }
            if(this.color.length() > 4){   //Для того чтобы все было ровно
                str.append("\t\tAmount: " + this.count + "\n");
            }else{
                str.append("\t\t\tAmount: " + this.count + "\n");
            }
            return new String(str);
        } else{
            return new String("The flowerstack is empty");
        }
    }

    public void initEmptyStack(Class flowerType, String color, double price){
        this.color = color;
        this.flowerType = flowerType;
        this.price = price;
    }

    public void parseJSON(BufferedReader reader) throws DeserializationException, IOException {
        String line = reader.readLine().trim();
        if(line.equals("{")){
            //Определяем параметр this.count
            line = reader.readLine();
            String caption = "\"count\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            line = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы
            this.count = Integer.parseInt(line);

            //Определяем параметр this.flowerType
            line = reader.readLine();
            caption = "\"flowerType\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            line = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы
            try {
                this.flowerType = Class.forName(line);
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + line + " was not found");
                e.printStackTrace();
            }

            //Определяем параметр this.color
            line = reader.readLine();
            caption = "\"color\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            this.color = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы

            //Определяем параметр this.price
            line = reader.readLine();
            caption = "\"price\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            line = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы
            this.price = Double.parseDouble(line);

            //Если на стєке есть цветы, надо инициализировать массив и цветы в этом массиве
            if(this.count > 0){
                line = reader.readLine();   //Тут мы должны получить строку "flowers": [
                if (line.indexOf("\"flowers\": [") >= 0) {
                    //За каждый цикл массива добавляется новый цветок
                    while (!(line = reader.readLine().trim()).equals("]")) {    //Если новый виток цикла начинается с символа "]" - это конец массива
                        if(!line.equals("null")){
                            //Создадим объект класса StringBuilder, в который положим всю информацию об очередном цветке
                            StringBuilder builder = new StringBuilder(line);    //Помещаем в builder символ начала объекта - {
                            builder.append("\n");
                            int opened = 1; //Количество открытых фигурных скобок
                            boolean newFlower = true;   //Признак непустой ячейки памяти в массиве цветов
                            line = reader.readLine().trim();
                            if (line.equals("null")) {
                                newFlower = false;    //Ячейка памяти в массиве цветов пустая
                            }
                            builder.append(line);   //Помещаем вторую строку объекта
                            builder.append("\n");
                            while (opened != 0) {
                                line = reader.readLine().trim();
                                if (line.equals("{")) {
                                    opened += 1;
                                }
                                if (line.equals("}") || line.equals("},")) {
                                    opened -= 1;
                                }
                                builder.append(line);
                                builder.append("\n");
                            }
                            //Увеличиваем массив цветов на 1
                            if(this.flowers == null){
                                this.flowers = new Flower[1];
                            }else{
                                Flower[] tmp = new Flower[this.flowers.length + 1];
                                for (int i = 0; i < this.flowers.length; i++) {
                                    tmp[i] = this.flowers[i];
                                }
                                this.flowers = tmp;
                            }
                            if (newFlower) {   //Если ячейка в массиве цветов не пустая, создадим новый цветок
                                try {
                                    this.flowers[this.flowers.length - 1] = (Flower)this.flowerType.newInstance();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                //Создаем новый поток, в котором будет информация для формирования стэка с цветами
                                try (BufferedReader flowerReader = new BufferedReader(
                                        new InputStreamReader(
                                                new ByteArrayInputStream(new String(builder).getBytes())))) {
                                    this.flowers[this.flowers.length - 1].parseJSON(flowerReader);    //Стэк будет сам себя инициализировать
                                }
                            }
                        }else{
                            //Если данные полей this.count и this.flowers не соответсвуют друг другу
                            if(this.count != 0){
                                throw new DeserializationException("Error during JSON-deserialization of FlowerStack object");
                            }
                        }
                    }
                }else{
                    throw new DeserializationException("Error during JSON-deserialization of FlowerStack object");
                }
            }
        }else{
            throw new DeserializationException("Error during JSON-deserialization of FlowerStack object");
        }
    }

    public void parseXML(BufferedReader reader) throws DeserializationException, IOException {
        String line = reader.readLine().trim();
        if(line.equals("<flowerStack>")){
            //Определяем параметр this.count
            line = reader.readLine();
            String begin = "<count>";
            String end = "</count>";
            line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы
            this.count = Integer.parseInt(line);

            //Определяем параметр this.flowerType
            line = reader.readLine();
            begin = "<flowerType>";
            end = "</flowerType>";
            line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы
            try {
                this.flowerType = Class.forName(line);
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + line + " was not found");
                e.printStackTrace();
            }

            //Определяем параметр this.color
            line = reader.readLine();
            begin = "<color>";
            end = "</color>";
            this.color = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы

            //Определяем параметр this.price
            line = reader.readLine();
            begin = "<price>";
            end = "</price>";
            line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный, конечный теги и лишние пробелы
            this.price = Double.parseDouble(line);

            //Если на стєке есть цветы, надо инициализировать массив и цветы в этом массиве
            if(this.count > 0){
                line = reader.readLine();   //Тут мы должны получить тег <flowers>
                if (line.indexOf("<flowers>") >= 0) {
                    //За каждый цикл массива добавляется новый цветок
                    while (!(line = reader.readLine().trim()).equals("</flowers>")) {    //пока не встртим тег конца массива
                        if(!line.equals("null")){
                            //Создадим объект класса StringBuilder, в который положим всю информацию об очередном цветке
                            StringBuilder builder = new StringBuilder(line);    //Помещаем в builder начальный тег
                            builder.append("\n");
                            boolean newFlower = true;   //Признак непустой ячейки памяти в массиве цветов
                            line = reader.readLine().trim();
                            if (line.equals("null")) {
                                newFlower = false;    //Ячейка памяти в массиве цветов пустая
                            }
                            builder.append(line);   //Помещаем вторую строку объекта
                            builder.append("\n");
                            do {
                                line = reader.readLine().trim();
                                builder.append(line);
                                builder.append("\n");
                            }while (!line.equals("</flower>"));
                            //Увеличиваем массив цветов на 1
                            if(this.flowers == null){
                                this.flowers = new Flower[1];
                            }else{
                                Flower[] tmp = new Flower[this.flowers.length + 1];
                                for (int i = 0; i < this.flowers.length; i++) {
                                    tmp[i] = this.flowers[i];
                                }
                                this.flowers = tmp;
                            }
                            if (newFlower) {   //Если ячейка в массиве цветов не пустая, создадим новый цветок
                                try {
                                    this.flowers[this.flowers.length - 1] = (Flower)this.flowerType.newInstance();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                //Создаем новый поток, в котором будет информация для формирования стэка с цветами
                                try (BufferedReader flowerReader = new BufferedReader(
                                        new InputStreamReader(
                                                new ByteArrayInputStream(new String(builder).getBytes())))) {
                                    this.flowers[this.flowers.length - 1].parseXML(flowerReader);    //Стэк будет сам себя инициализировать
                                }
                            }
                        }else{
                            //Если данные полей this.count и this.flowers не соответсвуют друг другу
                            if(this.count != 0){
                                throw new DeserializationException("Error during XML-deserialization of FlowerStack object");
                            }
                        }
                    }
                }else{
                    throw new DeserializationException("Error during XML-deserialization of FlowerStack object");
                }
            }
        }else{
            throw new DeserializationException("Error during XML-deserialization of FlowerStack object");
        }
    }
}

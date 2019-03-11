package Shop;

import Annotations.AnnotationContainer;
import Annotations.DefFlower;
import Flowers.*;
import ShopExceptions.*;

import java.io.*;
import java.lang.reflect.Field;

public class FlowerStore extends Bouquet implements Externalizable {
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
    transient private DataFormat format;  //Формат, в котором производится сохранение данных

    //Признак того что хранилище было инициализировано не из файла, а на основе аннотаций.
    //Этот параметр помогает определить в каком случае сразу после отработки метода readObject, надо запустить метод writeObject
    private boolean initializedByAnno;

    public FlowerStore() {
        this.flowerStacks = null;
        this.stacksCount = 0;
        this.format = null;
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
        stack.setDataFormat(this.format);
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
        stack.setDataFormat(this.format);
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

    //Метод устанавливает в каком формате будут записываться данные в файл - в XML или JSON
    //Плюс перезаписывает данные если произошла смена формата
    public void setDataFormat(DataFormat format) {
        this.format = format;
        for (int i = 0; i < this.stacksCount; i++) {
            this.flowerStacks[i].setDataFormat(format);
        }
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
        stack.setDataFormat(this.format);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        switch (this.format) {
            case XML:
                this.toXML(out);
                break;
            case JSON:
                this.toJSON(out);
                break;
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.format = null;
        String line = in.readLine();    //Из первой строки узнаем какой формат кодировки был использован ранее
        if (line != null) {
            line = line.trim(); //Уберем пробелы и знаки табуляции
            if (line.indexOf("<?") == 0) {  //Возможно надо учесть "\uEDACԀ"
                this.format = DataFormat.XML;
            } else if (line.equals("{")) {    //Возможно надо учесть "\uEDACԀ"
                this.format = DataFormat.JSON;
            } else {
                throw new IOException("Error while deserialization of FlowerStore object");
            }
        }
        if (this.format != null) {
            switch (format) {
                case XML: {
                    this.setDataFormat(DataFormat.XML);
                    this.parseXML(in);  //входной поток уходит без первой строки - <?xml version="1.1" encoding="UTF-8" ?>
                }
                break;
                case JSON: {
                    this.setDataFormat(DataFormat.JSON);
                    this.parseJSON(in);  //входной поток уходит без первой строки - {
                }
                break;
            }
        } else {  //Если файл был пуст, значение this.format == null. Тогда инициализация выполняется по аннотациям @DefFlower (ДЗ 11)
            this.setDataFormat(DataFormat.JSON);
            this.initByAnnotations();
        }
    }

    //Метод записи данных хранилища в файл в формате JSON
    private void toJSON(ObjectOutput out) throws IOException {
        out.writeChars("{\n");
        out.writeChars("\t\"stacksCount\":" + this.stacksCount + ",\n");
        out.writeChars("\t\"flowerStacks\": [");
        if (this.flowerStacks != null) {
            out.writeChars("\n");
            for (int i = 0; i < this.flowerStacks.length; i++) {
                if (this.flowerStacks[i] != null) {
                    out.writeChars("\t\t{\n");
                    this.flowerStacks[i].writeExternal(out);
                    out.writeChars("\t\t}");
                } else {  //Если указатель на отдельный стэк не инициализирована
                    out.writeChars("\t\tnull");
                }
                //Нужна ли запятая в конце строки
                if (i == this.flowerStacks.length - 1) {
                    out.writeChars("\n");
                } else {
                    out.writeChars(",\n");
                }
            }
            out.writeChars("\t]\n");
        } else {  //Если указатель на массив стэков равен null
            out.writeChars("]\n");
        }
        out.writeChars("}\n");
    }

    //Метод записи данных хранилища в файл в формате XML
    private void toXML(ObjectOutput out) throws IOException {
        out.writeChars("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>\n");
        out.writeChars("<flowerStore>\n");
        out.writeChars("\t<stacksCount>" + this.stacksCount + "</stacksCount>\n");
        out.writeChars("\t<flowerStacks>");
        if (this.flowerStacks != null) {
            out.writeChars("\n");
            for (int i = 0; i < this.flowerStacks.length; i++) {
                out.writeChars("\t\t<flowerStack>");
                if (this.flowerStacks[i] != null) {
                    out.writeChars("\n");
                    this.flowerStacks[i].writeExternal(out);
                    out.writeChars("\t\t</flowerStack>\n");
                } else {  //Если указатель на отдельный стэк не инициализирована
                    out.writeChars("null</flowerStack>\n");
                }
            }
            out.writeChars("\t</flowerStacks>\n");
        } else {  //Если указатель на массив стэков не инициализирован
            out.writeChars("null</flowerStacks>\n");
        }
        out.writeChars("</flowerStore>\n");
    }

    //Метод инициализации хранилища на основе данных из файла в формате JSON
    private void parseJSON(ObjectInput in) throws IOException, ClassNotFoundException {
        //Определяем параметр this.stacksCount
        String line = in.readLine().trim();
        String caption = "\"stacksCount\":";
        line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
        line = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы
        this.stacksCount = Integer.parseInt(line);
        line = in.readLine();   //Тут мы должны получить строку "flowerStacks": [
        caption = "\"flowerStacks\": [";
        if (line.indexOf(caption) >= 0) { //Проверка правильности структуры
            line = line.substring(line.indexOf(caption) + caption.length()).trim();
            if (!line.equals("]")) {  //Если массив стэков не пустой
                //За каждый цикл массива добавляется новый стэк с цветами
                while (!(line = in.readLine().trim()).equals("]")) {    //Если новый виток цикла начинается с символа "]" - это конец массива
                    this.expandCapacity();  //Увеличиваем массив стэков на 1
                    if (line.equals("{")) {
                        int index = this.flowerStacks.length - 1;
                        this.flowerStacks[index] = new FlowerStack();
                        this.flowerStacks[index].readExternal(in);
                    }
                }
            } else {
                //Если данные полей this.flowerStacks и this.stacksCount не соответсвуют друг другу
                if (this.stacksCount != 0) {
                    throw new IOException("Error during JSON-deserialization of FlowerStore object");
                }
            }
            //Читаем замыкающий символ
            line = in.readLine().trim();
            if(!line.equals("}") && !line.equals("},")){
                throw new IOException("Missing \"}\" symbol in JSON-object");
            }
        } else {
            throw new IOException("Error while JSON-deserialization of FlowerStore object");
        }
    }

    //Метод десериализации хранилища на основе данных из файла в формате XML
    private void parseXML(ObjectInput in) throws IOException, ClassNotFoundException {
        String line = in.readLine().trim();
        if (line.equals("<flowerStore>")) {  //Объект XML должне начинаться с <flowerStore>
            //Определяем параметр this.stacksCount
            line = in.readLine();
            String begin = "<stacksCount>";
            String end = "</stacksCount>";
            line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный и конечный теги и лишние пробелы
            this.stacksCount = Integer.parseInt(line);

            line = in.readLine();   //Тут мы должны получить строку <flowerStacks>
            String caption = "<flowerStacks>";
            if (line.indexOf(caption) >= 0) {
                caption = "</flowerStacks>";
                if(line.indexOf(caption) == -1){   //Если массив стеков не пустой
                    //За каждый цикл массива добавляется новый стэк с цветами
                    while (!(line = in.readLine().trim()).equals("</flowerStacks>")) {    //Пока не найдем конечный тэг
                        caption = "<flowerStack>";
                        if (line.indexOf(caption) >= 0) {
                            this.expandCapacity();  //Увеличиваем массив стэков на 1
                            caption = "</flowerStack>";
                            if(line.indexOf(caption) == -1){   //Если элемент массива стеков не пустой
                                int index = this.flowerStacks.length - 1;
                                this.flowerStacks[index] = new FlowerStack();
                                this.flowerStacks[index].readExternal(in);
                            }
                        }else{
                            throw new IOException("Error during XML-deserialization of FlowerStore object");
                        }
                    }
                }
                //Читаем замыкающий тег
                line = in.readLine().trim();
                if(!line.equals("</flowerStore>")){
                    throw new IOException("Missing </flowerStore> in XML-object");
                }
            } else {
                throw new IOException("Error during XML-deserialization of FlowerStore object");
            }
        } else {
            throw new IOException("Error during XML-deserialization of FlowerStore object");
        }
    }

    public void synchronizeFormat() {
        this.setDataFormat(this.format);
    }
}

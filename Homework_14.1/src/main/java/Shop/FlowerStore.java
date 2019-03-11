package Shop;

import Annotations.AnnotationContainer;
import Annotations.DefFlower;
import Flowers.*;
import ShopExceptions.*;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

public class FlowerStore extends Bouquet {
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
    private String path;    //Путь, по которому хранятся данные хранилища
    private DataFormat format;  //Формат, в котором производится сохранение данных

    public FlowerStore() throws IOException, DeserializationException {
        this.flowerStacks = null;
        this.stacksCount = 0;
        this.path = new String("src/main/resources/data.txt");
        this.format = DataFormat.JSON;  //Фактически значение будет потом переопределено в методе this.parse();
        this.parse();    //Инициализируем хранилище цветов данными из файла
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
        this.writeToFile();
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
        this.writeToFile();
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
        this.writeToFile();
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
        this.writeToFile();
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

    //Приватные методы для внутреннего использования====================================================================
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
        this.writeToFile();
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

    private void initByAnnotations() {
        Class c = this.getClass();
        //c = FlowerStore.class;    //Можно еще так
        Field field = null;
        try {
            field = c.getDeclaredField("flowerStacks");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        AnnotationContainer container = field.getDeclaredAnnotation(AnnotationContainer.class);
        for (DefFlower item : container.value()) {
            this.addEmptyStack(item.flowerType(), item.color(), item.price());
        }
    }

    //Метод инициализации хранилища данными из файла
    private void parse() throws DeserializationException {
        this.format = null;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(this.path), StandardCharsets.UTF_8))) {
            String line = null;
            line = reader.readLine();    //Из первой строки узнаем какой формат кодировки был использован ранее
            if (line != null) {
                line = line.trim(); //Уберем пробелы и знаки табуляции
                if (line.indexOf("<?") == 0) {    //Если строка начинается с подстроки "<?", то это формат XML
                    this.format = DataFormat.XML;
                } else {
                    this.format = DataFormat.JSON;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.format != null) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(this.path), StandardCharsets.UTF_8))) {
                switch (format) {
                    case XML: {
                        this.format = DataFormat.XML;
                        this.parseXML(reader);
                    }
                    break;
                    case JSON: {
                        this.format = DataFormat.JSON;
                        this.parseJSON(reader);
                    }
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {  //Если файл был пуст, значение this.format == null. Тогда инициализация выполняется по аннотациям @DefFlower (ДЗ 11)
            this.format = DataFormat.JSON;
            this.initByAnnotations();
        }
    }

    //Метод инициализации хранилища на основе данных из файла в формате JSON
    private void parseJSON(BufferedReader reader) throws DeserializationException, IOException {
        String line = reader.readLine().trim();
        if (line.equals("{")) {  //Объект JSON должне начинаться со знака "{"
            //Определяем параметр this.stacksCount
            line = reader.readLine();
            String caption = "\"stacksCount\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            line = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы
            this.stacksCount = Integer.parseInt(line);

            //Определяем параметр this.path
            line = reader.readLine();
            caption = "\"path\":";
            line = line.substring(line.indexOf(caption) + caption.length()).trim(); //Уберем заголовок поля и лишние пробелы
            this.path = line.substring(0, line.indexOf(',')).trim(); //Уберем запятую в конце строки и лишние пробелы

            line = reader.readLine();   //Тут мы должны получить строку "flowerStacks": [
            if (line.indexOf("\"flowerStacks\": [") >= 0) {
                //За каждый цикл массива добавляется новый стэк с цветами
                while (!(line = reader.readLine().trim()).equals("]")) {    //Если новый виток цикла начинается с символа "]" - это конец массива
                    if(!line.equals("null")){   //Если массив this.flowerStacks != null
                        //Создадим объект класса StringBuilder, в который положим всю информацию об очередном стэке
                        StringBuilder builder = new StringBuilder(line);    //Помещаем в builder символ начала объекта - {
                        builder.append("\n");
                        int opened = 1; //Количество открытых фигурных скобок
                        boolean newStack = true;   //Признак непустой ячейки памяти в массиве стэков
                        line = reader.readLine().trim();
                        if (line.equals("null")) {
                            newStack = false;    //Ячейка памяти в массиве стэков пустая
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
                        //Увеличиваем массив стэков на 1
                        if(this.flowerStacks == null){
                            this.flowerStacks = new FlowerStack[1];
                        }else{
                            FlowerStack[] tmp = new FlowerStack[this.flowerStacks.length + 1];
                            for (int i = 0; i < this.flowerStacks.length; i++) {
                                tmp[i] = this.flowerStacks[i];
                            }
                            this.flowerStacks = tmp;
                        }
                        if (newStack) {   //Если ячейка в массиве стэков не пустая, создадим новый стэк
                            this.flowerStacks[this.flowerStacks.length - 1] = new FlowerStack();
                            //Создаем новый поток, в котором будет информация для формирования стэка с цветами
                            try (BufferedReader stackReader = new BufferedReader(
                                    new InputStreamReader(
                                            new ByteArrayInputStream(new String(builder).getBytes())))) {
                                this.flowerStacks[this.flowerStacks.length - 1].parseJSON(stackReader);    //Стэк будет сам себя инициализировать
                            }
                        }
                    } else{
                        //Если данные полей this.flowerStacks и this.stacksCount не соответсвуют друг другу
                        if(this.stacksCount != 0){
                            throw new DeserializationException("Error during JSON-deserialization of FlowerStore object");
                        }
                    }
                }
            } else {
                throw new DeserializationException("Error during JSON-deserialization of FlowerStore object");
            }
        } else {
            throw new DeserializationException("Error during JSON-deserialization of FlowerStore object");
        }
    }

    //Метод инициализации хранилища на основе данных из файла в формате XML
    private void parseXML(BufferedReader reader) throws DeserializationException, IOException {
        String line = reader.readLine().trim();
        if(line.startsWith("<?") && line.endsWith("?>")){
            line = reader.readLine().trim();
            if (line.equals("<flowerStore>")) {  //Объект XML должне начинаться с <flowerStore>
                //Определяем параметр this.stacksCount
                line = reader.readLine();
                String begin = "<stacksCount>";
                String end = "</stacksCount>";
                line = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный и конечный теги и лишние пробелы
                this.stacksCount = Integer.parseInt(line);

                //Определяем параметр this.path
                line = reader.readLine();
                begin = "<path>";
                end = "</path>";
                this.path = line.substring(line.indexOf(begin) + begin.length(), line.indexOf(end)).trim(); //Уберем начальный и конечный теги и лишние пробелы

                line = reader.readLine();   //Тут мы должны получить строку <flowerStacks>
                if (line.indexOf("<flowerStacks>") >= 0) {
                    //За каждый цикл массива добавляется новый стэк с цветами
                    while (!(line = reader.readLine().trim()).equals("</flowerStacks>")) {    //Пока не найдем конечный тэг
                        if(!line.equals("null")){   //Если массив this.flowerStacks != null
                            //Создадим объект класса StringBuilder, в который положим всю информацию об очередном стэке
                            StringBuilder builder = new StringBuilder(line);    //Помещаем в builder тег начала стека
                            builder.append("\n");
                            boolean newStack = true;   //Признак непустой ячейки памяти в массиве стэков
                            line = reader.readLine().trim();
                            if (line.equals("null")) {
                                newStack = false;    //Ячейка памяти в массиве стэков пустая
                            }
                            builder.append(line);   //Помещаем вторую строку объекта
                            builder.append("\n");
                            do{
                                line = reader.readLine().trim();
                                builder.append(line);
                                builder.append("\n");
                            }while(!line.equals("</flowerStack>"));
                            //Увеличиваем массив стэков на 1
                            if(this.flowerStacks == null){
                                this.flowerStacks = new FlowerStack[1];
                            }else{
                                FlowerStack[] tmp = new FlowerStack[this.flowerStacks.length + 1];
                                for (int i = 0; i < this.flowerStacks.length; i++) {
                                    tmp[i] = this.flowerStacks[i];
                                }
                                this.flowerStacks = tmp;
                            }
                            if (newStack) {   //Если ячейка в массиве стэков не пустая, создадим новый стэк
                                this.flowerStacks[this.flowerStacks.length - 1] = new FlowerStack();
                                //Создаем новый поток, в котором будет информация для формирования стэка с цветами
                                try (BufferedReader stackReader = new BufferedReader(
                                        new InputStreamReader(
                                                new ByteArrayInputStream(new String(builder).getBytes())))) {
                                    this.flowerStacks[this.flowerStacks.length - 1].parseXML(stackReader);    //Стэк будет сам себя инициализировать
                                }
                            }
                        } else{
                            //Если данные полей this.flowerStacks и this.stacksCount не соответсвуют друг другу
                            if(this.stacksCount != 0){
                                throw new DeserializationException("Error during XML-deserialization of FlowerStore object");
                            }
                        }
                    }
                } else {
                    throw new DeserializationException("Error during XML-deserialization of FlowerStore object");
                }
            } else {
                throw new DeserializationException("Error during XML-deserialization of FlowerStore object");
            }
        }else{
            throw new SerializationException("Error during XML-deserialization of FlowerStore object");
        }
    }

    ////Метод записи данных хранилища в файл. Формат хранени данных выбирается на основе поля this.format
    private void writeToFile(){
        switch (this.format) {
            case XML:
                this.toXML();
                break;
            case JSON:
                this.toJSON();
                break;
        }
    }

    //Метод записи данных хранилища в файл в формате JSON
    private void toJSON() {
        try (FileWriter writer = new FileWriter(this.path, false)) {
            writer.append("{\n");
            writer.append("\t\"stacksCount\":" + this.stacksCount + ",\n");
            writer.append("\t\"path\":" + this.path + ",\n");
            writer.append("\t\"flowerStacks\": [\n");
            if (this.flowerStacks != null) {
                for (int i = 0; i < this.flowerStacks.length; i++) {
                    writer.append("\t\t{\n");
                    if (this.flowerStacks[i] != null) {
                        writer.append("\t\t\t\"count\":" + this.flowerStacks[i].getCount() + ",\n");
                        writer.append("\t\t\t\"flowerType\":" + this.flowerStacks[i].getFlowerType().getName() + ",\n");
                        writer.append("\t\t\t\"color\":" + this.flowerStacks[i].getColor() + ",\n");
                        writer.append("\t\t\t\"price\":" + this.flowerStacks[i].getPrice() + ",\n");
                        writer.append("\t\t\t\"flowers\": [\n");
                        if (this.flowerStacks[i].capacity() > 0) {
                            for (int j = 0; j < this.flowerStacks[i].capacity(); j++) {
                                writer.append("\t\t\t\t{\n");
                                if (j < this.flowerStacks[i].getCount()) {
                                    writer.append("\t\t\t\t\t\"color\":" + this.flowerStacks[i].getFlower(j).getColor() + ",\n");
                                    writer.append("\t\t\t\t\t\"price\":" + this.flowerStacks[i].getFlower(j).getPrice() + ",\n");
                                    writer.append("\t\t\t\t\t\"costPrice\":" + this.flowerStacks[i].getFlower(j).getCostPrice() + "\n");
                                } else {
                                    writer.append("\t\t\t\t\tnull\n");
                                }
                                if (j == this.flowerStacks[i].capacity() - 1) {
                                    writer.append("\t\t\t\t}\n");
                                } else {
                                    writer.append("\t\t\t\t},\n");
                                }
                            }
                        } else {  //Если указатель на массив цветов не инициализирован
                            writer.append("\t\t\t\tnull\n");
                        }
                        writer.append("\t\t\t]\n");
                    } else {  //Если указатель на отдельный стэк не инициализирована
                        writer.append("\t\t\tnull\n");
                    }
                    if (i == this.flowerStacks.length - 1) {
                        writer.append("\t\t}\n");
                    } else {
                        writer.append("\t\t},\n");
                    }
                }
            } else {  //Если указатель на массив стэков не инициализирован
                writer.append("\t\tnull\n");
            }
            writer.append("\t]\n");
            writer.append("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Метод записи данных хранилища в файл в формате XML
    private void toXML() {
        try (FileWriter writer = new FileWriter(this.path, false)) {
            writer.append("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>\n");
            writer.append("<flowerStore>\n");
            writer.append("\t<stacksCount>" + this.stacksCount + "</stacksCount>\n");
            writer.append("\t<path>" + this.path + "</path>\n");
            writer.append("\t<flowerStacks>\n");
            if (this.flowerStacks != null) {
                for (int i = 0; i < this.flowerStacks.length; i++) {
                    writer.append("\t\t<flowerStack>\n");
                    if (this.flowerStacks[i] != null) {
                        writer.append("\t\t\t<count>" + this.flowerStacks[i].getCount() + "</count>\n");
                        writer.append("\t\t\t<flowerType>" + this.flowerStacks[i].getFlowerType().getName() + "</flowerType>\n");
                        writer.append("\t\t\t<color>" + this.flowerStacks[i].getColor() + "</color>\n");
                        writer.append("\t\t\t<price>" + this.flowerStacks[i].getPrice() + "</price>\n");
                        writer.append("\t\t\t<flowers>\n");
                        if (this.flowerStacks[i].capacity() > 0) {
                            for (int j = 0; j < this.flowerStacks[i].capacity(); j++) {
                                writer.append("\t\t\t\t<flower>\n");
                                if (j < this.flowerStacks[i].getCount()) {
                                    writer.append("\t\t\t\t\t<color>" + this.flowerStacks[i].getFlower(j).getColor() + "</color>\n");
                                    writer.append("\t\t\t\t\t<price>" + this.flowerStacks[i].getFlower(j).getPrice() + "</price>\n");
                                    writer.append("\t\t\t\t\t<costPrice>" + this.flowerStacks[i].getFlower(j).getCostPrice() + "</costPrice>\n");
                                } else {
                                    writer.append("\t\t\t\t\tnull\n");
                                }
                                writer.append("\t\t\t\t</flower>\n");
                            }
                        } else {  //Если указатель на массив цветов не инициализирован
                            writer.append("\t\t\t\tnull\n");
                        }
                        writer.append("\t\t\t</flowers>\n");
                    } else {  //Если указатель на отдельный стэк не инициализирована
                        writer.append("\t\t\tnull\n");
                    }
                    writer.append("\t\t</flowerStack>\n");
                }
            } else {  //Если указатель на массив стэков не инициализирован
                writer.append("\t\tnull\n");
            }
            writer.append("\t</flowerStacks>\n");
            writer.append("</flowerStore>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Метод устанавливает в каком формате будут записываться данные в файл - в XML или JSON
    //Плюс перезаписывает данные если произошла смена формата
    public void setDataFormat(DataFormat format) {
        if(this.format != format){
            this.format = format;
            this.writeToFile();
        }else{
            this.format = format;
        }
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
        this.writeToFile();
    }
}

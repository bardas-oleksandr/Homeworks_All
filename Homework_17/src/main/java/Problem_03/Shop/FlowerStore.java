package Problem_03.Shop;

import Problem_03.Annotations.DefFlower;
import Problem_03.Flowers.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.*;

public class FlowerStore implements Serializable {
    //У Map-а первого уровня ключем является тип цветка
    //У Map-а второго уровня ключем является цвет
    //Этими двумя ключами определяется доступ к ArrayList цветов соответствующего типа и цвета
    @DefFlower(flowerType = RoseFlower.class, color = "yellow", price = 1.8)
    @DefFlower(flowerType = RoseFlower.class, color = "white", price = 1.7)
    @DefFlower(flowerType = RoseFlower.class, color = "red", price = 2.0)
    @DefFlower(flowerType = Tulip.class, color = "yellow", price = 1.4)
    @DefFlower(flowerType = Tulip.class, color = "orange", price = 1.5)
    @DefFlower(flowerType = Tulip.class, color = "red", price = 1.3)
    @DefFlower(flowerType = Carnation.class, color = "red", price = 1.3)
    @DefFlower(flowerType = Gladiolus.class, color = "white", price = 1.5)
    private Map<Class, Map<String, ArrayList<Flower>>> classesMap;

    public FlowerStore() {
        this.classesMap = new LinkedHashMap<>();
    }

    String showForOwner(ChoiceMapWrapper wrapper) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        Set<Class> flowerTypes = this.classesMap.keySet();
        for (Class flowerType : flowerTypes) {
            Map<String, ArrayList<Flower>> colorMap = this.classesMap.get(flowerType);
            Set<String> colores = colorMap.keySet();
            for (String color : colores) {
                ArrayList<Flower> flowerList = colorMap.get(color);
                if (flowerList != null && flowerList.size() > 0) {
                    wrapper.get().put(counter, flowerList);
                    String name = flowerType.getName().substring(19);
                    result.append(counter++ + " - " + name);
                    if (name.length() <= 6) {
                        result.append("\t");
                    }
                    result.append("\t" + color);
                    if (color.length() <= 4) {
                        result.append("\t");
                    }
                    result.append("\t\tprice: " + flowerList.get(0).getPrice()
                            + "\t\tamount: " + flowerList.size() + "\n");
                }
            }
        }
        if (counter == 1) {
            result.append("Flower store is empty");
        }
        return new String(result);
    }

    String showForProvider(ChoiceMapWrapper wrapper) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        Set<Class> flowerTypes = this.classesMap.keySet();
        for (Class flowerType : flowerTypes) {
            Map<String, ArrayList<Flower>> colorMap = this.classesMap.get(flowerType);
            Set<String> colores = colorMap.keySet();
            for (String color : colores) {
                ArrayList<Flower> flowerList = colorMap.get(color);
                if (flowerList != null && flowerList.size() > 0) {
                    wrapper.get().put(counter, flowerList);
                    String name = flowerType.getName().substring(19);
                    result.append(counter++ + " - " + name);
                    if (name.length() <= 6) {
                        result.append("\t");
                    }
                    result.append("\t" + color);
                    if (color.length() <= 4) {
                        result.append("\t");
                    }
                    result.append("\tamount: " + flowerList.size() + "\n");
                }
            }
        }
        if (counter == 1) {
            result.append("Flower store is empty");
        }
        return new String(result);
    }

    String showForBuyer(ChoiceMapWrapper wrapper) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        Set<Class> flowerTypes = this.classesMap.keySet();
        for (Class flowerType : flowerTypes) {
            Map<String, ArrayList<Flower>> colorMap = this.classesMap.get(flowerType);
            Set<String> colores = colorMap.keySet();
            for (String color : colores) {
                ArrayList<Flower> flowerList = colorMap.get(color);
                if (flowerList != null && flowerList.size() > 0) {
                    String name = flowerType.getName().substring(19);
                    double price = flowerList.get(0).getPrice();
                    if (price > 0) {
                        wrapper.get().put(counter, flowerList);
                        result.append(counter++ + " - " + name);
                        if (name.length() <= 6) {
                            result.append("\t");
                        }
                        result.append("\t" + color);
                        if (color.length() <= 4) {
                            result.append("\t");
                        }
                        result.append("\t\tprice: " + flowerList.get(0).getPrice()
                                + "\t\tamount: " + flowerList.size() + "\n");
                    }
                }
            }
        }
        if (counter == 1) {
            result.append("Flower store is empty");
        }
        return new String(result);
    }

    //Считает сколько категорий цветов есть в магазине
    int categories() {
        int count = 0;
        Collection<Map<String, ArrayList<Flower>>> mapsCollection = this.classesMap.values();
        for (Map<String, ArrayList<Flower>> colorMap : mapsCollection) {
            count += colorMap.size();
        }
        return count;
    }

    int flowersCount(Class flowerType, String color) {
        Map<String, ArrayList<Flower>> colorMap = this.classesMap.get(flowerType);
        if (colorMap != null) {
            ArrayList<Flower> flowerList = colorMap.get(color);
            if (flowerList != null) {
                return flowerList.size();
            }
        }
        return 0;
    }

    int categoriesWithPrice() {
        int counter = 0;
        Set<Class> flowerTypes = this.classesMap.keySet();
        for (Class flowerType : flowerTypes) {
            Map<String, ArrayList<Flower>> colorMap = this.classesMap.get(flowerType);
            Set<String> colores = colorMap.keySet();
            for (String color : colores) {
                ArrayList<Flower> flowerList = colorMap.get(color);
                if (flowerList != null && flowerList.size() > 0) {
                    double price = flowerList.get(0).getPrice();
                    if (price > 0) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    Flower removeFlower(Class flowerType, String color) {
        Map<String, ArrayList<Flower>> colorMap = this.classesMap.get(flowerType);
        if (colorMap != null) {
            ArrayList<Flower> flowerList = colorMap.get(color);
            if (flowerList != null && flowerList.size() > 0) {
                Flower flower = flowerList.remove(0);
                if (flowerList.size() == 0) { //Если был удален последний цветок заданного цвета и типа
                    colorMap.remove(color);
                    if (colorMap.size() == 0) {   //Если был удален последний цветок заданного типа
                        classesMap.remove(flowerType);
                    }
                }
                return flower;
            }
        }
        return null;
    }

    void setPrice(Class flowerType, String color, double price) {
        Map<String, ArrayList<Flower>> mapColor = this.classesMap.get(flowerType);
        if (mapColor != null) {
            ArrayList<Flower> flowerList = mapColor.get(color);
            if (flowerList != null) {
                if (!flowerList.isEmpty()) {
                    for (Flower flower : flowerList) {
                        flower.setPrice(price);
                    }
                }
            }
        }
    }

    void add(Class flowerType, String color, double costPrice, int count) {
        Map<String, ArrayList<Flower>> mapColor = this.classesMap.get(flowerType);
        if (mapColor != null) {
            ArrayList<Flower> flowerList = mapColor.get(color);
            double price = 0;
            if (flowerList != null) {
                if (!flowerList.isEmpty()) {
                    price = flowerList.get(0).getPrice();
                }
                flowerList.addAll(prepareDeliveryList(flowerType, color, price, costPrice, count)); //Добавляем цветы в существующий список
            } else {
                ArrayList<Flower> deliveryList = prepareDeliveryList(flowerType, color, 0, costPrice, count);   //Создаем новый список
                mapColor.put(color, deliveryList);
            }
        } else {
            ArrayList<Flower> deliveryList = prepareDeliveryList(flowerType, color, 0, costPrice, count);   //Создаем новый список
            mapColor = new LinkedHashMap<>();
            mapColor.put(color, deliveryList);
            this.classesMap.put(flowerType, mapColor);
        }
    }

    void addAll(ArrayList<Flower> source) {
        Flower flower = source.get(0);
        if (flower != null) {
            Class flowerType = flower.getClass();
            String color = flower.getColor();
            Map<String, ArrayList<Flower>> colorMap = this.classesMap.get(flowerType);
            if (colorMap != null) {
                ArrayList<Flower> destination = colorMap.get(color);
                if (destination != null) {    //Если в хранилище есть цветы возвращаемого типа и цвета
                    destination.addAll(source);
                } else {  //Если в хранилище не было цветов заданного цвета (а тип цветка есть)
                    colorMap.put(color, source);
                }
            } else {  //Если в хранилище не было цветов заданного типа и цвета
                colorMap = new LinkedHashMap<>();
                colorMap.put(color, source);
                this.classesMap.put(flowerType, colorMap);
            }
        }
    }

    private ArrayList<Flower> prepareDeliveryList(Class flowerType, String color, double price, double costPrice, int count) {
        ArrayList<Flower> flowerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            try {
                Constructor constructor = flowerType.getDeclaredConstructor(String.class, double.class, double.class);
                flowerList.add((Flower) constructor.newInstance(color, price, costPrice));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flowerList;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
}

package Problem_03.Shop;

import Interfaces.IService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Statistics implements Serializable {
    private double expenses;    //Расходы магазина
    private double income;      //Доход магазина
    private Map<Class, Map<String, Double>> classesMap;  //Карта, содержащая информацию по доходности отдельных категорий

    public Statistics() {
        this.expenses = this.income = 0;
        this.classesMap = new LinkedHashMap<>();
    }

    void addExpences(double dx) {
        this.expenses += dx;
    }

    void addIncome(double dx) {
        this.income += dx;
    }

    public double getExpences() {
        return this.expenses;
    }

    public double getIncome() {
        return this.income;
    }

    void add(Class flowerType, String color) {
        Map<String, Double> colorMap = this.classesMap.get(flowerType);
        if (colorMap != null) {
            colorMap.putIfAbsent(color, new Double(0));
        } else {
            colorMap = new LinkedHashMap<>();
            colorMap.put(color, new Double(0));
            this.classesMap.put(flowerType, colorMap);
        }
    }

    void modify(Class flowerType, String color, double increase) {
        Map<String, Double> colorMap = this.classesMap.get(flowerType);
        if (colorMap != null) {
            colorMap.compute(color, (colorCopy, incomeCopy) -> incomeCopy + increase);
        } else {
            colorMap = new LinkedHashMap<>();
            colorMap.put(color, increase);
            this.classesMap.put(flowerType, colorMap);
        }
    }

    @Override
    public String toString() {
        return mapToString(this.classesMap);
    }

    private String mapToString(Map<Class, Map<String, Double>> map) {
        StringBuilder result = new StringBuilder();
        Set<Class> classSet = map.keySet();
        Iterator<Class> classIterator = classSet.iterator();
        while (classIterator.hasNext()) {
            Class flowerType = classIterator.next();
            Map<String, Double> colorMap = map.get(flowerType);
            Set<String> colorSet = colorMap.keySet();
            Iterator<String> colorIterator = colorSet.iterator();
            while (colorIterator.hasNext()) {
                String color = colorIterator.next();
                Double profit = colorMap.get(color);
                result.append(getLine(flowerType, color, profit));
            }
        }
        return new String(result);
    }

    String top(int count, boolean maximum) {
        Map<Class, Map<String, Double>> map = new LinkedHashMap<>();
        for (int i = 0; i < count; i++) {
            this.put(map, maximum);
        }
        return mapToString(map);
    }

    //Метод добавляет в Map минимальное (максимальное) значение ыз другого Map-a, отбрасывая ранее добавленные значения
    private void put(Map<Class, Map<String, Double>> map, boolean maximum) {
        //Мы не можем запоминать минимальное значение по индексу, поэтому запоминаем само значение
        //При этом возникает сложность задания начального минимаьного значения
        //Чтобы ее избежать - создадим флажок, который после нахождения первого значения прибыли изменит свое значение
        //Таким образом первое значение прибыли можно будет принять за начальное минимальное значение
        boolean flag = false;
        double extremeProfit = 0;
        Class extremeFlowerType = null;
        String extremeColor = null;
        Set<Class> flowerTypeSet = this.classesMap.keySet();
        for (Class flowerType : flowerTypeSet) {
            Map<String, Double> colorMap = this.classesMap.get(flowerType);
            Set<String> colorSet = colorMap.keySet();
            for (String color : colorSet) {
                Double profit = colorMap.get(color);
                if (!isPresent(map, flowerType, color)) {
                    if (!flag) {
                        flag = true;
                        extremeProfit = profit;
                    }
                    if (extremeProfit * (maximum? 1: -1) <= profit * (maximum? 1: -1)) {
                        extremeProfit = profit;
                        extremeFlowerType = flowerType;
                        extremeColor = color;
                    }
                }
            }
        }
        if(extremeColor != null && extremeFlowerType != null){
            Map<String, Double> colorMap = map.get(extremeFlowerType);
            if(colorMap != null){
                colorMap.put(extremeColor, extremeProfit);
            }else{
                colorMap = new LinkedHashMap<>();
                colorMap.put(extremeColor, extremeProfit);
                map.put(extremeFlowerType, colorMap);
            }
        }
    }

    private boolean isPresent(Map<Class, Map<String, Double>> map, Class flowerType, String color){
        Map<String, Double> colorMap = map.get(flowerType);
        if(colorMap != null){
            Double profit = colorMap.get(color);
            if(profit != null){
                return true;
            }
        }
        return false;
    }

    private String getLine(Class flowerType, String color, double profit) {
        StringBuilder result = new StringBuilder();
        result.append(flowerType.getName().substring(19) + "\t");
        if (flowerType.getName().length() < 25) {
            result.append("\t");
        }
        result.append(color);
        if (color.length() < 4) {
            result.append("\t");
        }
        result.append("\t\tprofit: " + profit + "$\n");
        return new String(result);
    }

    void showGeneral() {
        System.out.println("Total income: " + IService.round(this.income, 2));
        System.out.println("Total expences: " + IService.round(this.expenses, 2));
        System.out.println("Total profit: " + IService.round((this.income - this.expenses), 2));
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
    }
}

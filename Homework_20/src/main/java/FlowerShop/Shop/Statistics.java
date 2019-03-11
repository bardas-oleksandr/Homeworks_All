package FlowerShop.Shop;

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
    private Map<String, Map<String, Double>> classesMap;  //Карта, содержащая информацию по доходности отдельных категорий

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

    void add(String productName, String color) {
        this.classesMap.
                computeIfAbsent(productName,(x)->new LinkedHashMap<>()).
                putIfAbsent(color, 0.0);
    }

    void modify(String productName, String color, double increase) {
        Map<String, Double> colorMap = this.classesMap.computeIfAbsent(productName,(x)->new LinkedHashMap<>());
        colorMap.compute(color, (colorCopy, incomeCopy) -> incomeCopy + increase);
    }

    @Override
    public String toString() {
        return mapToString(this.classesMap);
    }

    private String mapToString(Map<String, Map<String, Double>> map) {
        StringBuilder result = new StringBuilder();
        Set<String> productNames = map.keySet();
        Iterator<String> nameIterator = productNames.iterator();
        while (nameIterator.hasNext()) {
            String productName = nameIterator.next();
            Map<String, Double> colorMap = map.get(productName);
            Set<String> colors = colorMap.keySet();
            Iterator<String> colorIterator = colors.iterator();
            while (colorIterator.hasNext()) {
                String color = colorIterator.next();
                Double profit = colorMap.get(color);
                result.append(getLine(productName, color, profit));
            }
        }
        return new String(result);
    }

    String top(int count, boolean maximum) {
        Map<String, Map<String, Double>> map = new LinkedHashMap<>();
        for (int i = 0; i < count; i++) {
            this.put(map, maximum);
        }
        return mapToString(map);
    }

    //Метод добавляет в Map минимальное (максимальное) значение ыз другого Map-a, отбрасывая ранее добавленные значения
    private void put(Map<String, Map<String, Double>> map, boolean maximum) {
        //Мы не можем запоминать минимальное значение по индексу, поэтому запоминаем само значение
        //При этом возникает сложность задания начального минимаьного значения
        //Чтобы ее избежать - создадим флажок, который после нахождения первого значения прибыли изменит свое значение
        //Таким образом первое значение прибыли можно будет принять за начальное минимальное значение
        boolean flag = false;
        double extremeProfit = 0;
        String extremeProduct = null;
        String extremeColor = null;
        Set<String> productNames = this.classesMap.keySet();
        for (String productName : productNames) {
            Map<String, Double> colorMap = this.classesMap.get(productName);
            Set<String> colors = colorMap.keySet();
            for (String color : colors) {
                Double profit = colorMap.get(color);
                if (!isPresent(map, productName, color)) {
                    if (!flag) {
                        flag = true;
                        extremeProfit = profit;
                    }
                    if (extremeProfit * (maximum? 1: -1) <= profit * (maximum? 1: -1)) {
                        extremeProfit = profit;
                        extremeProduct = productName;
                        extremeColor = color;
                    }
                }
            }
        }
        if(extremeColor != null && extremeProduct != null){
            map.computeIfAbsent(extremeProduct,(x)->new LinkedHashMap<>()).put(extremeColor, extremeProfit);
        }
    }

    private boolean isPresent(Map<String, Map<String, Double>> map, String productName, String color){
        return map.containsKey(productName) && map.get(productName).containsKey(color);
    }

    private String getLine(String productName, String color, double profit) {
        StringBuilder result = new StringBuilder();
        result.append(productName);
        result.append("\t");
        if (productName.length() < 6) {
            result.append("\t");
        }
        result.append(color);
        if (color.length() < 4) {
            result.append("\t");
        }
        result.append("\t\tprofit: ");
        result.append(profit);
        result.append("$\n");
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

package Problems_01_and_03.Shop;

import java.io.Serializable;
import java.util.*;

//Класс "Набор товаров одного вида"
public class ProductList<T extends Product> extends LinkedList<T> implements Serializable {
    private ProdListParameters parameters;  //Объект класса extends Observable

    public ProductList() {
        super();
        this.parameters = new ProdListParameters();
    }

    public ProductList(String name, String color, double price, Observer observer) {
        super();
        this.parameters = new ProdListParameters(name, color, price, observer);
    }

    public ProductList(List<T> list, String name, String color, double price, Observer observer) {
        super();
        this.addAll(list);
        this.parameters = new ProdListParameters(name, color, price, observer);
    }

    void setName(String name) {
        this.parameters.setName(name);
    }

    void setColor(String color) {
        this.parameters.setColor(color);
    }

    void setPrice(double price) {
        this.parameters.setPrice(price);
    }

    public String name() {
        return this.parameters.name();
    }

    public String color() {
        return this.parameters.color();
    }

    public double price() {
        return this.parameters.price();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.name());
        result.append("\t");
        if (this.name().length() < 6) {
            result.append("\t");
        }
        result.append(this.color());
        if (this.color().length() < 6) {
            result.append("\t");
        }
        result.append("\t\t" + ShopBundle.getString(ShopBundle.UNIT_PRICE));
        result.append(this.price());
        result.append("$\t\t" + ShopBundle.getString(ShopBundle.AMOUNT));
        result.append(this.size());
        return new String(result);
    }

    String showAdvanced() {
        StringBuilder result = new StringBuilder();
        result.append(this);
        //Следующий фрагмент кода показывает группировку товара по себестоимости
        //(все товары должны иметь одинаковую цену в магазине, но каждый товар имеет свою себестоимость)
        result.append("\n\tCost prices:\n");
        Map<Double, Integer> map = new TreeMap<>();
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            T product = iterator.next();
            Integer oldCount = map.get(product.costPrice());
            if(oldCount == null){
                oldCount = 0;
            }
            map.put(product.costPrice(), ++oldCount);
        }
        Set<Double> keySet = map.keySet();
        for(Double costPrice: keySet) {
            result.append("\t\t\t\t");
            result.append(costPrice);
            result.append("$");
            result.append(" - ");
            result.append(map.get(costPrice));
            result.append(" pieces\n");
        }
        return new String(result);
    }
}

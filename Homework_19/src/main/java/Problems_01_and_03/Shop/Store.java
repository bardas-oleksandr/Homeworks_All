package Problems_01_and_03.Shop;

import java.io.*;
import java.util.*;

//Класс хранилище всех товаров
public class Store implements Serializable {
    //У Map-а первого уровня ключем является название товара (тип цветка)
    //У Map-а второго уровня ключем является цвет
    //Этими двумя ключами определяется доступ к хранилищу товара конкретного типа
    private Map<String, Map<String, ProductList<Product>>> productMap;
    private final String pathDefault = "src/main/resources/default.txt";    //Путь к файлу с ценами по умолчанию
    private final String pathArchive = "src/main/resources/archive.txt";    //Путь к файлу архива цен
    private final String pathBackUp = "src/main/resources"; //Папка, содержимое которой будет архивироваться
    private PriceLogger priceLogger;
    private transient Timer timerBackUp;
    private transient Timer timerDiscount;

    //Конструктор вызывается только если объект не восстановлен путем десериализации
    //Поэтому нужен дополнительный способ инициализации транзиентных полей
    public Store() {
        this.productMap = new LinkedHashMap<>();
        this.priceLogger = new PriceLogger(pathArchive);
    }

    //Метод, который запускает процесс резервного копирования на основе Timer и TimerTask с настройками по умолчанию
    public void runDefaultBackUp(){
        //Запускаем архивирование файлов магазина
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,45);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date time = calendar.getTime();
        long period = 24*60*60*1000;
        setBackUpSchedule(time,period);
    }

    void setBackUpSchedule(Date time, long period){
        this.timerBackUp = new Timer(true);
        this.timerBackUp.scheduleAtFixedRate(new BackUp(pathBackUp),time,period);
    }

    void setDiscountShedule(double discount, Date dropTime, Date comeBackTime, long period) {
        this.timerDiscount = new Timer(true);
        this.timerDiscount.scheduleAtFixedRate(new PriceChanger(discount, this), dropTime, period);
        this.timerDiscount.scheduleAtFixedRate(new PriceChanger(-discount, this), comeBackTime, period);
    }

    PriceLogger priceLogger() {
        return this.priceLogger;
    }

    void saveDefaultPrices(Properties properties) {
        try (OutputStream out = new FileOutputStream(this.pathDefault)) {
            properties.storeToXML(out, "Default prices");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void changeAllPrices(double discount) {
        synchronized (this.productMap) {
            Set<String> names = this.productMap.keySet();
            for (String name : names) {
                Map<String, ProductList<Product>> colorMap = this.productMap.get(name);
                Set<String> colors = colorMap.keySet();
                for (String color : colors) {
                    ProductList<Product> list = colorMap.get(color);
                    if (discount > 0) {
                        list.setPrice(list.price() * (1 - discount / 100));
                    } else {
                        list.setPrice(list.price() / (1 + discount / 100));
                    }
                }
            }
        }
    }

    void checkPrices() {
        Set<String> names = this.productMap.keySet();
        for (String name : names) {
            Map<String, ProductList<Product>> colorMap = this.productMap.get(name);
            Set<String> colors = colorMap.keySet();
            for (String color : colors) {
                ProductList<Product> list = colorMap.get(color);
                if (list.price() == 0) {
                    list.setPrice(this.setDefaultPrice(name, color));
                }
            }
        }
    }

    private double setDefaultPrice(String name, String color) {
        try (InputStream in = new FileInputStream(this.pathDefault)) {
            Properties properties = new Properties();
            properties.loadFromXML(in);
            String key = name + color;
            return Double.parseDouble(properties.getProperty(key, "0.0"));
        } catch (IOException e) {
            return 0.0;
        }
    }

    //Считает сколько категорий товара есть в магазине
    int categories() {
        int count = 0;
        Collection<Map<String, ProductList<Product>>> mapsCollection = this.productMap.values();
        for (Map<String, ProductList<Product>> colorMap : mapsCollection) {
            count += colorMap.size();
        }
        return count;
    }

    private Optional<ProductList<Product>> getProductList(String name, String color) {
        return Optional.ofNullable(this.productMap.get(name)).
                map((colorMap) -> colorMap.get(color));
    }

    int productsAmount(String name, String color) {
        return getProductList(name, color).orElse(new ProductList<>()).size();
    }

    int categoriesWithPrice() {
        int counter = 0;
        Set<String> productNames = this.productMap.keySet();
        for (String productName : productNames) {
            Map<String, ProductList<Product>> colorMap = this.productMap.get(productName);
            Set<String> colores = colorMap.keySet();
            for (String color : colores) {
                ProductList<Product> productList = colorMap.get(color);
                if (!productList.isEmpty() && productList.price() > 0) {
                    counter++;
                }
            }
        }
        return counter;
    }

    //Method removes and then returns one product instance
    Optional<Product> removeProduct(String name, String color) {
        ProductList<Product> list = getProductList(name, color).orElse(new ProductList<>());
        return list.isEmpty() ? Optional.empty() : Optional.of(list.remove());
    }

    void setPrice(String name, String color, double price) {
        getProductList(name, color).ifPresent((list) -> list.setPrice(price));
    }

    //Создает товар согласно заданным характеристикам
    void add(String productName, String color, double costPrice, int count) {
        Map<String, ProductList<Product>> colorMap = this.productMap.computeIfAbsent
                (productName, (x1) -> new LinkedHashMap<>());
        ProductList<Product> productList = colorMap.computeIfAbsent
                (color, (x2) -> new ProductList<>(productName, color, 0, this.priceLogger));
        for (int i = 0; i < count; i++) {
            productList.add(new Product(costPrice));
        }
    }

    //Метод добавляет в хранилще всю коллекцию товара (товар может быть нескольких разных категорий)
    void addAll(ArrayList<ProductList<Product>> source) {
        if (!source.isEmpty()) {
            for (ProductList<Product> productList : source) {
                Map<String, ProductList<Product>> colorMap = this.productMap.
                        computeIfAbsent(productList.name(), (x1) -> new LinkedHashMap<>());
                ProductList<Product> destination = colorMap.
                        computeIfAbsent(productList.color(), (x2) -> new ProductList<>(productList.name(), productList.color(), 0, this.priceLogger));
                destination.addAll(productList);
            }
        }
    }

    String showForOwner(ChoiceMapWrapper wrapper) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        Set<String> productNames = this.productMap.keySet();
        for (String productName : productNames) {
            Map<String, ProductList<Product>> colorMap = this.productMap.get(productName);
            Set<String> colores = colorMap.keySet();    //colorMap != null всегда, так как получен на основе keySet()
            for (String color : colores) {
                ProductList<Product> productList = colorMap.get(color);
                wrapper.get().put(counter, productList);
                result.append(counter++);
                result.append(" - ");
                result.append(productList.showAdvanced());
            }
        }
        if (counter == 1) {
            result.append(ShopBundle.getString(ShopBundle.STORE_EMPTY));
        }
        return new String(result);
    }

    String showForProvider() {
        StringBuilder result = new StringBuilder();
        Set<String> productNames = this.productMap.keySet();
        boolean flag = true;
        for (String productName : productNames) {
            Map<String, ProductList<Product>> colorMap = this.productMap.get(productName);
            Set<String> colores = colorMap.keySet();    //colorMap != null всегда, так как получен на основе keySet()
            for (String color : colores) {
                ProductList<Product> productList = colorMap.get(color);
                result.append(productList.showAdvanced());
                flag = false;
            }
        }
        if (flag) {
            result.append(ShopBundle.getString(ShopBundle.STORE_EMPTY));
        }
        return new String(result);
    }

    String showForBuyer(ChoiceMapWrapper wrapper) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        Set<String> productNames = this.productMap.keySet();
        for (String productName : productNames) {
            Map<String, ProductList<Product>> colorMap = this.productMap.get(productName);
            Set<String> colores = colorMap.keySet();
            for (String color : colores) {
                ProductList<Product> productList = colorMap.get(color);
                if (!productList.isEmpty() && productList.price() > 0) {
                    wrapper.get().put(counter, productList);
                    result.append(counter++);
                    result.append(" - ");
                    result.append(productList);
                    result.append("\n");
                }
            }
        }
        if (counter == 1) {
            result.append(ShopBundle.getString(ShopBundle.STORE_EMPTY));
        }
        return new String(result);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
}

package Problems_01_and_03.Shop;

import java.util.TimerTask;

public class PriceChanger extends TimerTask {
    private double discount;
    private Store store;

    public PriceChanger(double discount, Store store){
        super();
        this.discount = discount;
        this.store = store;
    }

    @Override
    public void run() {
        this.store.changeAllPrices(discount);
    }
}

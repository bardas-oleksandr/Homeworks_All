package FlowerShop.Processes;

import FlowerShop.Shop.ShopBundle;
import FlowerShop.ShopInterfaces.IProcess;
import Interfaces.IService;
import FlowerShop.Shop.FlowerShop;
import FlowerShop.ShopInterfaces.IProvider;

public class ProcessProvider extends Process implements IProcess {
    private IProvider provider;

    public ProcessProvider(FlowerShop shop, String defPrices, String priceArchive) {
        super(defPrices,priceArchive);
        this.provider = shop;
    }

    @Override
    public void start() {
        String productName;
        do{
            IService.clearConsole();
            this.provider.showForProvider();
            String[] types = {"Rose flower","Tulip","Carnation","Gladioulus"};
            productName = setStringParamProcess(types, ShopBundle.getString(ShopBundle.SELECT_TYPE));
            String color;
            if(productName != null){
                String[] colors = {"Red","Yellow","Orange","White"};
                color = setStringParamProcess(colors, ShopBundle.getString(ShopBundle.SELECT_FLOWER_COLOR)).toLowerCase();
                int count = setIntParamProcess(ShopBundle.getString(ShopBundle.SET_FLOWERS_AMOUNT));
                double costPrice = setDoubleParamProcess(ShopBundle.getString(ShopBundle.SET_COST_PRICE));
                this.provider.deliverProduct(productName, color, costPrice, count, this.defaultPricesPath());
            }
        }while(productName != null);
    }
}
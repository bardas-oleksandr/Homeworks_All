package FlowerShop.ShopInterfaces;

import FlowerShop.Processes.ProcessBuyer;
import FlowerShop.Processes.ProcessOwner;
import FlowerShop.Processes.ProcessProvider;
import FlowerShop.Shop.FlowerShop;

public interface IProcessCreator {
    static IProcess create(int choice, FlowerShop shop, String defaultPrices, String priceArchive) throws IllegalStateException{
        switch(choice){
            case 1: return new ProcessBuyer(shop, defaultPrices, priceArchive);
            case 2: return new ProcessOwner(shop, defaultPrices, priceArchive);
            case 3: return new ProcessProvider(shop, defaultPrices, priceArchive);
            default: throw new IllegalArgumentException();
        }
    }
}

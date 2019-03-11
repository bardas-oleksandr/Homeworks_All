package Problems_01_and_03.ShopInterfaces;

import Problems_01_and_03.Processes.ProcessBuyer;
import Problems_01_and_03.Processes.ProcessOwner;
import Problems_01_and_03.Processes.ProcessProvider;
import Problems_01_and_03.Shop.FlowerShop;

public interface IProcessCreator {
    static IProcess create(int choice, FlowerShop shop) throws IllegalStateException{
        switch(choice){
            case 1: return new ProcessBuyer(shop);
            case 2: return new ProcessOwner(shop);
            case 3: return new ProcessProvider(shop);
            default: throw new IllegalArgumentException();
        }
    }
}

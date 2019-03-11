package Interfaces;

import Processes.ProcessBuyer;
import Processes.ProcessOwner;
import Processes.ProcessProvider;
import Shop.FlowerShop;

public interface ICreator {
    static IProcess create(int choice, FlowerShop shop) throws IllegalStateException{
        switch(choice){
            case 1: return new ProcessBuyer(shop);
            case 2: return new ProcessOwner(shop);
            case 3: return new ProcessProvider(shop);
            default: throw new IllegalArgumentException();
        }
    }
}

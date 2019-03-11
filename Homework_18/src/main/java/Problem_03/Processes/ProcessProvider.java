package Problem_03.Processes;

import Problem_03.ShopInterfaces.IProcess;
import Interfaces.IService;
import Problem_03.Shop.FlowerShop;
import Problem_03.ShopInterfaces.IProvider;

public class ProcessProvider extends Process implements IProcess {
    private IProvider provider;

    public ProcessProvider(FlowerShop shop){
        this.provider = shop;
    }

    @Override
    public void start() {
        String productName;
        do{
            IService.clearConsole();
            this.provider.showForProvider();
            String[] types = {"Rose flower","Tulip","Carnation","Gladioulus"};
            productName = setStringParamProcess(types, "Select flower type to add");
            String color;
            if(productName != null){
                String[] colors = {"Red","Yellow","Orange","White"};
                color = setStringParamProcess(colors,"Select color of the flower").toLowerCase();
                int count = setIntParamProcess("Set flowers amount:");
                double costPrice = setDoubleParamProcess("Set cost price:");
                this.provider.deliverProduct(productName, color, costPrice, count);
            }
        }while(productName != null);
    }
}

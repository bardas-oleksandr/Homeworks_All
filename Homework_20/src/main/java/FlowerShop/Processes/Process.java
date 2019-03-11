package FlowerShop.Processes;

import Interfaces.IService;
import FlowerShop.Shop.ShopBundle;

abstract class Process {
    private String defaultPricesPath;
    private String priceArchivePath;

    public Process(String defPrices, String priceArchive){
        this.defaultPricesPath = defPrices;
        this.priceArchivePath = priceArchive;
    }

    String defaultPricesPath(){
        return this.defaultPricesPath;
    }

    String priceArchivePath(){
        return this.priceArchivePath;
    }

    String setStringParamProcess(String[] types, String header){
        final int EXIT = 0;
        int typeNum;
        System.out.println(header);
        for(int i = 0; i< types.length; i++){
            System.out.println((i+1) + " - " + types[i]);
        }
        System.out.println(EXIT + ShopBundle.getString(ShopBundle.EXIT_MODE));
        System.out.print(ShopBundle.getString(ShopBundle.CHOICE));
        do {
            typeNum = IService.getInteger();
            if (typeNum < 0 || typeNum > types.length) {
                System.out.println("Incorrect input. Integer from the range 0.." + types.length + " is expected.");
                System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
            }
        } while (typeNum < 0 || typeNum > types.length);
        if(typeNum != 0){
            return types[typeNum - 1];
        }else{
            return null;
        }
    }

    int setIntParamProcess(String header){
        int count;
        System.out.print(header);
        do {
            count = IService.getInteger();
            if (count <= 0) {
                System.out.println("Incorrect input. Positive integer value is expected.");
                System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
            }
        } while (count <= 0);
        return count;
    }

    double setDoubleParamProcess(String header){
        double value;
        System.out.print(header);
        do {
            value = IService.getDouble();
            if (value <= 0) {
                System.out.println("Incorrect input. Positive value is expected.");
                System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
            }
        } while (value <= 0);
        return value;
    }
}

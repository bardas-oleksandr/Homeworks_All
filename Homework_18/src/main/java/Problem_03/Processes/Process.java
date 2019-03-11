package Problem_03.Processes;

import Interfaces.IService;

import java.util.Date;

abstract class Process {

    String setStringParamProcess(String[] types, String header){
        int typeNum;
        System.out.println(header);
        for(int i = 0; i< types.length; i++){
            System.out.println((i+1) + " - " + types[i]);
        }
        System.out.println("0 - Exit");
        System.out.print("Your choice:");
        do {
            typeNum = IService.getInteger();
            if (typeNum < 0 || typeNum > types.length) {
                System.out.println("Incorrect input. Integer from the range 0.." + types.length + " is expected.");
                System.out.print("Try again: ");
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
                System.out.print("Try again: ");
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
                System.out.print("Try again: ");
            }
        } while (value <= 0);
        return value;
    }
}

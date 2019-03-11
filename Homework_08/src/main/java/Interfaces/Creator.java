package Interfaces;

import Dispatcher.Locker;

public interface Creator {
    static Thread create(int number, Locker locker){
        switch(number){
            case 1:
                return new Problem_01.Processor(locker);
            case 2:
                return new Problem_02.Processor(locker);
            default:
                return null;
        }
    }
}

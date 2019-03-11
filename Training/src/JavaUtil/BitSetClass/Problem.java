package JavaUtil.BitSetClass;

import Interfaces.IProblem;

import java.util.BitSet;

public class Problem implements IProblem {
    @Override
    public void solve() {
        int x = 255;
        int mask = 2;

        if((x&mask) == mask){
            System.out.println("Operator & allows to check if the bit is switched on");
        }

        x = 4;
        System.out.println("x = " + x);
        if((x&mask) != mask){
            System.out.println("Third bit is switched off");
        }
        x = x|mask;
        System.out.println("x = " + x);
        if((x&mask) == mask){
            System.out.println("Third bit is switched on");
        }
        System.out.println("Operator | allows to turn on the bit");

        x = 255;    //11111111
        mask = (int)Math.pow(2,7); //10000000
        System.out.println("x = " + x);
        System.out.println("mask = " + mask);
        x = x^mask; //01111111 - 127
        System.out.println("x = " + x);
        System.out.println("Operator ^ allows to change bit-state");

        BitSet bitSet = new BitSet(8);

        System.out.println(bitSet);
        for(int i = 0 ; i < 8; i++){
            bitSet.flip(i);
            System.out.println(bitSet);
        }
        for(int i = 0 ; i < 8; i++){
            bitSet.flip(i);
            System.out.println(bitSet);
        }

        System.out.println("andNot");
        bitSet.flip(2);
        bitSet.flip(4);
        System.out.println("bitset: " + bitSet);
        BitSet bitSet1 = new BitSet(8);
        bitSet1.flip(2);
        bitSet1.flip(3);
        System.out.println("bitset1: " + bitSet1);
        bitSet.andNot(bitSet1); //в объекте bitSet выключаются все биты, которые были включены в обоих объектах bitSet и bitSet1
        System.out.println("bitSet.andNot(bitSet1): " + bitSet);

        System.out.println("xor");
        bitSet.flip(2);
        System.out.println("bitset: " + bitSet);
        System.out.println("bitset1: " + bitSet1);
        bitSet.xor(bitSet1);    //В объекте bitSet меняют свое значение все биты, включенные в объекте bitSet1
        System.out.println("bitSet.xor(bitSet1): " + bitSet);
    }
}

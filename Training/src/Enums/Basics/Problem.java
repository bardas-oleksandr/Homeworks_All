package Enums.Basics;

import Interfaces.IProblem;

import java.lang.Enum;

public class Problem implements IProblem {
    public void solve() {
        Fish fish;
        fish = Fish.Pike;

        System.out.println(fish.say());

        switch (fish) {
            //case Fish.Pike:   //������ ��� ����� �������
            case Pike:
                System.out.println("I caught a pike");
                break;
            case Perch:
                System.out.println("I caught a perch");
                break;
            case Zander:
                System.out.println("I caught zander");
                break;
        }

        Fish allFish[] = Fish.values();
        for (Fish smallFish : allFish) {
            System.out.println(smallFish);
        }

        for (Fish smallFish : Fish.values()) {
            System.out.println(smallFish);
        }

        Fish bigFish = Fish.valueOf("Pike");
        System.out.println(bigFish.say());

        //Fish unknownFish = Fish.valueOf("Sturgeon");  //��� �������� � ������

        for (Fish smallFish : Fish.values()) {
            System.out.println(smallFish);
        }

        System.out.println(fish.ordinal());

        System.out.println(fish.compareTo(Fish.Zander));

        //System.out.println(fish.compareTo(Frog.Green)); //� ������� compareTo ������ ���������� ������� ������ ������������

        System.out.println("fish.Pike.equals(Frog.Red) = " + fish.Pike.equals(Frog.Red));   //� ������� equals ����� ���������� ������� ������ ������������

        //��������� enum
        //1. � ����� ������ "������������" - ������������� ������ equals � ��������� "==" �����������
        Fish first = Fish.Pike;
        Fish second = Fish.Catfish;
        if (first == second) {

        }
        if (first.equals(second)) {

        }
        //2. �� ������������� "==" ����� ������������
        //2.1. ����� ��������
        //2.2. �������� ������� �������
        //2.3. ��������� �������� NullPointerException
        Fish third = null;
        if (third == second) {

        }
        try {
            third.equals(second);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        //2.4. ������������� "==" ������������ ����� ������� �����������.
        Frog fourth = Frog.Green;
        //��� �� ���������� Enum ������ �������.
        try {
            fourth.equals(first);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //�������� "==" ��� �� �������� �������� ������ ����
//        if(fourth == first){
//
//        }

        //�������������� STRING � ENUM
        Enum.valueOf(Fish.class, "Pike");
    }
}

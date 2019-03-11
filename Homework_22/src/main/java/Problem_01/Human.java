package Problem_01;

import java.util.Formatter;

public class Human {
    private double heigth;
    private double weight;
    private int yearsOld;

    Human(double heigth, double weight, int yearsOld){
        this.heigth = heigth;
        this.weight = weight;
        this.yearsOld = yearsOld;
    }

    @Override
    public String toString(){
        Formatter formatter = new Formatter();
        formatter.format("weight: %6.2f kg\t\theigth: %4.2f m\t\tyears: %d", this.weight, this.heigth, this.yearsOld);
        return formatter.toString();
    }

    double heigth(){
        return this.heigth;
    }

    double weight(){
        return this.weight;
    }

    int yearsOld(){
        return this.yearsOld;
    }
}

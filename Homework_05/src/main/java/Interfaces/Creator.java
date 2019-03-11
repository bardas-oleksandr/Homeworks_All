package Interfaces;

public interface Creator {
    static IProblem create(int number){
        switch(number){
            case 1:
                return new Problem_01.Problem_01();
            case 2:
                return new Problem_02.Problem_02();
            case 3:
                return new Problem_03.Problem_03();
            default:
                return null;
        }
    }
}

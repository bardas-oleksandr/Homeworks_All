package Interfaces;

public interface ICreator  {
    static IProblem create(int choice) throws IllegalArgumentException{
        switch(choice){
            case 1: return new Problem_01.Problem();
            case 2: return new Problem_02.Problem();
            default: throw new IllegalArgumentException();
        }
    }
}

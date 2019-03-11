package Interfaces;

public interface ICreator  {
    static IProblem create(int choice) throws IllegalArgumentException{
        switch(choice){
            case 1: return new Problem_01.Problem();
            default: throw new IllegalArgumentException();
        }
    }
}

package Interfaces;

public interface ICreator {
    static IProblem create(int choice) throws IllegalStateException{
        switch(choice){
            case 1: return new Problem_01.Problem();
            case 2: return new Problem_02.Problem();
            case 3: return new Problem_03.Problem();
            default: throw new IllegalArgumentException();
        }
    }
}

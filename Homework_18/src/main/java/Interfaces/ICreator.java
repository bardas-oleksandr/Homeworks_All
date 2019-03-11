package Interfaces;

public interface ICreator {
    static IProblem create(int choice){
        switch(choice){
            case 1: return new Problem_03.Problem();
            default: return null;
        }
    }
}

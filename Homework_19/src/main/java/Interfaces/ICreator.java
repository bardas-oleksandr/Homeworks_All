package Interfaces;

public interface ICreator {
    static IProblem create(int choice){
        switch(choice){
            case 1: return new Problems_01_and_03.Problem();
            case 2: return new Problem_02.Problem();
            case 3: return new Problem_04.Problem();
            default: return null;
        }
    }
}

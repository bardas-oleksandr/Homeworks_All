package Interfaces;

public interface ICreator {
    static IProblem create(int choice){
        switch(choice){
            case 1: return new Problem_01.Problem();
            case 2: return new Problem_01_FileVisitor.Problem();
            case 3: return new FlowerShop.Problem();
            default: return null;
        }
    }
}

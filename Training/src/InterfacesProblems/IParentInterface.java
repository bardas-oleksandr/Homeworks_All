package InterfacesProblems;

public interface IParentInterface {
    default void method(){
        System.out.println("Default overridenMethod from parent interface");
    }
}

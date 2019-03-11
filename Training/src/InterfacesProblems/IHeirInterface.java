package InterfacesProblems;

public interface IHeirInterface extends IParentInterface {
    @Override
    public default void method(){
        System.out.println("Default overridenMethod from heir interface");
    }
}

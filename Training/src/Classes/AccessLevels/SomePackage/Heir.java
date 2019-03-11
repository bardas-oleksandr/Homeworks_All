package Classes.AccessLevels.SomePackage;

public class Heir extends Parent {

    public void doNothing(){
        this.x = 1; //public-переменная доступна наследнику
        this.y = 1; //protected-переменная доступна наследнику
        this.q = 1; //package-level-переменная доступна наследнику
        //this.z = 1; //private-переменная НЕдоступна наследнику
    }
}

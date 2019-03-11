package Classes.AccessLevels;

import Classes.AccessLevels.SomePackage.Parent;

public class HeirFromAnotherPackage extends Parent {

    public void doNothing(){
        this.x = 1; //public-переменная доступна наследнику из другого package
        this.y = 1; //protected-переменная доступна ЛЮБОМУ наследнику, даже из другого package
        //this.q = 1; //package-level-переменная НЕдоступна наследнику из другого package
        //this.z = 1; //private-переменная НЕдоступна наследнику из другого package
    }
}

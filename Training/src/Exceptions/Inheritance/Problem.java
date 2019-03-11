package Exceptions.Inheritance;

import Exceptions.Inheritance.ClassPack.HeirClass;
import Exceptions.Inheritance.ClassPack.ParentClass;
import Interfaces.IProblem;

public class Problem implements IProblem {
    public void solve(){
        HeirClass heir = new HeirClass();
        heir.InterfaceThrowsClassDoesnt();

        ParentClass parent = new ParentClass();
        parent.InterfaceThrowsClassDoesnt();

        IExample example = parent;
        try{
            example.InterfaceThrowsClassDoesnt();
        }
        catch(Exception e){}

        try{
            example = heir;
            example.InterfaceThrowsClassDoesnt();
        }
        catch(Exception e){}

        try{
            parent = heir;
            parent.InterfaceThrowsClassDoesnt();
        }
        catch(Exception e){}
    }
}

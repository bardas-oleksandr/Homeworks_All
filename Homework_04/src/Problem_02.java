public class Problem_02 implements IWhatToDo {
    public void solve(){
        System.out.println("WORKING WITH SINGLETON");
        //Singleton first = new Singleton();    //Error. 'Singleton()' has private access
        System.out.println("Starting value, stored in singleton: " + Singleton.getReference().getEntity().getValue());
        Singleton.getReference().getEntity().setValue(10.1);  //We can change class data
        System.out.println("Value after changing: " + Singleton.getReference().getEntity().getValue());
        Singleton another = Singleton.getReference();   //It's OK, we have not create a new object, it is just a reference to an existing one
        another.getEntity().setValue(11.1);   //We also can change data with the help of new reference
        System.out.println("Value, changed with a new reference on the same object: " + Singleton.getReference().getEntity().getValue()); //But we have the only one object still
    }
}

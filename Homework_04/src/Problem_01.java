public class Problem_01 implements IWhatToDo {
    public void solve(){
        System.out.println("WORKING WITH TRIPLETON");
        {   //Local scope
            int size = Tripleton.MAXIMUM + 1;
            Tripleton array[] = new Tripleton[size];
            for(int i = 0; i < size; i++){
                try {
                    array[i] = Tripleton.create();
                    System.out.println("Object was successfully created!");
                    array[i].getEntity().setValue(Math.round(100*Math.random()));
                }
                catch(CountOverflow e) {
                    System.out.println(e.getMessage());
                    System.out.println("Object can't be created!");
                }
            }
            for(int i = 0; i < size; i++){
                if(array[i]!=null){
                    System.out.println(array[i].getEntity().getValue());
                }else{
                    System.out.println("array[" + i + "]==null");
                }
            }
        }
    }
}

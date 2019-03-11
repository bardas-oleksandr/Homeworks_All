public class Tripleton {
    private static int counter;
    public static final int MAXIMUM = 3;
    private Entity entity;

    private Tripleton()
    {
        entity = new Entity();
    }

    public static Tripleton create() throws CountOverflow{
        if(Tripleton.counter < MAXIMUM){
            Tripleton.counter++;
            return new Tripleton();
        }else {
            String str = "Total objects count can't exceed " + MAXIMUM;
            throw new CountOverflow(str);
        }
    }

    public Entity getEntity(){
        return entity;
    }

    protected void finalize(){  //You should not rely on this method
        counter--;
    }
}

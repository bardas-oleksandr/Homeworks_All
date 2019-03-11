public class Singleton {
    private static Singleton ref;

    static{ //Will be executed once when class-loader is working
        ref = new Singleton();
    }

    private Entity entity;

    private Singleton(){
        entity = new Entity();
    }

    public static Singleton getReference(){
        return ref;
    }

    public Entity getEntity(){
        return entity;
    }
}

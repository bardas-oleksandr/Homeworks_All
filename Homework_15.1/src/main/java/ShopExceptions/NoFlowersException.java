package ShopExceptions;

public class NoFlowersException extends FlowerStoreException {
    public NoFlowersException(Class flowerType, String color){
        super(flowerType, color);
    }

    @Override
    public String getMessage(){
        return new String("We need more of " + super.getColor() + " " + super.getFlowerType().getName() + "s");
    }
}

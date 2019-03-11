package ShopExceptions;

public class StackOverflowException extends FlowerStoreException {
    public StackOverflowException(Class flowerType, String color){
        super(flowerType, color);
    }

    @Override
    public String getMessage(){
        return new String("Stackoverflow on " + super.getColor() + " " + super.getFlowerType() + "s stack");
    }
}

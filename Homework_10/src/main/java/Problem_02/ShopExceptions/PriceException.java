package Problem_02.ShopExceptions;

//Should be thrown in the case of attempt to establish negative price of the flower
public class PriceException extends NoFlowersException {
    private double price;

    public PriceException(Class flowerType, String color, double price){
        super(flowerType, color);
        this.price = price;
    }

    @Override
    public String getMessage(){
        return new String("Attempt of operating with non-positive price was made.");
    }

    public double getPrice(){
        return this.price;
    }
}

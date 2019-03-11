package Problem_02.ShopExceptions;

public class CountException extends NoFlowersException {
    private int count;

    public CountException(Class flowerType, String color, int count){
        super(flowerType, color);
        this.count = count;
    }

    @Override
    public String getMessage(){
        return new String("An attempt of operating with negative flowers count was made.");
    }

    public int getCount(){
        return this.count;
    }
}

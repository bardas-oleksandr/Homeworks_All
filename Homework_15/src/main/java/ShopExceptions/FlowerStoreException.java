package ShopExceptions;

public class FlowerStoreException extends Exception {
    private Class flowerType;
    private String color;

    public FlowerStoreException(Class flowerType, String color){
        this.flowerType = flowerType;
        this.color = color;
    }

    public Class getFlowerType(){
        return this.flowerType;
    }

    public String getColor(){
        return this.color;
    }
}

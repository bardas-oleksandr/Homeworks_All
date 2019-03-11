package ShopExceptions;

public class NoFlowersException extends Exception {
    private Class flowerType;
    private String color;

    public NoFlowersException(Class flowerType, String color){
        this.flowerType = flowerType;
        this.color = color;
    }

    public Class getFlowerType(){
        return this.flowerType;
    }

    public String getColor(){
        return this.color;
    }

    @Override
    public String getMessage(){
        return new String("We need more of " + color + " " + flowerType.getName() + "s");
    }
}

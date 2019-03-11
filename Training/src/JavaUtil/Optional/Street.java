package JavaUtil.Optional;

import java.util.Optional;

public class Street {
    private String name;

    public Street(){
        this.name = null;
    }

    public Street(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    static public Optional<String> getOptionalStreetName(Street street){
        return street.name == null? Optional.empty(): Optional.of(street.name);
    }

    static public String getStaticStreetName(Street street){
        return street.name;
    }

    @Override
    public String toString(){
        return name;
    }
}

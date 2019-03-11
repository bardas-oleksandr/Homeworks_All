package JavaUtil.Optional;

import java.util.Optional;

public class Adress {
    private Street street;

    public Adress(){
        this.street = null;
    }

    public Adress(Street street){
        this.street = street;
    }

    public Street getStreet(){
        return this.street;
    }

    static public Optional<Street> getOptionalStreet(Adress adress){
        return adress.street == null? Optional.empty(): Optional.of(adress.street);
    }

    static public Street getStaticStreet(Adress adress){
        return adress.street;
    }

    @Override
    public String toString(){
        return "street " + street;
    }
}

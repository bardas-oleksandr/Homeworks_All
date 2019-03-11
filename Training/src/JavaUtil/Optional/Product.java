package JavaUtil.Optional;


import java.util.Optional;

public class Product {
    private Double val;

    public Product(){
        this.val = null;
    }

    public Product(double val){
        this.val = val;
    }

    public void set(double val){
        this.val = val;
    }

    public Optional<Double> getValue(){
        if(this.val == null){
            return Optional.empty();
        }else{
            return Optional.of(this.val);
        }
    }
}

public class RodAnother {
    private double length;
    private int weight;
    private double price;
    private int lowerTest;
    private int higherTest;
    private String type;
    private String material;

    public void show(){
        System.out.println("Fishing rod");
        System.out.println("Price: " + price + "$");
        System.out.println("Length: " + length);
        System.out.println("Weight: " + weight);
        System.out.println("Test: " + lowerTest + "-" + higherTest);
        System.out.println("Type: " + type);
        System.out.println("Material: " + material);
    }

    public static class AnotherRodBuilder{
        private double length;
        private int weight;
        private double price;
        private int lowerTest;
        private int higherTest;
        private String type;
        private String material;

        public RodAnother build(){
            return new RodAnother(this);
        }

        public AnotherRodBuilder hasLength(double length){
            this.length = length;
            return this;
        }
        public AnotherRodBuilder hasWeight(int weight){
            this.weight = weight;
            return this;
        }
        public AnotherRodBuilder costs(double price){
            this.price = price;
            return this;
        }
        public AnotherRodBuilder hasLowerTest(int lowerTest){
            this.lowerTest = lowerTest;
            return this;
        }
        public AnotherRodBuilder hasHigherTest(int higherTest){
            this.higherTest = higherTest;
            return this;
        }
        public AnotherRodBuilder hasType(String type){
            this.type = type;
            return this;
        }
        public AnotherRodBuilder madeOf(String material){
            this.material = material;
            return this;
        }
    }

    private RodAnother(AnotherRodBuilder builder){
        this.length = builder.length;
        this.weight = builder.weight;
        this.price = builder.price;
        this.lowerTest = builder.lowerTest;
        this.higherTest = builder.higherTest;
        this.type = builder.type;
        this.material = builder.material;
    }
}

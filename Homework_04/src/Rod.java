public class Rod {
    private double length;
    private int weight;
    private double price;
    private int lowerTest;
    private int higherTest;
    private String type;
    private String material;
    private static int counter;
    private static int MAXIMUM=2;

    public static RodBuilder create()throws CountOverflow{
        if(counter<MAXIMUM) {
            counter++;
            return new Rod().new RodBuilder();  //Из статического метода нельзя вернуть просто new RodBuilder() самого по себе
            //Это связано с тем что метод статический и вызывается не через конкретный объект, а через имя класса.
            // В то же время объекты класса RodBuilder должны существовать только в контексте объемлющего класса Rod
            //Так как метод статический, то он вызывается не объектом. Следовательно нельзя возвращать экземпляр внутреннего класса
            //если нет экземпляра объемлющего класса
        }else{
            String str = "Total objects count can't exceed " + MAXIMUM;
            throw new CountOverflow(str);
        }
    }

    public void show(){
        System.out.println("Fishing rod");
        System.out.println("Price: " + price + "$");
        System.out.println("Length: " + length);
        System.out.println("Weight: " + weight);
        System.out.println("Test: " + lowerTest + "-" + higherTest);
        System.out.println("Type: " + type);
        System.out.println("Material: " + material);
    }

    public class RodBuilder{
        public Rod getRod(){
            return Rod.this;   //returning the instance of enclosing method
        }
        public RodBuilder hasLength(double length){
            Rod.this.length = length;   //Rod.this.length - full name, we can also use the short one - length, but not this.length
            return this;    //returning the instance of nested class
        }
        public RodBuilder hasWeight(int weight){
            Rod.this.weight = weight;
            return this;
        }
        public RodBuilder costs(double price){
            Rod.this.price = price;
            return this;
        }
        public RodBuilder hasLowerTest(int lowerTest){
            Rod.this.lowerTest = lowerTest;
            return this;
        }
        public RodBuilder hasHigherTest(int higherTest){
            Rod.this.higherTest = higherTest;
            return this;
        }
        public RodBuilder hasType(String type){
            Rod.this.type = type;
            return this;
        }
        public RodBuilder madeOf(String material){
            Rod.this.material = material;
            return this;
        }
    }
}

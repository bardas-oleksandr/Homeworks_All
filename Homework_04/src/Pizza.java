public class Pizza {
    public int X;
    public Builder_2 Q1;
    public Builder_2 Q2;

    public Pizza(){
        Q1= new Builder_2();
        Q2= new Builder_2();
    }

    public class Builder_1{
        public int Y;
    }

    public static class Builder_2{
        public int Y;
    }

    public Builder_1 create_1(){
        return new Builder_1();
    }

    public static Builder_2 create_2(){
        return new Builder_2();
    }
}

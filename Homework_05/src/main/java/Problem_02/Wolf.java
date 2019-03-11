package Problem_02;

class Wolf extends LivingBeing {
    Wolf(int power, int IQ){
        super(power,IQ);
    }

    void makeSound(){
        if(this.isAlive()){
            System.out.println("U-u-u-u");
        }else{
            System.out.println("Dead wolf can't u-u-u-u");
        }
    }

    @Override
    public String toString(){
        return new String("Wolf\tAlive: " + isAlive() +"\t\tPower: " + getPower() + "\t\tIQ: " + getIQ());
    }
}

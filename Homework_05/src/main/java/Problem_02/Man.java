package Problem_02;

class Man extends LivingBeing {
    private boolean gun;  //Признак наличия ружья

    Man(int power, int IQ, boolean gun){
        super(power, IQ);
        this.gun = gun;
    }

    void setGun(boolean gun){
        if(isAlive()){  //Покупать или продавать ружье может только живой человек
            this.gun = gun;
        }
    }

    boolean hasGun(){
        return this.isAlive() && this.gun;
    }

    @Override
    void attack(LivingBeing victim){
        if(isAlive() && gun){ //Человек может кого-то убить только если он еще сам живой и у него есть ружье
            victim.die();
        }
    }

    void makeSound(){
        if(isAlive()){
            System.out.println("I am alive");
        }else{
            System.out.println("Dead man can't speak");
        }
    }

    @Override
    public String toString(){
        return new String("Man\t\tAlive: " + isAlive() +"\t\tPower: " + getPower() + "\t\tIQ: " + getIQ() + "\t\tHas gun: " + gun);
    }
}

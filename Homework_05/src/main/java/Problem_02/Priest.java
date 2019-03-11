package Problem_02;

class Priest extends Werewolf {
    Priest(int power, int IQ, boolean gun){
        super(power, IQ, gun);
    }
    void pray(){
        if(this.getEntity().isAlive()){
            if(this.getEntity() instanceof Werewolf.InnerMan){   //Если сейчас священник человек а не волк
                System.out.println("Priest is praying");    //Священник молится
            }else{
                System.out.println("Priest can't pray. He is turned into wolf now");   //Священник не может молится, он сейчас превращен в волка
            }
        }else{
            System.out.println("Dead priest can't pray");   //Мертвый священник не может молится
        }
    }

    @Override
    public String toString(){
        return new String(super.toString() + "\tPriest");
    }
}

package Problem_02;
//класс Оборотень
class Werewolf {
    private LivingBeing entity;  //Указатель на сущность оборотня в какой-то момент времени (волк или человек)

    Werewolf(int power, int IQ, boolean gun){
        this.entity = new InnerMan(power, IQ, gun);
    }

    void turnIntoWolf(){   //Превращение из человека в волка
        if(entity.isAlive() && entity instanceof InnerMan){   //Если оборотень жив и находится в состоянии человека
            this.entity = new InnerWolf(this.entity.getPower()*2,this.entity.getIQ()/2);  //Волк становится в два раза сильнее и в два раза глупее, чем в то время когда он был человеком
        }
    }

    void turnIntoMan(){ //Превращение из волка в человека
        if(entity.isAlive() && entity instanceof InnerWolf){   //Если оборотень жив и находится в состоянии волка
            this.entity = new InnerMan(this.entity.getPower()/2,this.entity.getIQ()*2,false);  //Человек становится в два раза слабее и в два раза умнее, чем в он был в то время, когда был волком
        }
    }

    LivingBeing getEntity(){
        return this.entity;
    }

    class InnerWolf extends Wolf{
        InnerWolf(int power, int IQ){
            super(power,IQ);
        }

        @Override
        void die() {    //Метод умереть. Оборотня нельзя убить пока он в состоянии волка
            System.out.println("You can't kill werewolf while it's entity is \"Wolf\"");
        }
    }

    class InnerMan extends Man{ //Класс-оболочка. Нужен только для целей наследования. Родительский класс Man не прописан
                                //прямо здесь для того, чтобы можно было создать простого человека
        InnerMan(int power, int IQ, boolean gun){
            super(power, IQ, gun);
        }
    }

    @Override
    public String toString(){
        return new String("Werewolf\tCurrent state: " + entity.getClass().getName() + entity.toString());
    }
}

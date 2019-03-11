package Problem_02;
//Класс живое существо
abstract class LivingBeing {
    private int power;    //Уровень силы
    private int IQ;       //Уровень интеллекта
    private boolean alive;    //Живой или мертвый

    LivingBeing(int power, int IQ){
        this.power = power;
        this.IQ = IQ;
        this.alive = true;
    }

    void setPower(int power) {
        if(alive){
            this.power = power;
        }
    }
    void setIQ(int IQ) {
        if(alive) {
            this.IQ = IQ;
        }
    }
    void die() {    //Метод умереть. Имеет односторонее действие, воскресить кого-то не получится.
        this.alive = false;
        this.power=0;
        this.IQ=0;
    }
    int getPower(){
        return this.power;
    }
    int getIQ(){
        return this.IQ;
    }
    boolean isAlive(){
        return this.alive;
    }
    void attack(LivingBeing victim){  //Одно живое существо убивает другое
        if(alive){
            victim.die();
        }
    }
    abstract void makeSound();  //Метод "издавать звук". Для человека это какие-то слова. Для волка - это вой.
}

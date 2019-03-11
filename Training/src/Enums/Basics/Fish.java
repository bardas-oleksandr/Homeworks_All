package Enums.Basics;

//enum может имплементировать интерфейс
//но не может наследовать какой-то класс
//(потому что люба€ конкретна€ реализаци€ enum уже наследована от Enum
public enum Fish implements MyInterface {
    Pike("I'm pike"), Perch("I'm perch"), Tench("I'm tench"), Zander("I'm zander"), Catfish("I'm catfish");

    private String say; //ѕоле может носить такое же им€, как и метод

    //1.¬ enum можно создать только один конструктор. ”ровень доступа конструктора - private.
    //ѕричем, мы не можем это изменить.
    //2.Ётот конструктор можно вызывать только по схеме Fish.Pike()
    //3. private is redundant
    private Fish(String message){
        this.say = message;
    }

    public String say(){
        return this.say;
    }
}

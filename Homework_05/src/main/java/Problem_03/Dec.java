package Problem_03;

abstract class Dec implements IDec {
    private IDec mail;

    Dec(IDec mail){
        this.mail = mail; //Тут ноый объект не создает, просто направялем ссылку на существующий
    }

    void setMail(IDec mail){
        this.mail = mail;
    }

    IDec getMail(){
        return this.mail;
    }

    abstract public StringBuilder writeMail();
}

package Problem_03;

class DecBirthday extends Dec {
    DecBirthday(IDec mail){
        super(mail);
    }

    public StringBuilder writeMail(){
        StringBuilder result = super.getMail().writeMail();
        if(super.getMail() instanceof Mail){
            int position = ((Mail)super.getMail()).getHead().length() + ((Mail)getMail()).getBody().length();
            while(position > 0 && result.charAt(position - 1) == '\n'){ //Если пользователь завершил body символами перенсоа строки, мы не будем отрывать "элемент декора" от тела
                position--;
            }
            result.insert(position,new StringBuilder(" Happy birthday, again!"));
            position = ((Mail)super.getMail()).getHead().length();
            return result.insert(position,new StringBuilder("Happy Birthday! "));
        }
        else{
            return new StringBuilder("You can't use more then one decorator");
        }
    }
}

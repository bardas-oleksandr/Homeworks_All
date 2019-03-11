package Classes.InnerClasses;

public class Outer {
    private int xOuter;
    private Inner inner;
    private StaticInner staticInner;

    public Outer(int x, int y){
        this.xOuter = x;
        this.inner = new Inner(y);
        this.staticInner = new StaticInner();
    }

    class Inner{
        private int xInner;

        public Inner(int x){
            this.xInner = x;
            Outer.this.xOuter = x+1;  //Нестатический внутренний класс имеет непосредственный доступ ко всем полям и методам объемлющего класса
                                    //Поэтому объект нестатического класса не может существовать вне контекста объемлющего
        }

        public int getxInner() {
            return xInner;
        }

        //Можно создать такой хитрый метод. Через него мы сможем например в main через объект внутреннего класса
        //вытянуть объект наружного класса (даже если существует только ссылка на объект внутреннего класса)
        //А потом можем через полученное значение вытянуть все значения объекта наружного класса.
        public Outer getOuter(){
            return Outer.this;
        }
    }

    static class StaticInner{
        private int xStaticInner;

        public StaticInner(){
            this.xStaticInner = 0;
            //Outer.this.xOuter = 1;  //Статический класс доступа к НЕСТАТИЧЕСКИМ полям и методам объемлющего класса не имеет
        }

        public int getxStaticInner() {
            return xStaticInner;
        }
    }

    public Inner getInner() {
        return inner;
    }

    public int getxOuter() {
        return xOuter;
    }

    public StaticInner getStaticInner() {
        return staticInner;
    }
}

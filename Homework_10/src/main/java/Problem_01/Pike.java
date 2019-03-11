package Problem_01;

@MyAnnotation(explanation = "В учебных целях класс содержит поля и методы со всеми возможными модификаторами доступа")
@MyAnnotation(explanation = "Пример повторяющейся аннотации")
public class Pike extends Fish {
    public boolean hungry;
    private boolean angry;
    protected boolean canJump;
    String color;

    public Pike(){
        super();
        this.hungry=true;
        this.angry=true;
        this.canJump=true;
        this.color="yellow-green";
    }

    public boolean isHungry(){
        return this.hungry;
    }

    private boolean isCanJump(){
        return this.canJump;
    }

    protected boolean isAngry(){
        return this.angry;
    }

    String getColor(){
        return this.color;
    }
}

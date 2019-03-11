package ua.levelup.XML.LookupMethodInjection;

public interface Guy {
    void findDamsel();
    //Вообще сеттеры и геттеры не стоит выносить в интерфейсы (которые по задумке представляют бизнес-логику)
    //Но в данном случае геттер вынесен в интерфейс в целях демонстрации
    Damsel getDamsel();
}

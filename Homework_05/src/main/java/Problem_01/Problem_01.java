package Problem_01;
//1) Необходимо реализовать иерархию цветов (для примера, пусть это будут розы, гвоздики, тюльпаны и... что-то на свой вкус).
// Создать несколько объектов-цветов. Собрать букет с определением его стоимости.
// В букет может входить несколько цветов одного типа.
public class Problem_01 implements Interfaces.IProblem {
    public void solve(){
        RoseFlower rose = new RoseFlower("yellow", 1.5);
        Carnation carnation = new Carnation("red", 0.9);
        Tulip tulip = new Tulip("orange", 1.1);
        Gladiolus gladiolus = new Gladiolus("white", 1.2);

        Bouquet bouquet = new Bouquet();

        //Добавляем цветы
        bouquet.addFlower(rose);
        bouquet.addFlower(carnation);
        bouquet.addFlower(tulip);
        bouquet.addFlower(gladiolus);

        System.out.println(bouquet);

        bouquet.addFlower(rose);    //Попытка добавить тот же самый экземпляр розы не дает никакого результата

        System.out.println(bouquet);

        //Добавяем две новые розы
        bouquet.addFlower(new RoseFlower("yellow", 1.5));
        bouquet.addFlower(new RoseFlower("yellow", 1.5));

        System.out.println(bouquet);

        //Удаляем все розы из букета
        bouquet.excludeFlower(rose);

        System.out.println(bouquet);

        //Очищаем букет
        bouquet.clear();

        System.out.println(bouquet);
    }
}

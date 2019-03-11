package Generics.WildcardsRevelation;

import Generics.WildcardsRevelation.ReturnTypes.Father;
import Generics.WildcardsRevelation.ReturnTypes.Son;
import Interfaces.IProblem;

import java.util.function.Function;
import java.util.function.Predicate;

public class Problem implements IProblem {
    @Override
    public void solve() {
        //ЧТО ПОКАЗЫВАЕТ ПРИМЕР
        //Пример был написан для того, чтобы разобраться в следующем запутанном выражении
        //<R> R map(Function<? super T, ? extends R> function)
        //КУДА СМОТРЕТЬ - смотреть весь метод public void solve() и класс MyStream.
        //Классы Animal - Fish - Pike представляют линию наследования и для понимания примера их код не важен.
        //ОБОБЩЕНИЯ
        //1. Wildcards - нечто, не имеющее перевода. Под этим термином подразумевают два выражения:
        //     ? super T
        //     ? extends T
        //2. Если в параметрах метода указан класс или интерфейс, параметризированный каким-то конкретным классом,
        //например ArrayList<Fish> или Predicate<Animal>, то в этот метод можно будет передавать объекты,
        //параметризированные ТАКИМИ И ТОЛЬКО ТАКИМИ классами. ПРИВЕДЕНИЕ ТИПОВ ТУТ НЕ РАБОТАЕТ.
        //3. Для того чтобы избавиться от ограничения, описанного в пункте 2, применяется wildcard ? super T
        //4. Wildcard ? extends T применяется для того чтобы можно было передать в качестве параметра
        //какой-то функциональный интерфейс, возвращающий не просто класс T, а его наследника.
        //При этом будет выполнятся приведение типа к необходимому типу T.
        //5. Wildcards касаются передачи в качестве параметров только дженериков. Если передается просто ссылка
        //на метод с конкретными типами данных или лямбда-выражение, то КАК Я ПОНЯЛ, для того чтобы
        //работало приведение типов, можно обойтись без ? super T и ? extends T

        //Поместим полученные ArrayList-ы в объекты MyStream, параметризированные классами Animal, Fish, Pike
        MyStream<Animal> animalStream = new MyStream<>(new Animal("Whale",true));
        MyStream<Fish> fishStream = new MyStream<>(new Fish("Murena",true));
        MyStream<Pike> pikeStream = new MyStream<>(new Pike("Pike", true));

        //Подготовим три предиката, параметризированные классами Animal, Fish, Pike
        Predicate<Animal> animalPredicate = new Predicate<Animal>() {
            @Override
            public boolean test(Animal animal) {
                return animal.isWaterAnimal();
            }
        };

        Predicate<Fish> fishPredicate = new Predicate<Fish>() {
            @Override
            public boolean test(Fish fish) {
                return fish.isSaltWaterFish();
            }
        };

        Predicate<Pike> pikePredicate = new Predicate<Pike>() {
            @Override
            public boolean test(Pike pike) {
                return pike.isHungry();
            }
        };

        //ЧАСТЬ 1. РАЗБОР МЕТОДА public void filterT(Predicate<T> predicate)
        System.out.println("====================================================================");
        //Метод filterT в данном случае параметризирован так: filterT(Predicate<Animal> predicate)
        //Это значит что в него можно передать только тот предикат, который параметризирован Animal
        animalStream.filterT(animalPredicate);
        //animalStream.filterT(fishPredicate);    //Predicate<Fish> не может быть передан вместо Predicate<Animal>
        //animalStream.filterT(pikePredicate);    //Predicate<Pike> не может быть передан вместо Predicate<Animal>

        System.out.println("====================================================================");
        //Метод filterT в данном случае параметризирован так: filterT(Predicate<Fish> predicate)
        //Это значит что в него можно передать только тот предикат, который параметризирован Fish
        //fishStream.filterT(animalPredicate);    //Predicate<Animal> не может быть передан вместо Predicate<Fish>
        fishStream.filterT(fishPredicate);
        //fishStream.filterT(pikePredicate);      //Predicate<Animal> не может быть передан вместо Predicate<Fish>

        System.out.println("====================================================================");
        //Метод filterT в данном случае параметризирован так: filterT(Predicate<Pike> predicate)
        //Это значит что в него можно передать только тот предикат, который параметризирован Pike
        //pikeStream.filterT(animalPredicate);    //Predicate<Animal> не может быть передан вместо Predicate<Pike>
        //pikeStream.filterT(fishPredicate);      //Predicate<Fish> не может быть передан вместо Predicate<Pike>
        pikeStream.filterT(pikePredicate);

        //ЧАСТЬ 2. РАЗБОР МЕТОДА public void filterSuperT(Predicate<? super T> predicate)
        System.out.println("====================================================================");
        //Сюда можно передать предикат параметризированный классом Animal, или его предком
        animalStream.filterSuperT(animalPredicate);
        //animalStream.filterSuperT(fishPredicate);   //Fish  - это не предок для Animal
        //animalStream.filterSuperT(pikePredicate);   //Pike  - это не предок для Animal

        System.out.println("====================================================================");
        //Сюда можно передать предикат параметризированный классом Fish, или его предком (Animal)
        fishStream.filterSuperT(animalPredicate);
        fishStream.filterSuperT(fishPredicate);
        //fishStream.filterSuperT(pikePredicate);     //Pike  - это не предок для Fish

        System.out.println("====================================================================");
        //Сюда можно передать предикат параметризированный классом Pike, или его предком (Animal, Fish)
        pikeStream.filterSuperT(animalPredicate);
        pikeStream.filterSuperT(fishPredicate);
        pikeStream.filterSuperT(pikePredicate);

        //ЧАСТЬ 3. РАЗБОР МЕТОДА public <R> MyStream<R> map(Function<? super T, ? extends R> function)
        //Создадим три Function, параметризированные классами Animal, Fish, Pike
        Function<Animal,Son> animalFunction = new Function<Animal, Son>() {
            @Override
            public Son apply(Animal animal) {
                return new Son();
            }
        };

        Function<Fish,Son> fishFunction = new Function<Fish, Son>() {
            @Override
            public Son apply(Fish fish) {
                return new Son();
            }
        };

        Function<Pike,Son> pikeFunction = new Function<Pike, Son>() {
            @Override
            public Son apply(Pike pike) {
                return new Son();
            }
        };

        MyStream<Father> fatherStream;
        //Возвращаемое значение метода map параметризировано как ? extends R (фактически получается Father)
        //Это значит что метод может возвращать либо тип R, (Father (тот тип, что слева от знака равно))
        //либо любого его наследника (класс Son).
        //При этом на выходе будет выполнено приведения типа к Father (если это необходимо)
        fatherStream = animalStream.map(animalFunction);    //Это работает благодаря ? extends R

        fatherStream = fishStream.map(animalFunction);    //Это работает благодаря ? extends R
        fatherStream = fishStream.map(fishFunction);    //Это работает благодаря ? extends R

        fatherStream = pikeStream.map(animalFunction);    //Это работает благодаря ? extends R
        fatherStream = pikeStream.map(fishFunction);    //Это работает благодаря ? extends R
        fatherStream = pikeStream.map(pikeFunction);    //Это работает благодаря ? extends R

        //Все эти проблемы, которые решаются с помощью wildcards, возникают только если в качестве
        //параметров методов передаются дженерики.
        //Если же мы передаем не дженерик (например, в качестве Function передадим
        //метод Son getSon(Animal animal), то приведение типов от Son к Father будет выполнятся даже
        //если вместо ? extends R указать просто R.
        fatherStream = animalStream.mapWithoutExtendsR(this::getSon);
    }

    private Son getSon(Animal animal){
        return new Son();
    }
}

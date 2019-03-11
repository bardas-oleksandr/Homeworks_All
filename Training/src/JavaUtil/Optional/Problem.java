package JavaUtil.Optional;

import Interfaces.IProblem;

import java.util.Optional;
import java.util.function.Predicate;

public class Problem implements IProblem {
    @Override
    public void solve() {
        System.out.println("========================================================");
        Product product = new Product(1);
        System.out.println(product.getValue().orElse(new Double(13)));


        System.out.println("========================================================");
        product = new Product();
        System.out.println(product.getValue().orElse(new Double(13)));

        System.out.println("========================================================");
        System.out.println("Optional<Integer> filter(Predicate<? super Т>)");
        product = new Product(10);
        Predicate<Double> predicate = (obj)->{
            return obj.doubleValue() == 10;
        };

        Optional<Double> optional = product.getValue();//Получаем из объекта product объект класса Optional<Double>
        if(optional.isPresent()){
            optional = optional.filter(predicate);  //Если объект, содержащийся в объекте Optional, соотвествует условию предиката,
            // мы получим этот объект Optional, иначе - получим пустой объект
        }
        System.out.println(optional.get());

        product = new Product();
        optional = product.getValue();
        if(optional.isPresent()){
            optional = optional.filter(predicate);
        }
        if(optional.isPresent()){
            System.out.println(optional.get());
        }else{
            System.out.println("Optional is empty");
        }

        System.out.println("========================================================");
        System.out.println("U Optional<U> flatMap(Function<? super Т, Optional<U>>)");
        Street street = new Street("Soborna");
        Adress adress = new Adress(street);
        Person person = new Person("Sasha", adress);
        Person homeless = new Person("Vasya");
        System.out.println(person);
        System.out.println(homeless);

        //String streetName = homeless.getAdress().getStreet().getName(); //На любом из этапов может прилететь NullPointerException

        System.out.println("In a long way");
        //Шаг 1
        Optional<Person> optionalPerson = Optional.of(homeless);
        //Шаг 2
        Optional<Adress> optionalAdress = optionalPerson.flatMap(Person::getOptionalAdress);
        //Шаг 3
        Optional<Street> optionalStreet = optionalAdress.flatMap(Adress::getOptionalStreet);
        //Шаг 4
        Optional<String> optionalStreetName = optionalStreet.flatMap(Street::getOptionalStreetName);
        //Шаг 5
        String streetName = optionalStreetName.orElse("None");
        System.out.println(streetName);

        System.out.println("In one step");
        String streetName1 = Optional.of(homeless).
                flatMap(Person::getOptionalAdress).
                flatMap(Adress::getOptionalStreet).
                flatMap(Street::getOptionalStreetName).
                orElse("None");
        System.out.println(streetName1);

        System.out.println("With the map method");
        String streetName2 = Optional.of(homeless).
                map(Person::getStaticAdress).   //Метод map принимает Function, которая возвращает объект класса U.
                //Если объект, вызывающий метод map, является isPresent, то метод map извлекает из него вложенный объект,
                //передает его в Function, которая возвращает объект класса U. Далее метод map оборачивает
                // возвращаемый U в Optional<U>, и возвращает полученный Optional<U>. Если же вызывающий объект пустой,
                // то метод map возвращает Optional.empty()
                map(Adress::getStaticStreet).
                map(Street::getStaticStreetName).
                orElse("None");
        System.out.println(streetName2);

        System.out.println("========================================================");
        System.out.println("ifPresent");
        Optional<Person> optionalPerson1 = Optional.of(person);
        optionalPerson1.ifPresent((x)-> System.out.println(x.toString()));

        System.out.println("========================================================");
        System.out.println("filter");
        optionalPerson1 = optionalPerson1.filter((x)->x.getAdress().equals(adress));
        optionalPerson1.ifPresent((x)-> System.out.println(x.toString()));
    }
}

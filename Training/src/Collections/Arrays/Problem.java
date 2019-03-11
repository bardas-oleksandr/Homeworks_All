package Collections.Arrays;

import Interfaces.IProblem;

import java.util.Arrays;

public class Problem implements IProblem{
    @Override
    public void solve() {
        Animal[] carnivore = {new Animal(1),new Animal(1),new Animal(1),new Animal(1),new Animal(1)};
        Animal[] herbivore = Arrays.copyOf(carnivore,5);

        show(carnivore);
        show(herbivore);    //Смотря по адресам, в массиве herbivore хранятся те же объекты что и в массиве carnivore
    }

    public void show(Animal[] arr){
        for(Animal item: arr){
            System.out.println(item + "\t");
        }
        System.out.println("\n");
    }
}

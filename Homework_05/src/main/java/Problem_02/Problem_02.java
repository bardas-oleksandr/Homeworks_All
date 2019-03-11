package Problem_02;

import java.io.IOException;

//2). Унаследовать характеристики и поведение 3-х классов в одном потомке.
public class Problem_02 implements Interfaces.IProblem {    //Доступ к публичному интерфейсу осуществляется через имя пакета. Для доступа к публичному классу имя пакета было бы не нужно
    //Есть класс Человек
    //Есть класс Волк
    //Есть класс Оборотень, в котором два внутренних класса, унаследованных от Волка и Человека
    //Оборотень может становиться то волком, то человеком
    //Волк может убить человека или волка (у мертвого персонажа методы перестают работать должным образом)
    //Если у человека есть ружье, он может убить другого человека или волка.
    //Оборотня можно убить только тогда, когда он находится в состоянии Человек.
    //Чтобы выполнить условие задачи и унаследовать поведение трех классов - введен класс Священник, унаследованный от оборотня.
    //Священник - оборотень, который может превращаться в человека и волка.
    //Священник может молится (если только он живой и не превращен в данный момент времени в волка)

    public void solve() throws IOException{
        //Создаем персонажей
        Man vendor = new Man(30, 100, false);       //продавец
        Man robber = new Man(35, 80, false);        //грабитель
        Man shepherd = new Man(30, 100, false);       //пастух
        Wolf wolf = new Wolf(100, 30);  //волк
        Priest priest = new Priest(30, 90, false);  //священник
        Werewolf werewolf = new Werewolf(30, 90, true);  //оборотень с оружием

        //Массив людей и волков (не оборотней)
        LivingBeing[] manWolfs = new LivingBeing[4];
        manWolfs[0] = vendor;
        manWolfs[1] = robber;
        manWolfs[2] = shepherd;
        manWolfs[3] = wolf;
        //Массив оборотней
        Werewolf[] werewolfs = new Werewolf[2];
        werewolfs[0] = priest;
        werewolfs[1] = werewolf;

        System.out.println("Starting situation");   //Начальное положение
        Problem_02.showAll(manWolfs, werewolfs);

        //Грабитель покупает оружие и убивает продавца, волк съедает пастуха
        System.out.println("\n\nEpisode 1, where robber buys the gun and kills the vendor, wolf eats the shephard");
        robber.setGun(true);
        System.out.print("Vendor says:");
        vendor.makeSound();
        System.out.println("Vendor attacks robber");
        vendor.attack(robber);  //Продавец пытается защититься от грабителя, но у него нет ружья
        System.out.println("Robber attacks vendor");
        robber.attack(vendor);
        System.out.print("Vendor says:");
        vendor.makeSound();
        System.out.print("Robber says:");
        robber.makeSound();
        System.out.print("Shepherd says:");
        shepherd.makeSound();
        System.out.println("Wolf attacks shephard");
        wolf.attack(shepherd);
        System.out.print("Shepherd says:");
        shepherd.makeSound();
        System.out.print("Wolf says:");
        wolf.makeSound();
        Problem_02.showAll(manWolfs, werewolfs);

        //Священник пытается убить грабителя, но у него это не получается так как священник еще не превратился в волка
        System.out.println("\n\nEpisode 2, where priest tries to kill the robber, but unsuccessfully");
        System.out.print("Robber says:");
        robber.makeSound();
        System.out.println("Priest attacks robber");
        priest.getEntity().attack(robber);
        System.out.print("Robber says:");
        robber.makeSound();
        Problem_02.showAll(manWolfs, werewolfs);

        //Священник превращается в волка и грабитель пытается его убить но безуспешно
        System.out.println("\n\nEpisode 3, where robber tries to kill the priest, but unsuccessfully");
        System.out.print("Priest says:");
        priest.getEntity().makeSound();
        System.out.println("Priest turns into wolf");
        priest.turnIntoWolf();
        System.out.print("Priest says:");
        priest.getEntity().makeSound();
        System.out.println("Robber attacks priest");
        robber.attack(priest.getEntity());
        System.out.print("Priest says:");
        priest.getEntity().makeSound();
        Problem_02.showAll(manWolfs, werewolfs);

        //Священник убивает грабителя и превращается обратно в человека
        System.out.println("\n\nEpisode 4, where priest kills the robber and turns backs into the human being");
        System.out.print("Robber says:");
        robber.makeSound();
        System.out.println("Priest attacks robber");
        priest.getEntity().attack(robber);
        System.out.print("Robber says:");
        robber.makeSound();
        System.out.print("Priest says:");
        priest.getEntity().makeSound();
        System.out.println("Priest turns back into human being");
        priest.turnIntoMan();
        System.out.print("Priest says:");
        priest.getEntity().makeSound();
        Problem_02.showAll(manWolfs, werewolfs);

        //Оборотень с ружьем, нахолясь в состоянии Человек, убивает священника
        System.out.println("\n\nEpisode 5, where werewolf kills the priest with the help of the gun");
        priest.pray();
        System.out.print("Werewolf says:");
        werewolf.getEntity().makeSound();
        System.out.println("Werewolf attacks priest");
        werewolf.getEntity().attack(priest.getEntity());
        priest.pray();
        Problem_02.showAll(manWolfs, werewolfs);

        //Обычный волк убивает оборотня, пока тот не успел превратиться в волка
        System.out.println("\n\nEpisode 6, where wolf kills the werewolf");
        System.out.print("Werewolf says:");
        werewolf.getEntity().makeSound();
        System.out.println("Wolf attacks werewolf");
        wolf.attack(werewolf.getEntity());
        System.out.print("Werewolf says:");
        werewolf.getEntity().makeSound();
        Problem_02.showAll(manWolfs, werewolfs);
    }

    private static void showAll(LivingBeing[] array1, Werewolf[] array2) throws IOException{
        for(LivingBeing person: array1){
            System.out.println(person);
        }
        for(Werewolf person: array2){
            System.out.println(person);
        }
        System.out.print("Press Enter to continue");
        char str = (char)System.in.read();
    }
}
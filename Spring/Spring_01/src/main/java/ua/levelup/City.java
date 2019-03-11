package ua.levelup;

public class City {
    private String name;
    private int population;

    public City(){
        this.name = null;
        this.population = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString(){
        return "City: " + this.name + "\tPopulation:" + this.population;
    }
}

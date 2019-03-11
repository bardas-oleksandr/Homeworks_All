package ua.levelup;

public class Princess {
    private int weight;
    private String name;
    private boolean virgin;
    private Guy fucker;

    public Princess() {
        this.weight=0;
        this.virgin = true;
        this.name = null;
        this.fucker = null;
    }

    public Princess(int weight, String name, boolean virgin){
        this.weight = weight;
        this.name = name;
        this.virgin = virgin;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVirgin(boolean virgin) {
        this.virgin = virgin;
    }

    public void setFucker(Guy fucker) {
        this.fucker = fucker;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isVirgin() {
        return virgin;
    }

    @Override
    public String toString(){
        return "Name:" + this.name
                +"\nWeight:" + this.weight
                +"\nVirgin:" + this.virgin
                +"\nFucks with:" + this.fucker;
    }
}

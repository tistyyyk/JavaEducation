package main.lesson6.homework;

public class Counter {
    private static Counter instance;
    protected int numberOfCats;
    protected int numberOfDogs;

    private Counter() {}

    public static Counter getInstance(){
        if(instance == null){
            instance = new Counter();
        }
        return instance;
    }

    public int getNumberOfCats(){
        return numberOfCats;
    }

    public int getGetNumberOfDogs(){
        return numberOfDogs;
    }

    public void addNumberOfCats() {
        numberOfCats++;
    }

    public void addNumberOfDogs() {
        numberOfDogs++;
    }

    public void printNumberOfCats() {
        System.out.println("Всего кошек - " + numberOfCats);
    }

    public void printNumberOfDogs() {
        System.out.println("Всего собак - " + numberOfDogs);
    }
}

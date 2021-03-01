package main.lesson6.homework;

public class Counter {
    private static Counter instance;
    protected int numberOfCats;
    protected int numberOfDogs;
    protected int numberOfAnimals;

    private Counter() {}

    public static Counter getInstance(){
        if(instance == null){
            instance = new Counter();
        }
        return instance;
    }

    public void addNumberOfCats() {
        numberOfCats++;
    }

    public void addNumberOfDogs() {
        numberOfDogs++;
    }

    public void addNumberOfAnimals() {
        numberOfAnimals++;
    }

    public void printNumberOfCats() {
        System.out.println("Всего кошек - " + numberOfCats);
    }

    public void printNumberOfDogs() {
        System.out.println("Всего собак - " + numberOfDogs);
    }

    public void printNumberOfAnimals() {
        System.out.println("Всего животных - " + numberOfAnimals);
    }
}

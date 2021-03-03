package main.lesson6.homework;

public class Dog extends Animal{
    private final int limitRun = 500;
    private final int limitSwim = 10;
    private Counter counter = Counter.getInstance();

    public Dog(String gender, String name) {
        super(gender, name);
        counter.addNumberOfDogs();
    }

    @Override
    public void run(int meters) {
        if (meters <= limitRun) {
            super.run(meters);
        }
    }

    @Override
    public void swim(int meters) {
        if (meters <= limitSwim) {
            super.swim(meters);
        }
    }
}

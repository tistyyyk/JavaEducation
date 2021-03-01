package main.lesson6.homework;

public class Cat extends Animal {
    private final int limitRun = 200;
    private Counter counter = Counter.getInstance();

    public Cat(String gender, String name) {
        super(gender, name);
        counter.addNumberOfCats();
    }

    @Override
    public void run(int meters) {
        if (meters <= limitRun) {
            super.run(meters);
        }
    }
}

package main.lesson7.homework;

import java.util.Random;

public class Cat {
    private Random random = new Random();
    private String name;
    protected boolean satiety;
    private String feminine = "";
    private final int appetite = random.nextInt(5)+15;


    public Cat(String name, String gender) {
        this.name = name;
        if (gender.equals("female")) {feminine="а";}
        satiety = false;
    }

    public void eat(Plate plate) {
        if (!satiety) {
            if (appetite > plate.getFood()) {
                return;
            }
            plate.decreaseFood(appetite);
            satiety = true;
        }
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (satiety) {
            return this.name + " " + "сыт" + feminine;
        }
        return this.name + " хочет кушать";
    }
}

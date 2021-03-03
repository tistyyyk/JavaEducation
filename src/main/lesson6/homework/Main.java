package main.lesson6.homework;

import java.util.Random;

public class Main {
    public static void main (String args[]) {
        Random random = new Random();

        Counter counter = Counter.getInstance();

        Cat catPushinka = new Cat("female", "Пушинка");
        Cat catBarsik = new Cat("male", "Барсик");
        Cat catMarkiz = new Cat("male", "Маркиз");
        Dog dogBobik = new Dog("male", "Бобик");
        Dog dogBelka = new Dog("female", "Белка");
        Dog dogStrelka = new Dog("female", "Стрелка");

        Animal[] animals = {
                catBarsik,
                catMarkiz,
                catPushinka,
                dogBelka,
                dogBobik,
                dogStrelka
        };

        for (int i = 0; i < animals.length; i++) {
            animals[i].run(random.nextInt(800));
            if (!(animals[i] instanceof Cat)) {
                animals[i].swim(random.nextInt(20));
            }
        }

        counter.printNumberOfCats();
        counter.printNumberOfDogs();
        counter.printNumberOfAnimals();
    }
}

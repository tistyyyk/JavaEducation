package main.lesson7.homework;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Cat martin = new Cat("Мартин", "male");
        Cat barsik = new Cat("Барсик", "male");
        Cat pushinka = new Cat("Пушинка", "female");
        Cat murka = new Cat("Мурка", "female");
        Cat leto = new Cat("Герцог Лето Атрейдес", "male");

        Cat[] cats = {martin,barsik,pushinka,murka,leto};

        Plate plate = new Plate(60);

        feedCats(cats, plate);
        printInfo(cats);

        System.out.println();
        plate.increaseFood(20);

        feedCats(cats, plate);
        printInfo(cats);

    }

    private static void feedCats(Cat[] cats, Plate plate) {
        for (Cat cat: cats) {
            cat.eat(plate);
            //plate.printInfo();
        }
    }

    private static void printInfo(Cat[] cats) {
        for (Cat cat: cats) {
            cat.printInfo();
        }
    }
}

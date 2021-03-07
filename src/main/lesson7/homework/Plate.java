package main.lesson7.homework;

public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void decreaseFood(int countFoods) {
            food -= countFoods;
    }

    public void increaseFood(int countFoods) {
        food+=countFoods;
        System.out.println("В миску добавили еды");
        System.out.println();
    }

    public void printInfo() {
        System.out.println("Кол-во еды в миске: " + food);
    }

    public int getFood() {
        return food;
    }
}


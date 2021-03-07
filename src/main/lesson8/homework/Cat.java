package main.lesson8.homework;

public class Cat implements IParticipant{

    private static int runnable = 10;
    private static int jumpable = 20;

    public Cat () {}

    @Override
    public void run(int length) {
        System.out.println("Кот пробежал "+length+"м.");
    }

    @Override
    public void jump(int heigth) {
        System.out.println("Кот перепрыгнул "+heigth+"м.");
    }

    public int getRunnable(){
        return runnable;
    }

    public int getJumpable(){
        return jumpable;
    }

    @Override
    public String toString() {
        return "Кот";
    }
}

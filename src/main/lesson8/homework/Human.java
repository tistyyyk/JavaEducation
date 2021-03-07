package main.lesson8.homework;

public class Human implements IParticipant{

    private static int runnable = 20;
    private static int jumpable = 10;

    public Human() {}

    @Override
    public void run(int length) {
        System.out.println("Человек пробежал "+length+"м.");
    }

    @Override
    public void jump(int heigth) {
        System.out.println("Человек перепрыгнул "+heigth+"м.");
    }

    public int getRunnable(){
        return runnable;
    }

    public int getJumpable(){
        return jumpable;
    }

    @Override
    public String toString() {
        return "Человек";
    }
}

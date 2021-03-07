package main.lesson8.homework;

public class Robot implements IParticipant{

    private static int runnable = 100;
    private static int jumpable = 100;

    public Robot() {}

    @Override
    public void run(int length) {
        System.out.println("Робот пробежал "+length+"м.");
    }

    @Override
    public void jump(int heigth) {
        System.out.println("Робот перепрыгнул "+heigth+"м.");
    }

    public int getRunnable(){
        return runnable;
    }

    public int getJumpable(){
        return jumpable;
    }

    @Override
    public String toString() {
        return "Робот";
    }
}

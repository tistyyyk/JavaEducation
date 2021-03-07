package main.lesson8.homework;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int barriersCount = 5;


        Cat cat = new Cat();
        Human human = new Human();
        Robot robot = new Robot();

        IParticipant[] participants = {cat, human, robot};
        IBarrier[] barriers = new IBarrier[5];

        makeRace(random, barriers);//создаем трассу из рандомных препятствий рандомной величины
        System.out.println(Arrays.toString(barriers));

        runRace(participants, barriers);

    }

    private static void runRace(IParticipant[] participants, IBarrier[] barriers) {
        for (IParticipant participant: participants) {
            for (IBarrier barrier: barriers) {
                if (!barrier.overcome(participant)) {
                    System.out.println(participant.toString()+" выбыл.");
                    break;
                }
            }
        }
    }

    private static void makeRace(Random random, IBarrier[] barriers) {
        for (int i = 0; i < barriers.length; i++) {
            switch (random.nextInt(2)) {
                case 0 :
                    barriers[i] = new Wall(random.nextInt(20)+5);
                    break;
                case 1:
                    barriers[i] = new Racetrack(random.nextInt(20)+5);
                    break;
                default:
                    break;
            }
        }
    }
}

package main.lesson3.homework;

import java.util.Random;
import java.util.Scanner;

public class Task1 {
    static final Scanner in = new Scanner(System.in);
    static final Random random = new Random();
    static final int YES = 1;
    static final int NO = 0;
    static final int DIAPAZON = 9;
    static final int COUNT = 3;

    public static void main(String[] args) {
        hotOrCold();
        int answer = -1;
        do {
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if (in.hasNextInt()) {
                answer = in.nextInt();
                if (answer == YES) {
                    hotOrCold();
                } else if (answer != NO) {
                    in.nextLine();
                    System.out.println("Не понимаю, что вы имеете в виду...");
                    continue;
                }
            } else {
                in.nextLine();
                System.out.println("Вы не выбрали ответ!");
            }
        } while (answer != NO);
    }

    private static void hotOrCold() {
        int hiddenNumber = random.nextInt(DIAPAZON);
        System.out.println(hiddenNumber);
        int userNumber = -1;
        int i = COUNT;
        boolean result = false;

        do {
            System.out.println("Попытка №" + (COUNT-i+1));
            boolean checkValid = false;
            do{
                System.out.println("Введите число от 0 до 9:");
                if (in.hasNextInt()){
                    userNumber = in.nextInt();
                    if (checkDiapazon(userNumber)) {
                        checkValid = true;
                    } else {
                        System.out.println("Это неверное число!");
                    }
                } else {
                    in.nextLine();
                    System.out.println("Вы ввели не число!");
                }
            } while (!checkValid);
            result = checkWin(hiddenNumber, userNumber);
            i--;
        } while (!result && i>0);
        if (!result) {
            System.out.println("Вы не угадали число");
        }
    }

    private static boolean checkDiapazon(int userNumber) {
        if (userNumber >= 0 && userNumber <= DIAPAZON) {
            return true;
        }
        return false;
    }

    private static boolean checkWin(int hiddenNumber, int userNumber) {
        if (hiddenNumber==userNumber) {
            System.out.println("Вы угадали!");
            return true;
        } else if (userNumber >  hiddenNumber) {
            System.out.println("Ваше число больше загаданного");
            return false;
        } else {
            System.out.println("Ваше число меньше загаданного");
            return false;
        }
    }
}

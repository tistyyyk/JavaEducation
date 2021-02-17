package main.lesson4;

import java.util.Random;
import java.util.Scanner;

public class XandO {

    static final int SIZE = 3;

    static final char DOT_EMPTY = '•';
    static final char DOT_HUMAN = 'X';
    static final char DOT_AI = 'O';

    static final char HEADER_FIRST_SYMBOL = '♥';
    static final String EMPTY = " ";

    static final char[][] MAP = new char[SIZE][SIZE];
    static final Scanner in = new Scanner(System.in);
    static final Random random = new Random();

    static int turnsCount;

    public static void main(String[] args) {
        turnGame();
    }

        public static void turnGame() {

            //инициализация поля
            initMap();

            //печать поля в консоль
            printMap();

            //игра
            playGame();

        }

    private static void initMap() {
            for (int i = 0; i<SIZE; i++) {
                for (int j = 0; j<SIZE; j++) {
                    MAP[i][j] = DOT_EMPTY;
                }
            }
        }

    private static void printMap() {
        //печать заголовка
        printHeaderMap();
        //печать поля
        printBodyMap();
    }

    private static void printHeaderMap() {
        System.out.print(HEADER_FIRST_SYMBOL + EMPTY);

        for (int i = 0; i<SIZE; i++) {
            System.out.print(i+1 + EMPTY);
        }

        System.out.println();
    }

    private static void printMapNumber(int i) {
        System.out.print(i+1 + EMPTY);
    }

    private static void printBodyMap() {
        for (int i = 0; i<SIZE; i++) {
            printMapNumber(i);
            for (int j = 0; j<SIZE; j++) {
                System.out.print(MAP[i][j] + EMPTY);
            }
            System.out.println();
        }
    }

    private static void playGame() {
        while (true) {
            turnsCount = 0;
            //ход человека
            humanTurn();
            printMap();
            //проверка окончания игры
            checkEnd(DOT_HUMAN);

            //ход компьютера
            aiTurn();
            printMap();
            //проверка окончания игры
            checkEnd(DOT_AI);
        }
    }

    private static void checkEnd(char symbol) {
        if (checkWin(symbol)) {
            if (symbol == DOT_HUMAN) {
                System.out.println("Ура! Вы победили!");
            } else {
                System.out.println("Восстание близко... ИИ победил...");
            }
            System.exit(0);
        }

        else if (isMapFull()) {
            System.out.println("Ничья!");
            System.exit(0);
        }
    }

    private static boolean isMapFull() {
        return turnsCount == SIZE*SIZE;
    }

    private static boolean checkWin(char symbol) {
        if (MAP[0][0] == symbol && MAP[0][1] == symbol && MAP[0][2] == symbol) return true;
        if (MAP[1][0] == symbol && MAP[1][1] == symbol && MAP[1][2] == symbol) return true;
        if (MAP[2][0] == symbol && MAP[2][1] == symbol && MAP[2][2] == symbol) return true;

        if (MAP[0][0] == symbol && MAP[1][0] == symbol && MAP[2][0] == symbol) return true;
        if (MAP[0][1] == symbol && MAP[1][1] == symbol && MAP[2][1] == symbol) return true;
        if (MAP[0][2] == symbol && MAP[1][2] == symbol && MAP[2][2] == symbol) return true;

        if (MAP[0][0] == symbol && MAP[1][1] == symbol && MAP[2][2] == symbol) return true;
        if (MAP[0][2] == symbol && MAP[1][1] == symbol && MAP[2][0] == symbol) return true;

        return false;
    }

    private static void aiTurn() {
        int rowNumber;
        int columnNumber;

        System.out.println("Ход компьютера:");

        do{
          rowNumber = random.nextInt(SIZE);
          columnNumber = random.nextInt(SIZE);
        } while (!isCellOccupancy(rowNumber,columnNumber));
        MAP[rowNumber][columnNumber] = DOT_AI;
        turnsCount++;
    }

    private static void humanTurn() {
        int rowNumber;
        int columnNumber;
        boolean isInputValid;
        System.out.println("Ход человека. Введите номер строки и столбца.");
         do {
             rowNumber = -1;
             columnNumber = -1;
             isInputValid = true;

             System.out.print("Строка: ");
             if (in.hasNextInt()) {
                 rowNumber = in.nextInt() - 1;
             } else {
                 processingIncorrectMethod();
                 isInputValid = false;
                 continue;
             }

             System.out.print("Столбик: ");
             if (in.hasNextInt()) {
                 columnNumber = in.nextInt() - 1;
             } else {
                 processingIncorrectMethod();
                 isInputValid = false;
                 continue;
             }
         } while (!isInputValid && !isHumanTurnValid(rowNumber, columnNumber));

        MAP[rowNumber][columnNumber] = DOT_HUMAN;
        turnsCount++;
    }

    private static boolean isHumanTurnValid(int rowNumber, int columnNumber) {
        if (!isNumbersValid(rowNumber, columnNumber)) {
            System.out.println("Проверьте значения строки и столбца!");
            return false;
        } else if (!isCellOccupancy(rowNumber, columnNumber)) {
            System.out.println("Вы выбрали занятую ячейку!");
            return false;
        }
        return true;
    }

    private static boolean isCellOccupancy(int rowNumber, int columnNumber) {
        return MAP[rowNumber][columnNumber] == DOT_EMPTY;
    }

    private static boolean isNumbersValid(int rowNumber, int columnNumber) {
        return !(rowNumber >= SIZE || rowNumber < 0 || columnNumber >= SIZE || columnNumber <0);
    }

    private static void processingIncorrectMethod() {
        in.nextLine();
        System.out.println("Ошибка ввода! Введите число в диапазоне размера игрового поля!");
    }
}

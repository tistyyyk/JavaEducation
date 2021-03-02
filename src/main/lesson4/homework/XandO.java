package main.lesson4.homework;

import java.util.Arrays;
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
    static final int WINLENGTH = 3;

    static int turnsCount;

    static int rowNumberHuman;
    static int columnNumberHuman;

    static int rowNumberAI;
    static int columnNumberAI;

    static final int[][] dangerMap = new int[SIZE+2][SIZE+2];

    public static void main(String[] args) {
        turnGame();
    }

        public static void turnGame() {
            initMap();
            printMap();
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
            checkEnd(DOT_HUMAN);

            //ход компьютера
            aiTurn();
            printMap();
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


// Новая проверка на выигрыш для любого поля и любой длины выигрышной комбинации (перебором всего и вся)

    private static boolean checkWin(char symbol) {
        int diagonalCount = (1+2*(SIZE-WINLENGTH))*2;
        String[] resultLines = new String[SIZE*2];
        String[] resultDiagonals = new String[diagonalCount+2];

        initializeResults(resultLines);
        initializeResults(resultDiagonals);

        checkLines(resultLines);
        checkDiagoniles(resultDiagonals);

        if (findCombination(symbol, resultLines)) return true;
        else if (findCombination(symbol, resultDiagonals)) return true;

        return false;
    }

    private static boolean findCombination(char symbol, String[] results) {
        for (int i = 0; i < results.length; i++) {
            if (results[i].contains(getWinLine(symbol))) {
                return true;
            }
        }
        return false;
    }

    private static void checkDiagoniles(String[] results) {
        int count = 0;
        int j = 0;

        //основные диагонали
        do {
            for (int i = 0; i < SIZE-j; i++) {
                results[count]+=MAP[i][i+j];
                results[count+1]+=MAP[i+j][i];
            }
            j++;
            count+=2;
        } while (j<=SIZE-WINLENGTH);

        j = 0;

        //побочные диагонали
        do {
            for (int i = 0; i < SIZE-j; i++) {
                results[count]+=MAP[i][SIZE-i-j-1];
                results[count+1]+=MAP[i+j][SIZE-i-1];
            }
            j++;
            count+=2;
        } while (j<=SIZE-WINLENGTH);
    }

    private static void checkLines(String[] results) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                results[count] += MAP[i][j];
            }
            count++;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                results[count] += MAP[j][i];
            }
            count++;
        }
    }

    private static void initializeResults(String[] results) {
        for (int i = 0; i < results.length; i++) {
            results[i]="";
        }
    }

    private static String getWinLine(char symbol) {
        String winline = "";
        for (int i = 0; i < WINLENGTH; i++) {
            winline+=symbol;
        }
        return winline;
    }

    // --------------------------------------------
    // Новая функция умного хода  AI

    private static void aiTurn() {

        System.out.println("Ход компьютера:");

        updateDangerMap();
        analyzeHumanTurn();

        MAP[rowNumberAI][columnNumberAI] = DOT_AI;

        turnsCount++;
    }

    private static void analyzeHumanTurn() {

        if (!isDangerousSituation()) {
            searchForMaxDangerousPlace();
        }
    }

    private static void searchForMaxDangerousPlace() {
        int max = 0;
        int row = 0;
        int column = 0;

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                if(dangerMap[i][j] > max && isCellOccupancy(i,j)) {
                    max = dangerMap[i][j];
                    row = i;
                    column = j;
                }
            }
        }

        rowNumberAI = row;
        columnNumberAI = column;
    }

    private static boolean isDangerousSituation() {
        char[][] tempMap = new char[SIZE+2][SIZE+2];
        int row = rowNumberHuman+1;
        int column = columnNumberHuman+1;

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                tempMap[i+1][j+1] = MAP[i][j];
            }
        }

        if (DOT_HUMAN == tempMap[row - 1][column - 1] && findPlace(1)) {
            return true;
        } else if (DOT_HUMAN == tempMap[row - 1][column] && findPlace(2)) {
            return true;
        } else if (DOT_HUMAN == tempMap[row][column - 1] && findPlace(3)) {
            return true;
        } else if (DOT_HUMAN == tempMap[row + 1][column + 1] && findPlace(4)) {
            return true;
        } else if (DOT_HUMAN == tempMap[row + 1][column] && findPlace(5)) {
            return true;
        } else if (DOT_HUMAN == tempMap[row][column + 1] && findPlace(6)) {
            return true;
        } else if (DOT_HUMAN == tempMap[row + 1][column - 1] && findPlace(7)) {
            return true;
        } else if (DOT_HUMAN == tempMap[row - 1][column + 1] && findPlace(8)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean findPlace(int i) {
        int row = 0;
        int column =0;
        switch (i) {
            case (1):
                if (rowNumberHuman+1 < SIZE && columnNumberHuman+1 < SIZE) {
                    row = rowNumberHuman+1;
                    column = columnNumberHuman+1;
                } else {
                    row = rowNumberHuman-2;
                    column = columnNumberHuman-2;
                }
                break;
            case (2):
                if (rowNumberHuman+1 < SIZE) {
                    row = rowNumberHuman+1;
                } else {
                    row = rowNumberHuman-2;
                }
                column = columnNumberHuman;
                break;
            case (3):
                if (columnNumberHuman+1 < SIZE) {
                    column = columnNumberHuman+1;
                } else {
                    column = columnNumberHuman-2;
                }
                row = rowNumberHuman;
                break;
            case (4):
                if (columnNumberHuman-1 >= 0 && rowNumberHuman-1 >=0) {
                    row = rowNumberHuman-1;
                    column = columnNumberHuman-1;
                } else {
                    row = rowNumberHuman+2;
                    column = columnNumberHuman+2;
                }
                break;
            case (5):
                if (rowNumberHuman-1 >=0) {
                    row = rowNumberHuman-1;
                } else {
                    row = rowNumberHuman+2;
                }
                column = columnNumberHuman;
                break;
            case (6):
                if (columnNumberHuman-1 >=0) {
                    row = rowNumberHuman;
                    column = columnNumberHuman-1;
                } else {
                    row = rowNumberHuman;
                    column = columnNumberHuman+2;
                }
                break;
            case (7):
                if (rowNumberHuman-1 >=0 && columnNumberHuman+1 < SIZE) {
                    row = rowNumberHuman-1;
                    column = columnNumberHuman+1;
                } else {
                    row = rowNumberHuman+2;
                    column = columnNumberHuman-2;
                }
                break;
            case (8):
                if (columnNumberHuman-1 >=0 && rowNumberHuman+1 < SIZE) {
                    row = rowNumberHuman+1;
                    column = columnNumberHuman-1;
                } else {
                    row = rowNumberHuman-2;
                    column = columnNumberHuman+2;
                }
                break;
            default:
                break;
        }
        if (isCellOccupancy(row,column)) {
            rowNumberAI = row;
            columnNumberAI = column;
            return true;
        } else return false;
    }

    private static void updateDangerMap() {
        int[][] tempMap = new int[SIZE+2][SIZE+2];
        int row = rowNumberHuman+1;
        int column = columnNumberHuman+1;

        tempMap[row-1][column-1]++;
        tempMap[row-1][column]++;
        tempMap[row][column-1]++;
        tempMap[row+1][column+1]++;
        tempMap[row+1][column]++;
        tempMap[row][column+1]++;
        tempMap[row+1][column-1]++;
        tempMap[row-1][column+1]++;

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                dangerMap[i][j]+=tempMap[i+1][j+1];
            }
        }
    }

    //-----------------------------------------------

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

        rowNumberHuman = rowNumber;
        columnNumberHuman = columnNumber;

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
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
        turnsCount = 0;
        while (true) {
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

    //----------------------------------------------------------------------
    //Проверка на выигрыш для любого поля и любой длины выигрышной комбинации
    private static boolean checkWin(char symbol) {
        String[] surroundings = new String[4];//массив, куда положим строку, столбец и две диагонали вокруг хода игрока

        initializeResults(surroundings);//заполним пустыми строками
        getSurroundings(surroundings);//заполняем данными

        if (findCombination(symbol, surroundings)) return true; //если после хода случилась выигрышная комбинация - то победа
        else return false;
    }

    //инициализации массива строк
    private static void initializeResults(String[] surroundings) {
        for (int i = 0; i < surroundings.length; i++) {
            surroundings[i]="";
        }
    }

    //ищем в массиве строк строку с победной комбинацией
    private static boolean findCombination(char symbol, String[] results) {
        for (int i = 0; i < results.length; i++) {
            if (results[i].contains(getWinLine(symbol))) {
                return true;
            }
        }
        return false;
    }

    //на основании заданной длины победной комбинации делаем эталонную строку победы для проверок
    private static String getWinLine(char symbol) {
        String winline = "";
        for (int i = 0; i < WINLENGTH; i++) {
            winline+=symbol;
        }
        return winline;
    }

    //собираем массив, куда положим строку, столбец и две диагонали вокруг хода игрока
    private static void getSurroundings(String[] surroundings) {
        int count = 0;
        int j;

        for (int i = 0; i < SIZE; i++) {
            surroundings[count]+=MAP[rowNumberHuman][i];
        }
        count++;

        for (int i = 0; i < SIZE; i++) {
            surroundings[count]+=MAP[i][columnNumberHuman];
        }
        count++;

        // основная диагональ
        j = rowNumberHuman-columnNumberHuman;
        if (j >= 0) {
            for (int i = j; i < SIZE; i++) {
                surroundings[count]+=MAP[i][i-j];
            }
        } else {
            for (int i = 0; i < SIZE+j; i++) {
                surroundings[count]+=MAP[i][i-j];
            }
        }
        count++;

        //побочная диагональ
        j = rowNumberHuman+columnNumberHuman;
        if (j < SIZE) {
            for (int i = 0; i <= j ; i++) {
                surroundings[count]+=MAP[j-i][i];
            }
        } else {
            for (int i = j-SIZE+1; i < SIZE; i++) {
                surroundings[count]+=MAP[j-i][i];
            }
        }
    }

    // --------------------------------------------
    // Новая функция умного хода  AI

    private static void aiTurn() {

        System.out.println("Ход компьютера:");

        //после каждого хода юзера анализируем ситуацию
        updateDangerMap();
        analyzeHumanTurn();

        MAP[rowNumberAI][columnNumberAI] = DOT_AI;
        turnsCount++;
    }

    //Выбор стратегии заполнения
    private static void analyzeHumanTurn() {

        if (!isDangerousSituation()) { //если не собирается ряд из 2+ крестиков игрока
            searchForMaxDangerousPlace(); //то делаем ход на основании таблицы опасных мест
        }
    }

    // Функция, которая рассматривает мапу опасности и делает ход в одну из "опаснейших" ячеек
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

    //Функция, которая проверяет, есть ли "опасная ситуация" - рядом с крестиком игрока стоит еще крестик
    private static boolean isDangerousSituation() {
        char[][] tempMap = new char[SIZE+2][SIZE+2];
        int row = rowNumberHuman+1;
        int column = columnNumberHuman+1;
// Заполняем временную мапу, которая нужна для удобства сравнения
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                tempMap[i+1][j+1] = MAP[i][j];
            }
        }

        if (DOT_HUMAN == tempMap[row - 1][column - 1] && findPlace(1)) {//на основной диагонали выше уже есть Х
            return true;
        } else if (DOT_HUMAN == tempMap[row - 1][column] && findPlace(2)) {//строкой выше есть Х
            return true;
        } else if (DOT_HUMAN == tempMap[row][column - 1] && findPlace(3)) {//в ряду ранее есть Х
            return true;
        } else if (DOT_HUMAN == tempMap[row + 1][column + 1] && findPlace(4)) {//на основной диагонали ниже есть Х
            return true;
        } else if (DOT_HUMAN == tempMap[row + 1][column] && findPlace(5)) {//строкой ниже есть Х
            return true;
        } else if (DOT_HUMAN == tempMap[row][column + 1] && findPlace(6)) {//в ряду впереди есть Х
            return true;
        } else if (DOT_HUMAN == tempMap[row + 1][column - 1] && findPlace(7)) {//на побочной диагонали ниже есть Х
            return true;
        } else if (DOT_HUMAN == tempMap[row - 1][column + 1] && findPlace(8)) {//на побочной диагонали выше есть Х
            return true;
        } else {
            return false;//опасной ситуации нет
        }
    }

    //Функция, которая думает, куда ставить 0, если в строке или диагонали есть два Х
    private static boolean findPlace(int i) {
        int row = 0;
        int column =0;
        switch (i) {
            case (1)://на основной диагонали выше уже есть Х
                if (rowNumberHuman+1 < SIZE && columnNumberHuman+1 < SIZE) {//далее по диагонали поле не закончилось?
                    row = rowNumberHuman+1;
                    column = columnNumberHuman+1;
                } else if (rowNumberHuman-2 >= 0 && columnNumberHuman-2 >= 0) {//а ранее по диагонали поле не закончилось?
                    row = rowNumberHuman-2;
                    column = columnNumberHuman-2;
                } //иначе ничего не делаем, вероятно это мелкая диагональ
                break;
            case (2)://строкой выше есть Х
                if (rowNumberHuman+1 < SIZE) {
                    row = rowNumberHuman+1;
                } else if (rowNumberHuman-2 >= 0) {//в строках и столбцах проверки избыточны, но для порядка пусть будут
                    row = rowNumberHuman-2;
                }
                column = columnNumberHuman;
                break;
            case (3)://в ряду ранее есть Х
                if (columnNumberHuman+1 < SIZE) {
                    column = columnNumberHuman+1;
                } else if (columnNumberHuman-2 >= 0) {
                    column = columnNumberHuman-2;
                }
                row = rowNumberHuman;
                break;
            case (4)://на основной диагонали ниже есть Х
                if (columnNumberHuman-1 >= 0 && rowNumberHuman-1 >=0) {
                    row = rowNumberHuman-1;
                    column = columnNumberHuman-1;
                } else if (rowNumberHuman+2 < SIZE && columnNumberHuman+2 < SIZE) {
                    row = rowNumberHuman+2;
                    column = columnNumberHuman+2;
                }
                break;
            case (5)://строкой ниже есть Х
                if (rowNumberHuman-1 >=0) {
                    row = rowNumberHuman-1;
                } else if (rowNumberHuman+2 < SIZE) {
                    row = rowNumberHuman+2;
                }
                column = columnNumberHuman;
                break;
            case (6)://в ряду впереди есть Х
                if (columnNumberHuman-1 >=0) {
                    row = rowNumberHuman;
                    column = columnNumberHuman-1;
                } else if (columnNumberHuman+2 < SIZE) {
                    row = rowNumberHuman;
                    column = columnNumberHuman+2;
                }
                break;
            case (7)://на побочной диагонали ниже есть Х
                if (rowNumberHuman-1 >=0 && columnNumberHuman+1 < SIZE) {
                    row = rowNumberHuman-1;
                    column = columnNumberHuman+1;
                } else if (rowNumberHuman+2 < SIZE && columnNumberHuman-2 >= 0) {
                    row = rowNumberHuman+2;
                    column = columnNumberHuman-2;
                }
                break;
            case (8)://на побочной диагонали выше есть Х
                if (columnNumberHuman-1 >=0 && rowNumberHuman+1 < SIZE) {
                    row = rowNumberHuman+1;
                    column = columnNumberHuman-1;
                } else if (columnNumberHuman+2 < SIZE && rowNumberHuman-2 >= 0){
                    row = rowNumberHuman-2;
                    column = columnNumberHuman+2;
                }
                break;
            default:
                break;
        }
        if (isCellOccupancy(row,column)) { //если что-то нашли, нужно проверить, не стоит ли там уже символ
            rowNumberAI = row;
            columnNumberAI = column;
            return true;
        } else return false;
    }

    //Функция, которая делает такое же поле, как игровое, но где каждая ячейка имеет "индекс опасности"
    private static void updateDangerMap() {
        int[][] tempMap = new int[SIZE+2][SIZE+2];//временная мама побольше, чтобы не добавлять 100500 if'ов для определения границ поля
        int row = rowNumberHuman+1;
        int column = columnNumberHuman+1;

        //увеличиваем индекс опасности ячейкам, которые вокруг ячейки, куда юзер поставил Х
        tempMap[row-1][column-1]++;
        tempMap[row-1][column]++;
        tempMap[row][column-1]++;
        tempMap[row+1][column+1]++;
        tempMap[row+1][column]++;
        tempMap[row][column+1]++;
        tempMap[row+1][column-1]++;
        tempMap[row-1][column+1]++;

        //обрезаем временную мапу под размеры поля и получаем мапу опасности
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
             }
         } while (!isInputValid || !isHumanTurnValid(rowNumber, columnNumber));

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
        System.out.println("Ошибка ввода! Введите число в диапазоне размера игрового поля!");
        in.nextLine();
    }
}

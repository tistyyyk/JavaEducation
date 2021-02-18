package main.lesson3.homework;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task2 {
    static final Scanner in = new Scanner(System.in);
    static final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    static final Random random = new Random();
    static final int SIZE = 25;
    static final char FILL = '#';
    static final String FULFILL = "#############";
    static String result = "";
    static int counter = -1;

    public static void main(String[] args) {
        guessWord();
    }

    private static void guessWord() {
        String hiddenWord = words[random.nextInt(25)];
        //System.out.println(hiddenWord);
        System.out.println("Мы загадали слово, угадайте его!");
        String userWord;
        do {
            userWord = userWord();
            wordsCompare(hiddenWord, userWord);
        } while (!checkWin(hiddenWord, userWord));
    }

    private static void wordsCompare(String hiddenWord, String userWord) {
        int length = findLength(hiddenWord.length(), userWord.length());
        for (int i = 0; i < length; i++) {
            if (hiddenWord.charAt(i) == userWord.charAt(i)) {
                if(i > result.length()-1){
                    result+=hiddenWord.charAt(i);
                    counter = i;
                } else {
                    char[] resultMas = result.toCharArray();
                    resultMas[i]= hiddenWord.charAt(i);
                    result = String.valueOf(resultMas);
                    counter = i;
                }
            } else {
                result+=FILL;
            }
        }
        if (!result.substring(0,counter+1).equals(hiddenWord)){
            System.out.println(result.substring(0, counter+1)+FULFILL);
        }
    }


    private static int findLength(int hidden, int user) {
        int length;
        if (hidden < user) {
            length = hidden;
        } else {
            length = user;
        }
        return length;
    }

    private static boolean checkWin(String hiddenWord, String userWord) {
        if (hiddenWord.equals(userWord)) {
            System.out.println("Вы угадали слово!");
            return true;
        }
        return false;
    }

    private static String userWord() {
        String word = in.nextLine();
        return word;
    }

//    private static boolean checkCorrect(String word) {
//        for (int i = 0; i < SIZE; i++) {
//            if (words[i].equals(word)) {
//                return true;
//            }
//        }
//        System.out.println("Такого слова у нас нет");
//        return false;
//    }
}

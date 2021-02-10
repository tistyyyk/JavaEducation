package main.lesson2.homework;

import java.util.Arrays;

public class Task4 {

    public static void main(String[] args) {
        int[][] arr = new int[9][9];

        for (int i = 0; i < arr.length; i++) {
                arr[i][i] = 1;
                arr[i][arr.length-i-1] = 1;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }

    }
}
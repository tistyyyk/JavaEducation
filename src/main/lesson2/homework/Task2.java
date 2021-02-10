package main.lesson2.homework;

import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {
        int[] arr = new int[8];

        for (int i = 0; i < arr.length; i++) {
            int j = i*3;
            arr[i] = j;
        }

        System.out.println(Arrays.toString(arr));
    }
}

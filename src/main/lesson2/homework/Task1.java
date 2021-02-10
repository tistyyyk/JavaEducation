package main.lesson2.homework;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.abs;

public class Task1 {

    public static void main(String[] args) {
        int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2);
        }
        //System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = abs(--arr[i]);
        }
        System.out.println(Arrays.toString(arr));
    }
}
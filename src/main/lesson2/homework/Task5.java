package main.lesson2.homework;

import java.util.Arrays;
import java.util.Random;

public class Task5 {

    public static void main(String[] args) {
        int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(arr));

// Нормальное решение
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        System.out.println("Минимальный элемент - " + min + ", максимальный элемент - " + max);

// Хитрожопое решение

        Arrays.sort(arr);

        System.out.println("Минимальный элемент - " + arr[0] + ", максимальный элемент - " + arr[arr.length-1]);
    }
}
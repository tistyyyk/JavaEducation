package main.lesson2.homework;

import java.util.Arrays;

public class Task3 {

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]<6) {
                arr[i]*=2;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}

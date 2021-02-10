package main.lesson2.homework;

import java.util.Arrays;

import static java.lang.Math.abs;

public class Task7 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int n = 4;

        if (n>=0) {
            shifterRight(arr, n);
        }
        else {
            shifterLeft(arr, n);
        }

        System.out.println(Arrays.toString(arr));
    }

    private static void shifterRight (int[] arr, int n) {
        n = n % arr.length;
        System.out.println(n);
        if (n == 0) return;
        int i = n;
        while (i>0) {
            for (int j = arr.length; j>n; j--) {
                int c = arr[j-i];
                arr[j-i] = arr[j-i-1];
                arr[j-i-1] = c;
                //System.out.println(Arrays.toString(arr));
            }
           i--;
        }
    }

    private static void shifterLeft (int[] arr, int n) {
        n = n % arr.length;
        System.out.println(n);
        if (n == 0) return;
        int i = abs(n);
        while (i > 0) {
            for (int j = i-1; j < arr.length+n+i-1; j++) {
                int c = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = c;
                //System.out.println(Arrays.toString(arr));
            }
            i--;
        }
    }
}
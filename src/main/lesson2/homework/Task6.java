package main.lesson2.homework;

import java.util.Arrays;

public class Task6 {

    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 1, 2, 2, 10, 1};

        System.out.println(checkBalance(arr));
    }

    private static boolean checkBalance (int[] arr){
        boolean checker = false;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
        }
        //System.out.println(sum);
        if (sum%2==0) {
            int left = 0;
            int right = 0;
            int i = 0;
            while (i < arr.length) {
                left = left + arr[i];
                if (left == sum/2) {
                    for (int j = i+1; j < arr.length; j++) {
                            right+=arr[j];
                        }
                        if (right==left) {
                            checker = true;
                        }
                }
                i++;
            }
        }
        return checker;
    }
}
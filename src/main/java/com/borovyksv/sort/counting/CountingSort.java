package com.borovyksv.sort.counting;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
//        int[] integers = new int[]{5, 1, 5, 4, 3, 2, 1};
        int[] integers = new int[]{5, 1, 5, 4, 3, 2, 1, 7, 9, 10, 8, 6, 5, 4, 3, 2};
        sort(integers, 11);
        System.out.println();
        System.out.println(Arrays.toString(integers));
    }

    private static void sort(int[] arr, int range) {
        int[] count = new int[range + 1];
        int[] result = new int[arr.length];
        for (int i : arr) {
            ++count[i];
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i : arr) {
            int index = --count[i];
            result[index] = i;
        }
        for (int i = 0; i < result.length; i++) {
            arr[i] = result[i];
        }
    }
}

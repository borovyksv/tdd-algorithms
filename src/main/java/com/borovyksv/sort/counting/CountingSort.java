package com.borovyksv.sort.counting;

public class CountingSort {

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

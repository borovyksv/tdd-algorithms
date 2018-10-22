package com.borovyksv.sort.bubble;

import com.borovyksv.sort.Sortable;

public class RecursiveBubbleSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        if (arr.length <= 1) return;
        sort(arr, arr.length);
    }

    private void sort(int[] arr, int length) {
        if (length == 1) return;

        for (int i = 0; i < length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                arr[i] = arr[i] ^ arr[i + 1];
                arr[i + 1] = arr[i] ^ arr[i + 1];
                arr[i] = arr[i] ^ arr[i + 1];
            }
        }
        sort(arr, length - 1);
    }
}

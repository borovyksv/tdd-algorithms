package com.borovyksv.sort.bubble;

import com.borovyksv.sort.Sortable;

public class BubbleSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }
}

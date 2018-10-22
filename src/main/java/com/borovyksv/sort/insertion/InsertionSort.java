package com.borovyksv.sort.insertion;

import com.borovyksv.sort.Sortable;

public class InsertionSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                int tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
            }
        }
    }
}

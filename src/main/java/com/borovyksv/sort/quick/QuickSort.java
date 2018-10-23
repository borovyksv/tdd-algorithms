package com.borovyksv.sort.quick;

import com.borovyksv.sort.Sortable;

public class QuickSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int low, int high) {
        if (low >= high) return;
        int indexForMin = low;
        for (int i = low; i < high; i++) {
            if (arr[i] <= arr[high]) {
                swap(arr, i, indexForMin++);
            }
        }
        swap(arr, high, indexForMin);
        sort(arr, low, indexForMin - 1);
        sort(arr, indexForMin + 1, high);
    }
}

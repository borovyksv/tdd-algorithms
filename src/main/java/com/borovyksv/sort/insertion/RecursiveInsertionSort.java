package com.borovyksv.sort.insertion;

import com.borovyksv.sort.Sortable;

import java.util.Arrays;

public class RecursiveInsertionSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        if (arr.length > 0) {
            sort(arr, 1);
        }
    }

    private void sort(int[] arr, int keyIndex) {
        if (keyIndex == arr.length) return;
        int compareIndex = keyIndex - 1;
        rearrange(arr, keyIndex, compareIndex);
        sort(arr, keyIndex + 1);
    }

    private void rearrange(int[] arr, int keyIndex, int compareIndex) {
        if (compareIndex < 0 || arr[keyIndex] > arr[compareIndex]) return;
        swap(arr, keyIndex, compareIndex);
        rearrange(arr, keyIndex - 1, compareIndex - 1);

    }
}

package com.borovyksv.sort.insertion;

import com.borovyksv.sort.Sortable;

public class InsertionSort2 implements Sortable {

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1] > key; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = key;
        }
    }
}

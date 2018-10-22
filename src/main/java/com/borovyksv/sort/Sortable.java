package com.borovyksv.sort;

public interface Sortable {
    void sort(int[] arr);

    default void swap(int[] arr, int first, int second) {
        int tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }
}

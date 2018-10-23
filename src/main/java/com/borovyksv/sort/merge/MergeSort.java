package com.borovyksv.sort.merge;

import com.borovyksv.sort.Sortable;

public class MergeSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        if (arr.length < 2) return;
        System.arraycopy(sort(arr, 0, arr.length - 1), 0, arr, 0, arr.length);
    }

    private int[] sort(int[] arr, int low, int high) {
        if (high <= low) return new int[]{arr[low]};
        int med = low + (high - low) / 2;
        int[] left = sort(arr, low, med);
        int[] right = sort(arr, med + 1, high);
        int[] result = new int[high + 1 - low];
        merge(result, left, right);
        return result;
    }

    private void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, index = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[index++] = left[i++];
            } else {
                result[index++] = right[j++];
            }
        }
        while (i < left.length) {
            result[index++] = left[i++];
        }
        while (j < right.length) {
            result[index++] = right[j++];
        }
    }
}

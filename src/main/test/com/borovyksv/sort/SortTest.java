package com.borovyksv.sort;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SortTest extends MultiClassSortTest {

    public SortTest(Object className, Sortable sorter) {
        super(className, sorter);
    }

    @Test
    public void testSortEmptyArray() {
        int[] integers = {};
        sorter.sort(integers);
    }

    @Test
    public void testSortOneElementArray() {
        int[] integers = {5};
        sorter.sort(integers);
        assertEquals(5, integers[0]);
    }

    @Test
    public void testSortTwoElementsArray() {
        int[] integers = {99, -99};
        sorter.sort(integers);
        assertEquals(-99, integers[0]);
        assertEquals(99, integers[1]);
    }

    @Test
    public void testSortArrayWithSameElements() {
        int[] integers = {2, 2, 1, 1};
        sorter.sort(integers);
        assertEquals(1, integers[0]);
        assertEquals(1, integers[1]);
        assertEquals(2, integers[2]);
        assertEquals(2, integers[3]);
    }

    @Test
    public void testSortUnorderedArray() {
        int[] integers = {5, 111, 3, 2, 19, -1, 90, 9, 87, 12};
        sorter.sort(integers);
        assertNaturalOrder(integers);
    }

    @Test
    public void testSortReverseOrderedArray() {
        int[] integers = {999, 99, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9};
        sorter.sort(integers);
        assertNaturalOrder(integers);
    }

    @Test
    public void testSortHugeRandomArray() {
        int size = 999;
        int[] integers = new Random().ints(size, 0, size).distinct().toArray();
        sorter.sort(integers);
        assertNaturalOrder(integers);
    }

}

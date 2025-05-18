package org.algorithms.test.copilot.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.algorithms.test.copilot.sort.IterativeMergeSort;
import org.algorithms.test.copilot.sort.IterativeQuickSort;

import java.util.Arrays;


@SpringBootTest
public class SortTests {
    @Autowired
    private IterativeMergeSort iterativeMergeSort;
    @Autowired
    private IterativeQuickSort iterativeQuickSort;

    @Test
    void testArrayIterativeQuickSortingHoare() {
        int[] arr = {12, 11, 13, 5, 6, 7, 3, 2, 2, 2, 1};
        iterativeQuickSort.quickSort(arr, true);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 2, 3, 5, 6, 7, 11, 12, 13}, arr); // Verify sorting
    }

    @Test
    void testArrayIterativeQuickSortingLomuto() {
        int[] arr = {12, 11, 13, 5, 6, 7, 3, 2, 2, 2, 1};
        iterativeQuickSort.quickSort(arr, false);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 2, 3, 5, 6, 7, 11, 12, 13}, arr); // Verify sorting
    }

    @Test
    void testArrayIterativeMergeSorting() {
        int[] arr = {12, 11, 13, 5, 6, 7, 3, 2, 2, 2, 1};
        iterativeMergeSort.mergeSort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 2, 3, 5, 6, 7, 11, 12, 13}, arr);
    }

    @Test
    void testArraySorting() {
        int[] arr = {12, 11, 13, 5, 6, 7, 3, 2, 2, 2, 1};
        Arrays.sort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 2, 3, 5, 6, 7, 11, 12, 13}, arr);
    }
}
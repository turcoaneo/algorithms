package org.toptal.test.copilot.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.toptal.test.copilot.search.Search;

@SpringBootTest
public class SearchTests {
    @Autowired
    private Search search;

    @Test
    void testBinarySearch() {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 15;
        int actual = search.binarySearch(arr, target);
        Assertions.assertEquals(7, actual);
    }

    @Test
    void testNotFoundBinarySearch() {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 10;
        int actual = search.binarySearch(arr, target);
        Assertions.assertEquals(-1, actual);
    }

    @Test
    void testInterpolationSearch() {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 15;
        int actual = search.binarySearch(arr, target);
        Assertions.assertEquals(7, actual);
    }

    @Test
    void testNotFoundInterpolationSearch() {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 10;
        int actual = search.interpolationSearch(arr, target);
        Assertions.assertEquals(-1, actual);
    }

    @Test
    void testTernarySearch() {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 1;
        int actual = search.ternarySearch(arr, target);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void testNotFoundTernarySearch() {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = -1;
        int actual = search.ternarySearch(arr, target);
        Assertions.assertEquals(-1, actual);
    }

    @Test
    void testNnarySearch() {
        int[] arr = {1, 5, 10, 15, 20, 25, 30, 35, 40, 50, 60, 70, 80, 90, 100};
        int target = 40;
        int n = 5; // Example: Splitting into 5 partitions
        int actual = search.nNarySearch(arr, target, n);
        Assertions.assertEquals(8, actual);
    }

    @Test
    void testNotFoundNnarySearch() {
        int[] arr = {1, 5, 10, 15, 20, 25, 30, 35, 40, 50, 60, 70, 80, 90, 100};
        int target = 400;
        int n = 5; // Example: Splitting into 5 partitions
        int actual = search.nNarySearch(arr, target, n);
        Assertions.assertEquals(-1, actual);
    }
}
package org.algorithms.test.copilot.sort;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.algorithms.test.copilot.service.ExampleService;

import java.util.Arrays;

@Component
public class IterativeMergeSort {

    @TrackExecutionTime
    public void mergeSort(int[] arr) {
        int n = arr.length;

        // Iterative merging (subarrays of size 1, then 2, 4, 8...)
        for (int size = 1; size < n; size *= 2) {
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(arr, left, mid, right);
            }
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;
        int[] leftArr = new int[n1], rightArr = new int[n2];

        // Copy elements into temp arrays
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            arr[k++] = (leftArr[i] <= rightArr[j]) ? leftArr[i++] : rightArr[j++];
        }

        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }


    public static void main(String[] args) throws InterruptedException {
        int[] arr = {12, 11, 13, 5, 6, 7, 3, 2, 2, 2, 1};
        new IterativeMergeSort().mergeSort(arr);
        System.out.println(Arrays.toString(arr));
        ExampleService service = new ExampleService();
        service.sampleMethod();
    }
}
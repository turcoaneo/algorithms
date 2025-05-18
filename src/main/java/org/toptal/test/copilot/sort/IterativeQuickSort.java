package org.toptal.test.copilot.sort;

import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.Stack;

@Component
public class IterativeQuickSort {
    @TrackExecutionTime
    public void quickSort(int[] arr, boolean isHoare) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, arr.length - 1}); // Push initial range

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int left = range[0], right = range[1];

            if (left >= right) continue; // Base case in recursion

            int pivot = hoarePartition(arr, left, right); // Partition step
            if (!isHoare) {
                pivot = partition(arr, left, right); // Partition step
            }

            // Push sub-arrays onto stack instead of recursive calls
            stack.push(new int[]{left, pivot - 1});  // Left partition
            stack.push(new int[]{pivot + 1, right}); // Right partition
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // Last element as pivot
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1; // Return pivot index
    }

    private int hoarePartition(int[] arr, int left, int right) {
        int pivot = arr[left]; // First element as pivot
        int i = left - 1, j = right + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot); // Move right until a larger element is found
            do {
                j--;
            } while (arr[j] > pivot); // Move left until a smaller element is found

            if (i >= j) return j; // Partition boundary found

            swap(arr, i, j); // Swap misplaced elements
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        new IterativeQuickSort().quickSort(arr, true);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
package org.algorithms.test.copilot.search;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

@Component
public class Search {

    @TrackExecutionTime
    public int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Find middle element

            if (arr[mid] == target) // Target found
                return mid;

            if (arr[mid] < target) // Target is in the right half
                left = mid + 1;
            else // Target is in the left half
                right = mid - 1;
        }

        return -1; // Target not found
    }

    @TrackExecutionTime
    public int interpolationSearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            // Estimate the probable position
            int pos = low + (target - arr[low]) * (high - low) / (arr[high] - arr[low]);

            if (arr[pos] == target) return pos; // Found the element
            if (arr[pos] < target) low = pos + 1; // Move right
            else high = pos - 1; // Move left
        }

        return -1; // Not found
    }

    @TrackExecutionTime
    public int ternarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            // Divide the range into three parts
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            if (arr[mid1] == target) return mid1; // Found at mid1
            if (arr[mid2] == target) return mid2; // Found at mid2

            if (target < arr[mid1]) right = mid1 - 1;  // Search in first third
            else if (target > arr[mid2]) left = mid2 + 1; // Search in last third
            else { // Search in middle section
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }
        return -1; // Not found
    }

    @TrackExecutionTime
    public int nNarySearch(int[] arr, int target, int n) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            // Compute partition size
            int partitionSize = (right - left) / n;

            // Define multiple midpoints
            int[] mids = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                mids[i] = left + (i + 1) * partitionSize;
            }

            // Search within partitions
            for (int i = 0; i < n - 1; i++) {
                if (arr[mids[i]] == target) return mids[i]; // Found target
                if (target < arr[mids[i]]) {
                    right = mids[i] - 1; // Search in left partition
                    break;
                } else if (i == n - 2) {
                    left = mids[i] + 1; // Search in right partition
                }
            }
        }
        return -1; // Not found
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};
        int target = 10;
        int result = new Search().binarySearch(arr, target);
        System.out.println(result != -1 ? "Index: " + result : "Element not found");

        arr = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        target = 15;
        result = new Search().interpolationSearch(arr, target);
        System.out.println(result != -1 ? "Index: " + result : "Element not found");

        arr = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        target = 9;
        result = new Search().ternarySearch(arr, target);
        System.out.println(result != -1 ? "Index: " + result : "Element not found");

        arr = new int[]{1, 5, 10, 15, 20, 25, 30, 35, 40, 50, 60, 70, 80, 90, 100};
        target = 40;
        int n = 5; // Example: Splitting into 5 partitions
        result = new Search().nNarySearch(arr, target, n);
        System.out.println(result != -1 ? "Index: " + result : "Element not found");
    }
}
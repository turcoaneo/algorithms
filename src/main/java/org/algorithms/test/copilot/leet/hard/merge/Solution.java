package org.algorithms.test.copilot.leet.hard.merge;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;

@Component("mergeKLists")
public class Solution {
    @TrackExecutionTime
    public ListNode mergeKLists(ListNode[] lists) {
        // Min-Heap (PriorityQueue) to keep track of smallest elements
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        if (lists.length == 0) {
            return null;
        }

        // Add all non-null lists to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll(); // Extract smallest node
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next); // Add the next element from the same list
            }
        }

        return dummy.next;
    }
}
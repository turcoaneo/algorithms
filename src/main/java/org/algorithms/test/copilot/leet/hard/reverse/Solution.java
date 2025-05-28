package org.algorithms.test.copilot.leet.hard.reverse;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component("reverseKGroup")
public class Solution {
    @TrackExecutionTime
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head; // Edge cases

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode groupStart = prevGroupEnd.next;
            ListNode groupEnd = prevGroupEnd;

            // Check if we have k nodes to reverse
            for (int i = 0; i < k; i++) {
                groupEnd = groupEnd.next;
                if (groupEnd == null) return dummy.next; // Not enough nodes left
            }

            ListNode nextGroupStart = groupEnd.next;

            // Reverse k nodes
            ListNode prev = nextGroupStart, curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect reversed group to previous part
            prevGroupEnd.next = groupEnd;
            prevGroupEnd = groupStart;
        }
    }

    public static class Main {
        public static void main(String[] args) {
            Solution solution = new Solution();

            // Test case 1: k = 2
            ListNode head1 = createLinkedList(new int[]{1, 2, 3, 4, 5});
            System.out.print("Input: ");
            printLinkedList(head1);
            ListNode result1 = solution.reverseKGroup(head1, 2);
            System.out.print("Output: ");
            printLinkedList(result1);

            // Test case 2: k = 3
            ListNode head2 = createLinkedList(new int[]{1, 2, 3, 4, 5});
            System.out.print("Input: ");
            printLinkedList(head2);
            ListNode result2 = solution.reverseKGroup(head2, 3);
            System.out.print("Output: ");
            printLinkedList(result2);

            // Test case 3: k = 1 (should remain the same)
            ListNode head3 = createLinkedList(new int[]{1, 2, 3, 4, 5});
            System.out.print("Input: ");
            printLinkedList(head3);
            ListNode result3 = solution.reverseKGroup(head3, 1);
            System.out.print("Output: ");
            printLinkedList(result3);
        }

        // Helper method to create a linked list from an array
        private static ListNode createLinkedList(int[] values) {
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            for (int value : values) {
                current.next = new ListNode(value);
                current = current.next;
            }
            return dummy.next;
        }

        // Helper method to print a linked list
        private static void printLinkedList(ListNode head) {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
}
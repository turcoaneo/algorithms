package org.algorithms.test.copilot.leetcode.hard.reverse;

import org.algorithms.test.copilot.leet.hard.reverse.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class LeetcodeReverseKGroupTest {
    @Autowired
    org.algorithms.test.copilot.leet.hard.reverse.Solution solution;
    static Stream<Arguments> testCases() {
        return Stream.of(
            Arguments.of(createLinkedList(new int[]{1, 2, 3, 4, 5}), 2, createLinkedList(new int[]{2, 1, 4, 3, 5})),
            Arguments.of(createLinkedList(new int[]{1, 2, 3, 4, 5}), 3, createLinkedList(new int[]{3, 2, 1, 4, 5})),
            Arguments.of(createLinkedList(new int[]{1, 2, 3, 4, 5}), 1, createLinkedList(new int[]{1, 2, 3, 4, 5})), // No change expected
            Arguments.of(createLinkedList(new int[]{1, 2}), 2, createLinkedList(new int[]{2, 1}))
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testReverseKGroup(ListNode input, int k, ListNode expected) {
        ListNode actual = solution.reverseKGroup(input, k);

        while (expected != null && actual != null) {
            Assertions.assertEquals(expected.val, actual.val);
            expected = expected.next;
            actual = actual.next;
        }
        
        Assertions.assertNull(expected);
        Assertions.assertNull(actual);
    }

    private static ListNode createLinkedList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }
}
package org.algorithms.test.copilot.leetcode.hard.merge;

import org.algorithms.test.copilot.leet.hard.merge.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeMergeTests {
    @Autowired
    org.algorithms.test.copilot.leet.hard.merge.Solution solution;

    static Stream<Arguments> getMedianArgs() {
        ListNode listNode1 = getListNode(new int[]{1, 4, 5});
        ListNode listNode2 = getListNode(new int[]{1, 3, 4});
        ListNode listNode3 = getListNode(new int[]{2, 6});

        //[1,1,2,3,4,4,5,6]
        ListNode listNodeResult = getListNodeResult();


        return Stream.of(
                Arguments.of(new ListNode[]{listNode1, listNode2, listNode3}, listNodeResult),
                Arguments.of(new ListNode[]{}, null),
                Arguments.of(new ListNode[]{new ListNode()}, new ListNode())
        );
    }

    private static ListNode getListNodeResult() {
        ListNode listNodeResult8 = new ListNode(6);
        ListNode listNodeResult7 = new ListNode(5, listNodeResult8);
        ListNode listNodeResult6 = new ListNode(4, listNodeResult7);
        ListNode listNodeResult5 = new ListNode(4, listNodeResult6);
        ListNode listNodeResult4 = new ListNode(3, listNodeResult5);
        ListNode listNodeResult3 = new ListNode(2, listNodeResult4);
        ListNode listNodeResult2 = new ListNode(1, listNodeResult3);
        return new ListNode(1, listNodeResult2);
    }

    private static ListNode getListNode(int[] input) {
        int length = input.length;
        ListNode last = new ListNode(input[length - 1]);
        int j = length - 2;
        while (j >= 0) {
            last = new ListNode(input[j], last);
            j--;
        }
        return last;
    }

    @ParameterizedTest
    @MethodSource("getMedianArgs")
    void testGetPerm(ListNode[] input, ListNode expected) {
        ListNode actual = solution.mergeKLists(input);
        System.out.println(actual);
        if (expected == null) {
            Assertions.assertNull(actual);
            return;
        }
        if (expected.next == null) {
            Assertions.assertNull(actual.next);
            return;
        }
        while (expected.next != null) {
            Assertions.assertEquals(expected.val, actual.val);
            expected = expected.next;
            actual = actual.next;
        }
    }
}
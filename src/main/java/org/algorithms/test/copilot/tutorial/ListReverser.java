package org.algorithms.test.copilot.tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.random.RandomGenerator;

public class ListReverser {
    public static void reverseListIndexing(List<Integer> list) {
        // Your code here
        int size = list.size();
        int middle = size / 2;
        for (int i = 0; i < middle; i++) {
            int lastIndex = size - 1 - i;
            Integer last = list.get(lastIndex);
            Integer first = list.get(i);
            list.set(i, last);
            list.set(lastIndex, first);
        }
    }

    public static void reverseList(List<Integer> list) {
        ListIterator<Integer> front = list.listIterator();
        ListIterator<Integer> back = list.listIterator(list.size());

        int mid = list.size() / 2;
        for (int i = 0; i < mid; i++) {
            Integer frontVal = front.next();
            Integer backVal = back.previous();

            front.set(backVal);
            back.set(frontVal);
        }
    }

    public static void main(String[] args) {
        List<Integer> list;
        if (RandomGenerator.getDefault().nextBoolean()) {
            list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        } else {
            list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        }
        if (list instanceof RandomAccess) {
            System.out.println(list); // should print [4, 3, 2, 1]
            reverseListIndexing(list);
        } else {
            reverseList(list);
            System.out.println(list); // should print [5, 4, 3, 2, 1]
        }
    }
}
package org.algorithms.test.copilot.fundamentals;

import org.algorithms.test.copilot.trees.binary.AVLTree;
import org.algorithms.test.copilot.trees.binary.BST;
import org.algorithms.test.copilot.trees.binary.RBTree;
import org.algorithms.test.copilot.trees.RandomNumberGenerator;
import org.algorithms.test.copilot.trees.btrees.BTree;
import org.algorithms.test.copilot.trees.btrees.BTreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class TreesTests {
    @Autowired
    BST binarySearchTree;
    @Autowired
    AVLTree avlTree;
    @Autowired
    RBTree rbTree;
    @Autowired
    RBTree rbTree1;
    @Autowired
    BTree bTree;

    @Test
    void testAutoBalancedTree() {
        int[] keys = {10, 20, 5, 6, 30, 40, 4, 3, 7, 8,};

        for (int key : keys) {
            bTree.insertIterative(key);
        }
        BTreeNode root = bTree.getRoot();
        Assertions.assertArrayEquals(root.getChildren()[0].getKeys(), new int[]{3, 4, 0});
        Assertions.assertArrayEquals(root.getChildren()[1].getKeys(), new int[]{6, 7, 8});
        Assertions.assertArrayEquals(root.getChildren()[2].getKeys(), new int[]{20, 30, 40});

        bTree.insertIterative(15);
        Assertions.assertArrayEquals(root.getKeys(), new int[]{5, 10, 30});
        Assertions.assertArrayEquals(root.getChildren()[2].getKeys(), new int[]{15, 20, 0});
        Assertions.assertArrayEquals(root.getChildren()[3].getKeys(), new int[]{40, 0, 0});
    }

    @Test
    void testBinaryTree() {
        int length = 30_000_000;
        List<Integer> values = RandomNumberGenerator.generateRandomList(length);
        BST.BinaryNode root = new BST.BinaryNode(0);
        BST.BinaryNode binaryNode = binarySearchTree.insertListRecursive(root, values);
        BST.BinaryNode root1 = new BST.BinaryNode(0);
        BST.BinaryNode binaryNode1 = binarySearchTree.insertListIterative(root1, values);
        Assertions.assertEquals(binaryNode.value, binaryNode1.value);

        int index = length / 2;
        Integer key = values.get(index);
        binaryNode = binarySearchTree.searchRecursive(root, key);
        binaryNode1 = binarySearchTree.searchIterative(root, key);
        Assertions.assertEquals(binaryNode.value, binaryNode1.value);

        binaryNode = binarySearchTree.deleteRecursive(root, key);
        binaryNode1 = binarySearchTree.deleteIterative(root, key);
        Assertions.assertEquals(binaryNode.value, binaryNode1.value);
    }

    @Test
    void testAVLTree() {
        int length = 10_000_000;
        List<Integer> values = RandomNumberGenerator.generateRandomList(length);
        AVLTree.Node root = new AVLTree.Node(0);
        AVLTree.Node avlNode = avlTree.insertListRecursive(root, values);
        AVLTree.Node root1 = new AVLTree.Node(0);
        AVLTree.Node avlNode1 = avlTree.insertListIterative(root1, values);
        Assertions.assertNotEquals(avlNode.value, avlNode1.value);
        Assertions.assertNotEquals(avlNode.height, avlNode1.height);

        int index = length / 2;
        Integer key = values.get(index);
        avlNode = avlTree.searchRecursive(root, key);
        avlNode1 = avlTree.searchIterative(root, key);
        Assertions.assertEquals(avlNode.value, avlNode1.value);
        Assertions.assertEquals(avlNode.height, avlNode1.height);

        avlNode = avlTree.deleteRecursive(root, key);
        avlNode1 = avlTree.deleteIterative(root, key);
        Assertions.assertEquals(avlNode.value, avlNode1.value);
        Assertions.assertEquals(avlNode.height, avlNode1.height);
    }

    @Test
    void testRBTree() {
        int length = 30_000_000;
        List<Integer> values = RandomNumberGenerator.generateRandomList(length);
        RBTree.Node rbNode = rbTree.insertListRecursive(values);
        RBTree.Node rbNode1 = rbTree1.insertListIterative(values);
        Assertions.assertEquals(rbNode.value, rbNode1.value);

        System.out.println("Recursive tree balancing");
        RBTree.Node node = rbTree.balanceAfterInsert(rbTree.getRoot());
        System.out.println("Iterative tree balancing");
        RBTree.Node node1 = rbTree1.balanceAfterInsert(rbTree.getRoot());
        Assertions.assertEquals(node.value, node1.value);

        int index = length / 2;
        Integer key = values.get(index);
        RBTree.Node found = rbTree.searchRecursive(rbNode, key);
        RBTree.Node found1 = rbTree.searchIterative(rbNode1, key);
        Assertions.assertEquals(found.value, found1.value);

        found = rbTree.deleteRecursive(key);
        found1 = rbTree.deleteIterative(key);
        Assertions.assertEquals(found.value, found1.value);
    }
}
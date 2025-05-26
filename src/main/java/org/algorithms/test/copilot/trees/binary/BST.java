package org.algorithms.test.copilot.trees.binary;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("binarySearchTree")
public class BST {
    public static class BinaryNode {
        public int value;
        BinaryNode left, right;

        public BinaryNode(int value) {
            this.value = value;
            this.left = this.right = null;
        }
    }

    @TrackExecutionTime
    public BinaryNode searchRecursive(BinaryNode root, int key) {
        if (root == null || root.value == key) return root;
        return key < root.value ? searchRecursive(root.left, key) : searchRecursive(root.right, key);
    }

    @TrackExecutionTime
    public BinaryNode insertListRecursive(BinaryNode root, List<Integer> values) {
        values.forEach(value -> insertRecursive(root, value));
        return root;
    }

    public BinaryNode insertRecursive(BinaryNode root, int key) {
        if (root == null) return new BinaryNode(key);
        if (key < root.value) root.left = insertRecursive(root.left, key);
        else if (key > root.value) root.right = insertRecursive(root.right, key);
        return root;
    }

    @TrackExecutionTime
    public BinaryNode insertListIterative(BinaryNode root, List<Integer> values) {
        values.forEach(value -> insertIterative(root, value));
        return root;
    }

    @TrackExecutionTime
    public BinaryNode searchIterative(BinaryNode root, int key) {
        while (root != null && root.value != key) {
            root = key < root.value ? root.left : root.right;
        }
        return root;
    }

    public BinaryNode insertIterative(BinaryNode root, int key) {
        BinaryNode newBinaryNode = new BinaryNode(key);
        if (root == null) return newBinaryNode;

        BinaryNode parent = null, current = root;
        while (current != null) {
            parent = current;
            current = key < current.value ? current.left : current.right;
        }

        if (key < parent.value) parent.left = newBinaryNode;
        else parent.right = newBinaryNode;

        return root;
    }

    @TrackExecutionTime
    public BinaryNode deleteRecursive(BinaryNode root, int key) {
        if (root == null) return null;

        if (key < root.value) {
            root.left = deleteRecursive(root.left, key);
        } else if (key > root.value) {
            root.right = deleteRecursive(root.right, key);
        } else {
            // Case 1: No child
            if (root.left == null && root.right == null) return null;

            // Case 2: One child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: Two children - Find inorder successor (smallest in right subtree)
            BinaryNode successor = findMin(root.right);
            root.value = successor.value;
            root.right = deleteRecursive(root.right, successor.value); // Remove successor
        }
        return root;
    }

    private BinaryNode findMin(BinaryNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    @TrackExecutionTime
    public BinaryNode deleteIterative(BinaryNode root, int key) {
        BinaryNode parent = null, current = root;

        // Step 1: Find the node to delete
        while (current != null && current.value != key) {
            parent = current;
            current = key < current.value ? current.left : current.right;
        }

        if (current == null) return root; // Key not found

        // Step 2: Handle deletion cases
        if (current.left == null || current.right == null) {
            // Case 1 & 2: No child or one child
            BinaryNode replacement = (current.left != null) ? current.left : current.right;

            if (parent == null) return replacement; // Deleting root node
            if (parent.left == current) parent.left = replacement;
            else parent.right = replacement;
        } else {
            // Case 3: Two children - Find inorder successor
            BinaryNode successorParent = current;
            BinaryNode successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.value = successor.value; // Copy successor value

            // Delete successor (smallest node in right subtree)
            if (successorParent.left == successor) successorParent.left = successor.right;
            else successorParent.right = successor.right;
        }

        return root;
    }

}
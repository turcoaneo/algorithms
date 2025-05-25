package org.algorithms.test.copilot.trees.binary;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@Component
public class AVLTree {
    public static class Node {
        public int value, height;
        Node left, right;

        public Node(int value) {
            this.value = value;
            this.height = 1; // Initial height
        }
    }

    @TrackExecutionTime
    public Node insertListIterative(Node root, List<Integer> values) {
        for (int value : values) {
            root = insertIterative(root, value); // Use iterative insertion for each element
        }
        return root;
    }

    public Node insertIterative(Node root, int key) {
        if (root == null) return new Node(key);

        Node current = root, parent = null;
        Stack<Node> path = new Stack<>(); // Track path for rebalancing

        while (current != null) {
            parent = current;
            path.push(current); // Store visited nodes for balancing
            current = key < current.value ? current.left : current.right;
        }

        Node newNode = new Node(key);
        if (key < parent.value) parent.left = newNode;
        else parent.right = newNode;

        // Rebalance tree
        while (!path.isEmpty()) {
            Node node = path.pop();
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
            root = balance(node); // Adjust rotations iteratively
        }

        return root;
    }

    @TrackExecutionTime
    public Node searchIterative(Node root, int key) {
        while (root != null && root.value != key) {
            root = key < root.value ? root.left : root.right;
        }
        return root; // Returns null if key not found
    }

    @TrackExecutionTime
    public Node insertListRecursive(Node root, List<Integer> values) {
        for (Integer value : values) {
            root = insertRecursive(root, value); // Insert each value
        }
        return root;
    }

    @TrackExecutionTime
    public Node deleteIterative(Node root, int key) {
        Node parent = null, current = root;
        Stack<Node> path = new Stack<>(); // Track path for rebalancing

        // Step 1: Find the node to delete
        while (current != null && current.value != key) {
            parent = current;
            path.push(current);
            current = key < current.value ? current.left : current.right;
        }

        if (current == null) return root; // Key not found

        // Step 2: Handle deletion cases
        if (current.left == null || current.right == null) {
            Node replacement = (current.left != null) ? current.left : current.right;

            if (parent == null) return replacement; // Root node deletion case
            if (parent.left == current) parent.left = replacement;
            else parent.right = replacement;
        } else {
            // Case 3: Two children → Find inorder successor
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.value = successor.value; // Copy successor value

            // Delete successor node
            if (successorParent.left == successor) successorParent.left = successor.right;
            else successorParent.right = successor.right;
        }

        // Step 3: Rebalance AVL tree after deletion
        while (!path.isEmpty()) {
            Node node = path.pop();
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
            root = balance(node);
        }

        return root;
    }

    public Node insertRecursive(Node root, int key) {
        if (root == null) return new Node(key);

        if (key < root.value) root.left = insertRecursive(root.left, key);
        else if (key > root.value) root.right = insertRecursive(root.right, key);
        else return root; // No duplicates allowed

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        return balance(root); // Ensure tree remains balanced
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    @TrackExecutionTime
    public Node searchRecursive(Node root, int key) {
        if (root == null || root.value == key) return root;
        return key < root.value ? searchRecursive(root.left, key) : searchRecursive(root.right, key);
    }

    @TrackExecutionTime
    public Node deleteRecursive(Node root, int key) {
        if (root == null) return null;

        if (key < root.value) root.left = deleteRecursive(root.left, key);
        else if (key > root.value) root.right = deleteRecursive(root.right, key);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node successor = findMin(root.right);
            root.value = successor.value;
            root.right = deleteRecursive(root.right, successor.value);
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        return balance(root); // Ensure AVL properties remain valid!
    }

    private Node balance(Node root) {
        int balanceFactor = getHeight(root.left) - getHeight(root.right);

        // Left-heavy case
        if (balanceFactor > 1) {
            if (getHeight(root.left.left) >= getHeight(root.left.right)) {
                return rotateRight(root); // Right rotation
            } else {
                root.left = rotateLeft(root.left);
                return rotateRight(root); // Left-Right rotation
            }
        }

        // Right-heavy case
        if (balanceFactor < -1) {
            if (getHeight(root.right.right) >= getHeight(root.right.left)) {
                return rotateLeft(root); // Left rotation
            } else {
                root.right = rotateRight(root.right);
                return rotateLeft(root); // Right-Left rotation
            }
        }

        return root;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

        return y;
    }

    private Node findMin(Node root) {
        while (root.left != null) root = root.left;
        return root;
    }
}
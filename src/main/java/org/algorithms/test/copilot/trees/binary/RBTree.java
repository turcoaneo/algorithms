package org.algorithms.test.copilot.trees.binary;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RBTree {
    public static class Node {
        public int value;
        boolean isRed; // True for RED, false for BLACK
        Node left, right, parent;

        public Node(int value) {
            this.value = value;
            this.isRed = true; // New nodes start as RED
        }
    }

    public Node getRoot() {
        return root;
    }

    private Node root;

    @TrackExecutionTime
    public Node insertListIterative(List<Integer> values) {
        for (int value : values) {
            insert(value); // Uses iterative Red-Black insertion
        }
        return root;
    }

    @TrackExecutionTime
    public Node insertListRecursive(List<Integer> values) {
        for (int value : values) {
            root = insertRecursive(root, root, value); // Uses iterative Red-Black insertion
        }
        return root;
    }

    @TrackExecutionTime
    public Node searchRecursive(Node root, int key) {
        if (root == null || root.value == key) return root;
        return key < root.value ? searchRecursive(root.left, key) : searchRecursive(root.right, key);
    }

    @TrackExecutionTime
    public Node searchIterative(Node root, int key) {
        while (root != null && root.value != key) {
            root = key < root.value ? root.left : root.right;
        }
        return root;
    }

    public void insert(int key) {
        Node newNode = new Node(key);
        root = insertBST(root, newNode); // Normal BST insertion
    }

    @TrackExecutionTime
    public void insertAndBalance(int key) {
        Node newNode = new Node(key);
        root = insertBST(root, newNode); // Normal BST insertion
        balanceAfterInsert(newNode); // Fix violations
    }

    @TrackExecutionTime
    public Node balanceAfterInsert(Node node) {
        while (node != root && node.parent.isRed) {
            Node parent = node.parent;
            Node grandparent = parent.parent;

            // Determine uncle (sibling of parent)
            Node uncle = (grandparent != null && grandparent.left == parent) ? grandparent.right : grandparent.left;

            if (uncle != null && uncle.isRed) {
                // Case 1: Red uncle → Recolor parent, uncle & grandparent
                parent.isRed = false;
                uncle.isRed = false;
                grandparent.isRed = true;
                node = grandparent; // Move up and continue fixing
            } else {
                // Case 2 & 3: Uncle is black → Perform rotations
                if (parent == grandparent.left) {
                    if (node == parent.right) { // Case 2: Left-Right
                        node = parent;
                        rotateLeft(node);
                    }
                    // Case 3: Left-Left
                    parent.isRed = false;
                    grandparent.isRed = true;
                    rotateRight(grandparent);
                } else {
                    if (node == parent.left) { // Case 2: Right-Left
                        node = parent;
                        rotateRight(node);
                    }
                    // Case 3: Right-Right
                    parent.isRed = false;
                    grandparent.isRed = true;
                    rotateLeft(grandparent);
                }
            }
        }
        root.isRed = false; // Root must always be BLACK
        return root;
    }

    public Node insertRecursive(Node root, Node parent, int key) {
        if (root == null) {
            Node newNode = new Node(key);
            newNode.parent = parent;
            return newNode;
        }

        if (key < root.value) {
            root.left = insertRecursive(root.left, root, key);
        } else if (key > root.value) {
            root.right = insertRecursive(root.right, root, key);
        } else {
            return root; // No duplicates allowed
        }

//        return balanceAfterInsert(root); // Maintain Red-Black properties
        return root;
    }

    @TrackExecutionTime
    public Node deleteIterative(int key) {
        Node node = searchIterative(root, key);
        if (node == null) return null;

        Node replacement;
        boolean originalColor = node.isRed;

        if (node.left == null) {
            replacement = node.right;
            replace(node, node.right);
        } else if (node.right == null) {
            replacement = node.left;
            replace(node, node.left);
        } else {
            // Find inorder successor
            Node successor = findMin(node.right);
            originalColor = successor.isRed;
            replacement = successor.right;

            if (successor.parent == node) {
                if (replacement != null) replacement.parent = successor;
            } else {
                replace(successor, successor.right);
                successor.right = node.right;
                successor.right.parent = successor;
            }

            replace(node, successor);
            successor.left = node.left;
            successor.left.parent = successor;
            successor.isRed = node.isRed;
        }

        if (!originalColor && replacement != null) {
            balanceAfterDelete(replacement);
        }
        return node;
    }

    @TrackExecutionTime
    public Node deleteRecursive(int key) {
        Node node = searchIterative(root, key);
        if (node == null) return null;

        deleteRecursiveBST(root, key);
//        balanceAfterDelete(node); // Fix Red-Black violations
        return node;
    }

    private Node deleteRecursiveBST(Node node, int key) {
        if (node == null) return null;

        if (key < node.value) {
            node.left = deleteRecursiveBST(node.left, key);
        } else if (key > node.value) {
            node.right = deleteRecursiveBST(node.right, key);
        } else {
            // Case 1: No child
            if (node.left == null && node.right == null) return null;

            // Case 2: One child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Case 3: Two children - Find inorder successor
            Node successor = findMin(node.right);
            node.value = successor.value;
            node.right = deleteRecursiveBST(node.right, successor.value); // Remove successor
        }

//        return balanceAfterDelete(node); // Adjust only if necessary
        return node;
    }

    private Node findMin(Node root) {
        while (root.left != null) root = root.left;
        return root;
    }

    private void replace(Node oldNode, Node newNode) {
        if (oldNode.parent == null) {
            root = newNode; // If replacing root, update it
        } else if (oldNode == oldNode.parent.left) {
            oldNode.parent.left = newNode; // Update parent's left child
        } else {
            oldNode.parent.right = newNode; // Update parent's right child
        }

        if (newNode != null) {
            newNode.parent = oldNode.parent; // Maintain correct parent reference
        }
    }

    private void balanceAfterDelete(Node node) {
        while (node != root && !node.isRed) {
            Node sibling = (node.parent.left == node) ? node.parent.right : node.parent.left;

            if (sibling.isRed) {
                sibling.isRed = false;
                node.parent.isRed = true;
                if (node == node.parent.left) rotateLeft(node.parent);
                else rotateRight(node.parent);
                sibling = (node.parent.left == node) ? node.parent.right : node.parent.left;
            }

            if (!sibling.left.isRed && !sibling.right.isRed) {
                sibling.isRed = true;
                node = node.parent;
            } else {
                if (!sibling.right.isRed) {
                    sibling.left.isRed = false;
                    sibling.isRed = true;
                    rotateRight(sibling);
                    sibling = node.parent.right;
                }
                sibling.isRed = node.parent.isRed;
                node.parent.isRed = false;
                sibling.right.isRed = false;
                rotateLeft(node.parent);
                node = root;
            }
        }
        node.isRed = false;
    }

    private Node insertBST(Node root, Node newNode) {
        if (root == null) return newNode;

        if (newNode.value < root.value) {
            root.left = insertBST(root.left, newNode);
            root.left.parent = root;
        } else {
            root.right = insertBST(root.right, newNode);
            root.right.parent = root;
        }
        return root;
    }

    private void rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        if (temp.left != null) temp.left.parent = node;

        temp.parent = node.parent;
        if (node.parent == null) root = temp;
        else if (node == node.parent.left) node.parent.left = temp;
        else node.parent.right = temp;

        temp.left = node;
        node.parent = temp;
    }

    private void rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        if (temp.right != null) temp.right.parent = node;

        temp.parent = node.parent;
        if (node.parent == null) root = temp;
        else if (node == node.parent.right) node.parent.right = temp;
        else node.parent.left = temp;

        temp.right = node;
        node.parent = temp;
    }
}
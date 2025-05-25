package org.algorithms.test.copilot.trees.btrees;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class BTree {
    public BTreeNode getRoot() {
        return root;
    }

    private BTreeNode root;
    private final int degree;

    public BTree() {
        this.root = new BTreeNode(2, true);
        this.degree = 2;
    }

    public BTree(Integer degree) {
        this.root = new BTreeNode(degree, true);
        this.degree = degree;
    }

    public void printTree(BTreeNode node, int level) {
        if (node != null) {
            // Print keys in current node
            System.out.print("Level " + level + " | Keys: ");
            for (int i = 0; i < node.keys.length; i++) {
                int key = node.keys[i];
                if (key != 0) System.out.print(key + " ");
            }
            System.out.println();

            // Recursively print children
            if (!node.isLeaf) {
                for (int i = 0; i <= node.numKeys; i++) {
                    BTreeNode child = node.children[i];
                    printTree(child, level + 1);
                }
            }
        }
    }

    @TrackExecutionTime
    public void insertIterative(int key) {
        BTreeNode node = root;

        // If the root is full, split first
        int degreeLength = 2 * degree - 1;
        if (node.numKeys == degreeLength) {
            BTreeNode newRoot = new BTreeNode(degree, false);
            newRoot.children[0] = root;
            splitChild(newRoot, 0, root);
            root = newRoot;
        }

        BTreeNode currentNode = root; // Track traversal separately

        // Iteratively traverse down to find the correct insertion point
        while (!currentNode.isLeaf) {
            int i = currentNode.numKeys - 1;

            // Locate correct child
            while (i >= 0 && key < currentNode.keys[i]) {
                i--;
            }
            i++;

            // **Check if child has space before splitting**
            if (currentNode.children[i].numKeys < degreeLength) {
                // There's room—insert directly into the correct child
            } else {
                // No space—perform a split before proceeding
                splitChild(currentNode, i, currentNode.children[i]);
                if (key > currentNode.keys[i]) {
                    i++;
                }
            }

            currentNode = currentNode.children[i]; // Move deeper into the correct child
        }

        // Now insert into the leaf node
        int j = currentNode.numKeys - 1;
        while (j >= 0 && currentNode.keys[j] > key) {
            currentNode.keys[j + 1] = currentNode.keys[j]; // Shift keys to right
            j--;
        }

        currentNode.keys[j + 1] = key; // Insert key
        currentNode.numKeys++;
    }

    public void splitChild(BTreeNode parent, int index, BTreeNode child) {
        BTreeNode newNode = new BTreeNode(child.degree, child.isLeaf);
        newNode.numKeys = degree - 1;

        // Copy second half of keys into new node
        for (int j = 0; j < degree - 1; j++) {
            newNode.keys[j] = child.keys[j + degree];
            child.keys[j + degree] = 0; // reset moved key
        }

        // If not a leaf, move child pointers
        if (!child.isLeaf) {
            for (int j = 0; j < degree; j++) {
                newNode.children[j] = child.children[j + degree];
            }
        }

        child.numKeys = degree - 1;

        // Shift children to make room
        for (int j = parent.numKeys; j > index; j--) {
            parent.children[j + 1] = parent.children[j];
        }

        parent.children[index + 1] = newNode;

        // Shift parent keys and insert median key
        for (int j = parent.numKeys - 1; j >= index; j--) {
            parent.keys[j + 1] = parent.keys[j];
        }

        int promotedKey = child.keys[degree - 1];
        parent.keys[index] = promotedKey; // Promote middle key
        resetPromotedKey(parent, promotedKey);
        parent.numKeys++;
    }

    private static void resetPromotedKey(BTreeNode parent, int promotedKey) {
        Arrays.stream(parent.children).filter(Objects::nonNull).map(c -> c.keys).forEach(childKeys -> {
            for (int i = 0; i < childKeys.length; i++) {
                if (childKeys[i] == promotedKey) {
                    childKeys[i] = 0;
                }
            }
        });
    }

    public static void main(String[] args) {
        BTree tree = new BTree(2); // B-Tree with degree
//        int[] keys = {10, 20, 5, 6, 30, 40, 4, 3, 7, 8, 12};
        int[] keys = {10, 20, 5, 6, 30, 40, 4, 3, 7, 8,};

        for (int key : keys) {
            tree.insertIterative(key);
        }

//        tree.insertIterative(15);
        System.out.println("B-Tree Structure:");
        tree.printTree(tree.root, 0);
    }
}
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
            if (currentNode.children[i].numKeys >= degreeLength) {
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
            if (degree >= 0) System.arraycopy(child.children, degree, newNode.children, 0, degree);
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

    public boolean isFound(BTreeNode node, int key) {
        int i = 0;

        // Locate the correct position within the node
        while (i < node.numKeys && key > node.keys[i]) {
            i++;
        }

        // If key is found, return true
        if (i < node.numKeys && key == node.keys[i]) {
            return true;
        }

        // If we've reached a leaf and didn't find the key, return false
        if (node.isLeaf) {
            return false;
        }

        // Recur down the correct child
        return isFound(node.children[i], key);
    }

    public SearchResult search(BTreeNode parent, BTreeNode node, int key, int childIndex) {
        int i = 0;

        // Locate correct position within the node
        while (i < node.numKeys && key > node.keys[i]) {
            i++;
        }

        // If key is found, return full search details
        if (i < node.numKeys && key == node.keys[i]) {
            return new SearchResult(key, parent, childIndex, i, node.isLeaf); // Captures parent, child index, and key index
        }

        // If it's a leaf and not found, return failure
        if (node.isLeaf) {
            return null; // Key not found
        }

        // Recur down the correct child while tracking parent and child index
        return search(node, node.children[i], key, i);
    }

    public record SearchResult(int key, BTreeNode parent, int childIndex, int keyPosition, boolean isLeaf) {
        @Override
        public String toString() {
            int[] keys = null;
            if (parent != null) keys = parent.keys;
            return "SearchResult{ key=" + key + " parent=" + Arrays.toString(keys) + ", childIndex=" + childIndex +
                    ", keyPosition=" + keyPosition + ", is leaf=" + isLeaf + " }";
        }
    }

    @TrackExecutionTime
    public void naiveDeleteFromLeaf(BTreeNode parent, int index, int key) {
        BTreeNode leafNode = parent.children[index];

        // Step 1: Remove the key if the leaf still has enough keys
        for (int i = 0; i < leafNode.numKeys; i++) {
            if (leafNode.keys[i] == key) {
                for (int j = i; j < leafNode.numKeys - 1; j++) {
                    leafNode.keys[j] = leafNode.keys[j + 1]; // Shift keys left
                }
                leafNode.numKeys--;
                leafNode.keys[leafNode.numKeys] = 0;
            }
        }
        // Step 2 check if child is empty and downgrade parent key
        if (leafNode.numKeys == 0) {
            leafNode.numKeys++;
            int lowerBound = Integer.MIN_VALUE;
            int[] parentKeys = parent.getKeys();
            int upperBound = parentKeys[0];
            for (int i = 0; i < parentKeys.length - 2; i++) { // check parent key
                if (lowerBound < key && key < upperBound) {
                    for (int j = i; j < parentKeys.length - 1; j++) {
                        parentKeys[j] = parentKeys[j + 1];// shift parent keys
                    }
                    break;
                }
                lowerBound = parentKeys[i];
                upperBound = parentKeys[i + 1];
            }

            int i = index;
            for (; i < parent.getChildren().length - 2; i++) {
                parent.children[i] = parent.children[i + 1];//shifting children
            }
            parent.children[i] = null;/// remove last child no matter what
            parentKeys[parentKeys.length - 1] = 0;// remove last parent key no matter what
            parent.numKeys--;
            this.insertIterative(upperBound); // re-insert parent key
        }
    }

    public static void main(String[] args) {
        BTree tree = new BTree(2); // B-Tree with degree
        int[] keys = {10, 20, 5, 6, 30, 40, 7, 8};

        for (int key : keys) {
            tree.insertIterative(key);
        }

        System.out.println("B-Tree Structure:");
        tree.printTree(tree.root, 0);
        boolean isFound = tree.isFound(tree.root, 5);
        System.out.println("Found key: " + isFound);

        SearchResult result = tree.search(null, tree.root, 5, -1);
        if (result == null) return;
        System.out.println(result);

        if (result.isLeaf()) tree.naiveDeleteFromLeaf(result.parent(), result.childIndex(), result.key());
        System.out.println("B-Tree Structure:");
        tree.printTree(tree.root, 0);
    }
}
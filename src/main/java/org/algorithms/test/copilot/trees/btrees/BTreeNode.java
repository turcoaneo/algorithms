package org.algorithms.test.copilot.trees.btrees;

public class BTreeNode {
    int[] keys;
    int degree; // Minimum degree (defines branching factor)
    BTreeNode[] children;
    int numKeys;

    public int[] getKeys() {
        return keys;
    }

    public BTreeNode[] getChildren() {
        return children;
    }

    boolean isLeaf;

    public BTreeNode(int degree, boolean isLeaf) {
        this.degree = degree;
        this.isLeaf = isLeaf;
        this.keys = new int[2 * degree - 1]; // Max keys per node
        this.children = new BTreeNode[2 * degree]; // Max children per node
        this.numKeys = 0;
    }
}
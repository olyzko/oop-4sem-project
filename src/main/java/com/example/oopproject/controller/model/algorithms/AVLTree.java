package com.example.oopproject.controller.model.algorithms;

import static java.lang.Math.max;

public class AVLTree extends BinaryTree {

    public AVLTree(String str) {
        int[] arr = arrayFromString(str);
        root = arrayToBST(arr, 0, arr.length - 1);
    }

    void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(x);
        updateHeight(x);
        return x;
    }

    public void insert (int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null)
            return new Node(key);
        if (key < node.data)
            node.left = insertRec(node.left, key);
        else if (key > node.data)
            node.right = insertRec(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalance(node);
        if (balanceFactor > 1) {
            if (key < node.left.data) {
                return rotateRight(node);
            } else if (key > node.left.data) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if (balanceFactor < -1) {
            if (key > node.right.data) {
                return rotateLeft(node);
            } else if (key < node.right.data) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        return node;
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null)
            return root;
        if (key < root.data)
            root.left = deleteRec(root.left, key);
        else if (key > root.data)
            root.right = deleteRec(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValue(root.right);
                root.data = temp.data;
                root.right = deleteRec(root.right, temp.data);
            }
        }
        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalance(root);
        if (balanceFactor > 1) {
            if (getBalance(root.left) >= 0) {
                return rotateRight(root);
            } else {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalance(root.right) <= 0) {
                return rotateLeft(root);
            } else {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }
        return root;
    }
}

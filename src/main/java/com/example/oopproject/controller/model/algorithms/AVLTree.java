package com.example.oopproject.controller.model.algorithms;

import java.util.ArrayList;

public class AVLTree extends BinaryTree {

    public AVLTree(String str) {
        int[] arr = arrayFromString(str);
        root = arrayToBST(arr, 0, arr.length - 1);
    }

    void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    int height(Node n) {
        return n == null ? -1 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    public void insert (int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else if (node.data > key) {
            node.left = insertRec(node.left, key);
        } else if (node.data < key) {
            node.right = insertRec(node.right, key);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node node, int key) {
        if (node == null) {
            return null;
        } else if (node.data > key) {
            node.left = deleteRec(node.left, key);
        } else if (node.data < key) {
            node.right = deleteRec(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                int mostLeftChild = minValue(node.right);
                node.right = deleteRec(node.right, mostLeftChild);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }
}

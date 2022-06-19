package com.example.oopproject.controller.model.algorithms;

import java.util.Arrays;

public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree(String str) {
        int[] arr = arrayFromString(str);
        root = arrayToBST(arr, 0, arr.length - 1);
    }

    public BinarySearchTree() {}

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key)  {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.data) {
            System.out.print(root.left);
            root.left = insertRec(root.left, key);
        }
        else if (key > root.data) {
            System.out.print(root.right);
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null)
            return null;

        if (key < root.data)
            root.left = deleteRec(root.left, key);
        else if (key > root.data)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            Node min = minValue(root.right);
            root.data = min.data;
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }
}

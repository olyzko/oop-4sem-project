package com.example.oopproject.controller.model.algorithms;

class Node {
    int data;
    Node left, right;

    Node(int d) {
        data = d;
        left = right = null;
    }
}

public class BinaryTree {
    static Node root;

    public Node arrayToBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        InsertionSort.sort(arr);
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);

        /* Recursively construct the left subtree and make it
         left child of root */
        node.left = arrayToBST(arr, start, mid - 1);
        node.right = arrayToBST(arr, mid + 1, end);

        return node;
    }

    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        preOrder(node.left);
        preOrder(node.right);
        System.out.print(node.data + " ");
    }

    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        preOrder(node.left);
        System.out.print(node.data + " ");
        preOrder(node.right);
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key)  {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.data)
            root.left = insertRec(root.left, key);
        else if (key > root.data)
            root.right = insertRec(root.right, key);
        return root;
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
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root)
    {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    public static int[] arrayFromString (String str) {
        String[] string = str.replaceAll("\\[", "")
                .replaceAll("]", "")
                .split(",");

        int[] arr = new int[string.length];
        try {
            for (int i = 0; i < string.length; i++)
                arr[i] = Integer.parseInt(string[i]);
        }
        catch (Exception e) {
            // show warning
            return new int[]{0};
        }
        return arr;
    }
}

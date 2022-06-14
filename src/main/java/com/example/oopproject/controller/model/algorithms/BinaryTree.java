package com.example.oopproject.controller.model.algorithms;

public class BinaryTree {
    public Node root;

    public BinaryTree(int[] arr, int start, int end) {
        arrayToBST(arr, start, end);
    }

    public BinaryTree(String str) {
        int[] arr = arrayFromString(str);
        root = arrayToBST(arr, 0, arr.length - 1);
    }

    public Node arrayToBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        InsertionSort.sort(arr);
        for (int i=0; i<arr.length-1; i++)
            if (arr[i] == arr[i+1] || arr[i] < 1 || arr[i] > 1000) {
                return null;
            }
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);

        node.left = arrayToBST(arr, start, mid - 1);
        node.right = arrayToBST(arr, mid + 1, end);

        return node;
    }

    private boolean search(Node root, int e){
        if(root == null)
            return false;
        else if(root.data == e)
            return true;
        else{
            if(root.data < e)
                return search(root.right, e);
            else
                return search(root.left, e);
        }
    }

    public boolean search(int e) {
        return search(root, e);
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

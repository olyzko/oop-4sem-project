package com.example.oopproject.controller.model.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

abstract class BinaryTree {
    public Node root;

    private boolean search(Node root, int key){
        if(root == null)
            return false;
        else if(root.data == key)
            return true;
        else{
            if(root.data < key)
                return search(root.right, key);
            else
                return search(root.left, key);
        }
    }

    public boolean search(int key) {
        return search(root, key);
    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    protected Node minValue(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
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

    public static Node arrayToBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        Arrays.sort(arr);
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

    public ArrayList<Node> path(int key){
        ArrayList<Node> list = new ArrayList<>();
        Node current = root;
        while(current != null){
            list.add(current);
            if(key < current.data)
                current = current.left;
            else if(key > current.data)
                current = current.right;
            else
                break;
        }
        return list;
    }

    protected abstract void insert(int key);
}

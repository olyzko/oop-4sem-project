package com.example.oopproject.controller.model.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public abstract class BinaryTree {
    public Node root;

    public BinaryTree() {}

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

    public ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Node> stack = new Stack<Node>();
        while(!stack.isEmpty() || root!=null) {
            if(root!=null) {
                stack.push(root);
                root=root.left;
            }
            else {
                root = (Node) stack.pop();
                list.add(root.data);
                root = root.right;
            }
        }
        return list;
    }

    public ArrayList<Integer> postOrder(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        Node temp = root;
        HashSet<Node> visited = new HashSet<>();
        while ((temp != null  && !visited.contains(temp))) {
            if (temp.left != null && !visited.contains(temp.left))
                temp = temp.left;
            else if (temp.right != null && !visited.contains(temp.right))
                temp = temp.right;
            else {
                list.add(temp.data);
                visited.add(temp);
                temp = root;
            }
        }

        return list;
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

    public ArrayList<Integer> path(int key){
        ArrayList<Integer> list = new ArrayList<>();
        Node current = root;
        while(current != null){
            list.add(current.data);
            if(key < current.data)
                current = current.left;
            else if(key > current.data)
                current = current.right;
            else
                break;
        }
        return list;
    }

    public abstract void insert(int key);

    public abstract void delete(int key);
}

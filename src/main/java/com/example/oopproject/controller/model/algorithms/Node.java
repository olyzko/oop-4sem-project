package com.example.oopproject.controller.model.algorithms;

public class Node {
    public int data;
    public Node left, right;
    public int height = 0;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}

package com.example.oopproject.controller.model.algorithms;

public class BinaryTreeFactory {
    public static BinaryTree getTree (String type, String input) {
        if (type.equals("Binary Search Tree"))
            return new BinarySearchTree(input);
        else if (type.equals("AVL Tree"))
            return new AVLTree(input);

        return null;
    }

}

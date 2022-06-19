package com.example.oopproject.controller.model.algorithms;

public class BinaryTreeFactory {
    public BinaryTree getTree (String type, String input) {
        if (type.equalsIgnoreCase("Binary Search Tree"))
            return new BinarySearchTree(input);
        else if (type.equalsIgnoreCase("AVL Tree"))
            return new AVLTree(input);

        return new BinarySearchTree();
    }

}

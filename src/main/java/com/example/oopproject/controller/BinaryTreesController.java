package com.example.oopproject.controller;

import com.example.oopproject.controller.model.algorithms.BinaryTree;
import com.example.oopproject.ui.switch_handler.View;
import com.example.oopproject.ui.switch_handler.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class BinaryTreesController {

    @FXML
    public Button addButton;

    @FXML
    public Button deleteButton;

    @FXML
    public TextField nodesAdd;

    @FXML
    public TextField nodesDelete;

    public void onMainButton() {
        ViewSwitcher.switchTo(View.MAIN);
    }

    public void AddButtonClicked() {
        if (!nodesDelete.isVisible()) {
            String str = nodesAdd.getText();
            BinaryTree bst = new BinaryTree(str);
            if (bst.root == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setContentText("Wrong input");
                alert.show();
                nodesAdd.clear();
            }
            bst.preOrder(bst.root);
            nodesAdd.setPromptText("Enter the new node");
        }


        nodesDelete.setVisible(true);
        nodesDelete.setManaged(true);
        deleteButton.setVisible(true);
        deleteButton.setManaged(true);
        nodesAdd.clear();
    }


}

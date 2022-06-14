package com.example.oopproject.controller;

import com.example.oopproject.controller.model.algorithms.BinaryTree;
import com.example.oopproject.controller.model.algorithms.Node;
import com.example.oopproject.ui.switch_handler.View;
import com.example.oopproject.ui.switch_handler.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BinaryTreesController {

    @FXML
    public Button addButton;

    @FXML
    public Button deleteButton;

    @FXML
    public TextField nodesAdd;

    @FXML
    public TextField nodesDelete;
    public AnchorPane treePane;

    BinaryTree bst;
    private final double radius = 15;
    private final double vGap = 50;

    public void onMainButton() {
        ViewSwitcher.switchTo(View.MAIN);
    }

    public void AddButtonClicked() {
        if (!nodesDelete.isVisible()) {
            String str = nodesAdd.getText();
            bst = new BinaryTree(str);
            if (bst.root == null) {
                showAlert("Wrong input");
                nodesAdd.clear();
            }

            bst.preOrder(bst.root);
            nodesAdd.setPromptText("Enter the new node (1-1000)");
        }
        else {
            String str = nodesAdd.getText();
            try{
                int number = Integer.parseInt(str);
                if (!bst.search(number))
                    bst.insert(number);
                else {
                    showAlert("This node is already present");
                    nodesAdd.clear();
                }
                bst.preOrder(bst.root);

            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }
        }

        nodesDelete.setVisible(true);
        nodesDelete.setManaged(true);
        deleteButton.setVisible(true);
        deleteButton.setManaged(true);
        nodesAdd.clear();
        displayTree();
    }


    public void DeleteButtonClicked(ActionEvent actionEvent) {
        String str = nodesDelete.getText();
        try{
            int number = Integer.parseInt(str);
            if (bst.search(number))
                 bst.delete(number);
            else
                showAlert("This node isn't in the tree");
            displayTree();
            bst.preOrder(bst.root);
            nodesDelete.clear();
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    public void displayTree() {
        treePane.getChildren().clear();
        if(bst.root != null){
            displayTree(bst.root, treePane.getWidth() / 2, this.vGap, treePane.getWidth() / 4, Color.MEDIUMPURPLE);
        }
    }

    protected void displayTree(Node root, double x, double y, double hGap, Color color){
        if(root.left != null){
            treePane.getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.left, x - hGap, y + vGap, hGap / 2,color);
        }

        if (root.right != null){
            treePane.getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.right, x + hGap, y + vGap, hGap / 2, color);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        treePane.getChildren().addAll(circle, new Text(x - 4, y + 4, root.data + ""));
    }

    public void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setContentText(text);
        alert.show();
    }
}

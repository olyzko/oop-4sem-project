package com.example.oopproject.controller;

import com.example.oopproject.controller.model.algorithms.*;
import com.example.oopproject.ui.switch_handler.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BinaryTreesController implements Initializable {

    @FXML
    public Button addButton;

    @FXML
    public Button deleteButton;

    @FXML
    public TextField nodesAdd;

    @FXML
    public TextField nodesDelete;
    public AnchorPane treePane;
    public Button clearButton;
    public ChoiceBox choiceBox;

    private BinaryTreeFactory factory;
    private BinaryTree tree;

    private final double radius = 15;
    private final double vGap = 50;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        factory = new BinaryTreeFactory();
        configureChoiceBox();
    }

    public void onMainButton() {
        ViewSwitcher.switchTo(View.MAIN);
    }

    public void AddButtonClicked() {
        if (!nodesDelete.isVisible()) {
            String input = nodesAdd.getText();
            tree = factory.getTree((String) choiceBox.getValue(), input);

            if (tree.root == null) {
                showAlert("Wrong input");
                nodesAdd.clear();
            }

           // tree.preOrder(tree.root);
            nodesAdd.setPromptText("Enter the new node (1-1000)");
            displayTree(0);
        }
        else {
            String str = nodesAdd.getText();
            try{
                int number = Integer.parseInt(str);
                if (!tree.search(number) && number > 0 && number < 1000) {
                    ArrayList<Integer> list = tree.path(number);
                    for (Integer integer : list) {
                        try {
                            displayTree(integer);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    tree.insert(number);

                    try {
                        displayTree(5);
                        Thread.sleep(3000);
                        displayTree(number);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    showAlert("This node is already present");
                    nodesAdd.clear();
                }
               // tree.preOrder(tree.root);

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
    }


    public void DeleteButtonClicked() {
        String str = nodesDelete.getText();
        try{
            int number = Integer.parseInt(str);
            if (tree.search(number))
                 tree.delete(number);
            else
                showAlert("This node isn't in the tree");
            displayTree(0);
            tree.preOrder(tree.root);
            nodesDelete.clear();
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    public void ClearButtonClicked() {
        tree.root = null;
        nodesDelete.setVisible(false);
        nodesDelete.setManaged(false);
        deleteButton.setVisible(false);
        deleteButton.setManaged(false);
        nodesAdd.clear();
        displayTree(0);
    }

    private void configureChoiceBox() {
        choiceBox.getItems().addAll(
                List.of(
                        "Binary search tree", "AVL tree"
                )
        );
        choiceBox.setValue("Binary search tree");
    }

    public void displayTree(int key) {
        treePane.getChildren().clear();
        if(tree.root != null){
            displayTree(tree.root, treePane.getWidth() / 2, this.vGap, treePane.getWidth() / 4, key);
            System.out.print("\n\n");
        }
    }

    protected void displayTree(Node root, double x, double y, double hGap, int key){
        if(root.left != null){
            treePane.getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.left, x - hGap, y + vGap, hGap / 2, key);
        }

        if (root.right != null){
            treePane.getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.right, x + hGap, y + vGap, hGap / 2, key);
        }

        Circle circle = new Circle(x, y, radius);
        System.out.print(root.data +"/"+ key + " ");
        if (root.data == key) {
            circle.setFill(Color.ORANGE);
            System.out.print("vjitbb ");
        }
        else
            circle.setFill(Color.MEDIUMPURPLE);
        circle.setStroke(Color.BLACK);
        treePane.getChildren().addAll(circle, new Text(x - 4, y + 4, root.data + ""));
        System.out.println(circle.getFill());
    }

    public void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setContentText(text);
        alert.show();
    }
}

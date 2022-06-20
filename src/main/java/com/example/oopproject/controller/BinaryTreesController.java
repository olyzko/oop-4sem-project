package com.example.oopproject.controller;

import com.example.oopproject.controller.model.algorithms.*;
import com.example.oopproject.ui.switch_handler.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class BinaryTreesController implements Initializable {

    @FXML
    public Button addButton;

    @FXML
    public Button deleteButton;

    @FXML
    public TextField nodesAdd;

    @FXML
    public TextField nodesDelete;

    @FXML
    public AnchorPane treePane;

    @FXML
    public Button clearButton;

    @FXML
    public ChoiceBox choiceBox;

    @FXML
    public Button InorderButton;

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
            try {
                int number = Integer.parseInt(str);
                if (!tree.search(number) && number > 0 && number < 1000) {
                    if (choiceBox.getValue() == "AVL tree") {
                        tree.insert(number);
                        displayTree(0);
                    }
                    else {
                        ArrayList<Integer> list = tree.path(number);
                        final Integer[] integer = {0};
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {

                            if (integer[0] == list.size()) {
                                tree.insert(number);
                                displayTree(number);
                                return;
                            }
                            displayTree(list.get(integer[0]));
                            System.out.print(integer[0] + "/" + list.get(integer[0]) + " " + list.size() + "\n");
                            integer[0]++;
                        }));
                        timeline.setCycleCount(list.size() + 1);
                        timeline.play();
                    }
                }
                else {
                    showAlert("This node is already present");
                    nodesAdd.clear();
                }
               // tree.preOrder(tree.root);

            }
            catch (NumberFormatException ex){
                showAlert("Wrong input");
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
            if (tree.search(number)) {
                if (choiceBox.getValue() == "AVL tree") {
                    tree.delete(number);
                    displayTree(0);
                }
                else {
                    ArrayList<Integer> list = tree.path(number);
                    final Integer[] integer = {0};
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {

                        if (integer[0] == list.size()) {
                            tree.delete(number);
                            displayTree(number);
                            return;
                        }
                        displayTree(list.get(integer[0]));
                        System.out.print(integer[0] + "/" + list.get(integer[0]) + " " + list.size() + "\n");
                        integer[0]++;
                    }));
                    timeline.setCycleCount(list.size() + 1);
                    timeline.play();
                }
            }
            else
                showAlert("This node isn't in the tree");
            nodesDelete.clear();
        }
        catch (NumberFormatException ex){
            showAlert("Wrong input");
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
        if (root.data == key) {
            circle.setFill(Color.ORANGE);
        }
        else
            circle.setFill(Color.MEDIUMPURPLE);
        circle.setStroke(Color.BLACK);
        double xFixed = fix(root.data, x);
        treePane.getChildren().addAll(circle, new Text(xFixed, y + 4, root.data + ""));
    }

    public void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setContentText(text);
        alert.show();
    }

    private double fix(int key, double x) {
        if (key >= 100)
            return x-8;
        else if (key >= 10)
            return x-6;
        else
            return x-4;
    }

    public void InorderButtonClicked() {
        ArrayList<Integer> list = tree.inOrder(tree.root);
        final Integer[] integer = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            displayTree(list.get(integer[0]));
            integer[0]++;
        }));
        timeline.setCycleCount(list.size());
        timeline.play();
    }

    public void PostorderButtonClicked() {
        ArrayList<Integer> list = tree.postOrder(tree.root);
        final Integer[] integer = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            displayTree(list.get(integer[0]));
            integer[0]++;
        }));
        timeline.setCycleCount(list.size());
        timeline.play();
    }
}

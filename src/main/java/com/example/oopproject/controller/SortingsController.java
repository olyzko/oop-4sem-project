package com.example.oopproject.controller;

import com.example.oopproject.controller.model.algorithms.ISorting;
import com.example.oopproject.controller.model.algorithms.SortingAlgorithmsFactory;
import com.example.oopproject.ui.switch_handler.View;
import com.example.oopproject.ui.switch_handler.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class SortingsController implements Initializable {

    // Style static fields----------------------------------------------------------------------------------------------

    private final static String ELEMENT_STYLE = "-fx-background-color: #03FCF4";

    private final static String CURRENT_ELEMENT_STYLE = "-fx-background-color: #A12D5B";

    // FXML bound elements----------------------------------------------------------------------------------------------

    @FXML
    public Slider sliderSize;

    @FXML
    public AnchorPane visualizerPanel;

    @FXML
    public ChoiceBox<String> choiceBox;

    // Algorithm utilities----------------------------------------------------------------------------------------------

    private int size = 25;

    private double[] array;

    private ISorting sortingAlgorithm;

    private SortingAlgorithmsFactory factory;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        factory = new SortingAlgorithmsFactory();
        configureChoiceBox();
        configureSizeSlider();
        configureVisualizerPanel();
        array = new double[size];
        generateArray();
    }

    /**
     * Applies configuration for choice box.
     */

    private void configureChoiceBox() {
        choiceBox.getItems().addAll(
                List.of(
                        "Insertion", "Selection", "Merge"
                )
        );
        choiceBox.setValue("Insertion");

        choiceBox.sceneProperty().addListener((observableValue, scene, t1) -> {
            sortingAlgorithm = factory.getSorting(choiceBox.getValue());
        });
    }

    /**
     * Applies configuration for visualizer panel.
     */

    private void configureVisualizerPanel() {
        visualizerPanel.sceneProperty().addListener((observableValue, scene, t1) -> {
            generateArray();
        });
    }

    /**
     * Applies configuration for size slider.
     */

    private void configureSizeSlider() {
        sliderSize.valueProperty().addListener((observableValue, number, t1) -> {
            size = (int) sliderSize.getValue();
            array = new double[size];
            visualizerPanel.getChildren().clear();
            generateArray();
        });
    }

    /**
     * Generate an array on the visualizer panel.
     */

    private void generateArray() {
        for (int i = 0; i < size; i++) {
            visualizerPanel.getChildren().add(generateElement(i));
        }
    }

    /**
     * Builds an element of an array as pane.
     *
     * @param index number of an element created
     * @return      created pane
     */

    private Pane generateElement(int index) {
        AnchorPane pane = new AnchorPane();
        double elementWidth = visualizerPanel.getWidth() / size;
        double elementHeight = new Random().nextDouble(280.0);
        array[index] = elementHeight;

        pane.setPrefHeight(elementHeight);
        pane.setPrefWidth(elementWidth - 0.1 * size);
        pane.setStyle(ELEMENT_STYLE);
        AnchorPane.setLeftAnchor(pane, elementWidth * index);
        AnchorPane.setBottomAnchor(pane, 0.0);

        return pane;
    }

    public void swap(double firstHeight, double secondHeight) {
        Pane firstPane;
        Pane secondPane;
        visualizerPanel.getChildren().forEach(
                e -> {
                    AnchorPane currentPane = ((AnchorPane) e);
                    //TODO implement visual swap
                }
        );
    }

    /**
     * Return to main scene when the "Main" button clicked.
     */

    public void onMainButton() {
        ViewSwitcher.switchTo(View.MAIN);
    }

    public void onStartButton() {
        sortingAlgorithm.sort(array);
    }
}

package com.example.oopproject.controller;

import com.example.oopproject.controller.model.algorithms.*;
import com.example.oopproject.ui.switch_handler.View;
import com.example.oopproject.ui.switch_handler.ViewSwitcher;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.net.URL;
import java.util.*;

public class SortingsController implements Initializable {

    // Style static fields----------------------------------------------------------------------------------------------

    private final static String ELEMENT_STYLE = "-fx-background-color: #03FCF4";

    private final static String CURRENT_ELEMENT_STYLE = "-fx-background-color: #A12D5B";

    // FXML bound elements----------------------------------------------------------------------------------------------

    @FXML
    public Slider sliderSize;

    @FXML
    public Slider sliderSpeed;

    @FXML
    public AnchorPane visualizerPanel;

    @FXML
    public ChoiceBox<String> choiceBox;

    @FXML
    public Button btnStart;

    // Algorithm utilities----------------------------------------------------------------------------------------------

    private int size = 25;

    private double delay = 200;

    private int[] array;

    private ISorting sortingAlgorithm;

    private SortingAlgorithmsFactory factory;

    Timer timer = new Timer();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sortingAlgorithm = new InsertionSort();
        factory = new SortingAlgorithmsFactory();
        configureChoiceBox();
        configureSizeSlider();
        configureDelaySlider();
        configureVisualizerPanel();
        array = new int[size];
        generateArray();
        sortingAlgorithm.getTrace().clear();
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
        choiceBox.setOnAction((e) -> {
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
            btnStart.setDisable(false);
            array = new int[size];
            visualizerPanel.getChildren().clear();
            generateArray();
        });
    }

    /**
     * Applies configuration for size slider.
     */

    private void configureDelaySlider() {
        sliderSpeed.valueProperty().addListener((observableValue, number, t1) -> {
            delay = sliderSpeed.getValue();
        });
    }

    /**
     * Generate an array on the visualizer panel.
     */

    private void generateArray() {
        int baseSize = (int) visualizerPanel.getHeight() / size;
        for (int i = 0; i < size; i++) {
            int he = baseSize * (i + 1);
            array[i] = he;
        }
        Shuffle.shuffleArray(array);
        for (int i = 0; i < size; i++) {
            visualizerPanel.getChildren().add(generateElement(i, array[i]));
        }
    }

    /**
     * Builds an element of an array as pane.
     *
     * @param index number of an element created
     * @return      created pane
     */

    private Pane generateElement(int index, int height) {
        AnchorPane pane = new AnchorPane();
        double elementWidth = visualizerPanel.getWidth() / size;

        pane.setPrefHeight(height);
        pane.setPrefWidth(elementWidth - 0.1 * size);
        pane.setStyle(ELEMENT_STYLE);
        AnchorPane.setLeftAnchor(pane, elementWidth * index);
        AnchorPane.setBottomAnchor(pane, 0.0);

        return pane;
    }

    /**
     * Performs passed as a parameter task after
     * specified amount of time in milliseconds
     *
     * @param millis        delay in milliseconds
     * @param continuation  action after the delay
     */
    @Deprecated
    public void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    /**
     * Set new height of the pane.
     *
     * @param prevHeight  previous height
     * @param newHeight   new height
     */

    public void setCurrent(int prevHeight, int newHeight) {
        visualizerPanel.getChildren().forEach(e -> {
            AnchorPane pane = ((AnchorPane) e);
            if (pane.getPrefHeight() == prevHeight) {
                pane.setPrefHeight(newHeight);
            }
        });
    }

    /**
     * Return to main scene when the "Main" button clicked.
     */

    public void onMainButton() {
        timer.cancel();
        sortingAlgorithm.getTrace().clear();
        ViewSwitcher.switchTo(View.MAIN);
    }

    /**
     * Start the visualization of the chosen sorting
     * algorithm.
     */

    public void onStartButton() {
        btnStart.setDisable(true);
        sortingAlgorithm.sort(array);

        if (sortingAlgorithm instanceof MergeSort) {
            mergeSortVisualization();
            return;
        }

        TimerTask task = new TimerTask() {
            final Iterator<Pair<Integer, Integer>> entries = sortingAlgorithm.getTrace().iterator();

            final Pane[] panes = new Pane[2];

            @Override
            public void run() {
                if (panes[0] != null) {
                    panes[0].setStyle(ELEMENT_STYLE);
                    panes[1].setStyle(ELEMENT_STYLE);
                }
                if (!entries.hasNext()) {
                    timer.cancel();
                    timer = new Timer();
                    sortingAlgorithm.getTrace().clear();
                    return;
                }
                Pair<Integer, Integer> currentEntry = entries.next();

                visualizerPanel.getChildren().forEach(e -> {
                    AnchorPane pane = ((AnchorPane) e);
                    if (pane.getPrefHeight() == currentEntry.getKey()) {
                        panes[0] = pane;
                    }
                    if (pane.getPrefHeight() == currentEntry.getValue()) {
                        panes[1] = pane;
                    }
                });

                panes[0].setPrefHeight(currentEntry.getValue());
                panes[1].setPrefHeight(currentEntry.getKey());

                panes[0].setStyle(CURRENT_ELEMENT_STYLE);
                panes[1].setStyle(CURRENT_ELEMENT_STYLE);
            }
        };
        timer.scheduleAtFixedRate(task, 100L, (long) delay);
    }

    public void mergeSortVisualization() {
        TimerTask task = new TimerTask() {
            final Iterator<Pair<Integer, Integer>> entries = sortingAlgorithm.getTrace().iterator();

            Pane paneCurr;

            @Override
            public void run() {
                if (paneCurr != null) {
                    paneCurr.setStyle(ELEMENT_STYLE);
                }
                if (!entries.hasNext()) {
                    timer.cancel();
                    timer = new Timer();
                    sortingAlgorithm.getTrace().clear();
                    return;
                }
                Pair<Integer, Integer> currentEntry = entries.next();

                visualizerPanel.getChildren().forEach(e -> {
                    AnchorPane pane = ((AnchorPane) e);
                    if (pane.getPrefHeight() == currentEntry.getKey()) {
                        paneCurr = pane;
                    }
                });

                paneCurr.setPrefHeight(currentEntry.getValue());

                paneCurr.setStyle(CURRENT_ELEMENT_STYLE);
            }
        };
        timer.scheduleAtFixedRate(task, 100L, (long) delay);
    }
}

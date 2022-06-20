package com.example.oopproject.controller;

import com.example.oopproject.controller.model.algorithms.Node;
import com.example.oopproject.model.JobCanvas;
import com.example.oopproject.ui.switch_handler.View;
import com.example.oopproject.ui.switch_handler.ViewSwitcher;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ConcurrentParallelController implements Initializable {

    @FXML
    public AnchorPane root;

    @FXML
    public Button btnStart;

    private List<JobCanvas> jobs;

    private AnimationTimer timer;

    private int jobIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jobs = Arrays.asList(
                new JobCanvas(0, 0),
                new JobCanvas((int) root.getPrefWidth() / 2, 0),
                new JobCanvas(0, 300),
                new JobCanvas((int) root.getPrefWidth() / 2, 300)
        );

        root.getChildren().addAll(
                jobs
        );

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                concurrent();
                parallel();
            }
        };

        timer.start();

        btnStart.toFront();
    }

    /**
     * Proceeds visualization of concurrent
     * process.
     */

    private void concurrent() {
        jobs.get(jobIndex).makeProgress();

        jobIndex++;

        if (jobIndex == 2) {
            jobIndex = 0;
        }
    }

    /**
     * Proceeds visualization of parallel
     * process
     */

    private void parallel() {
        jobs.get(2).makeProgress();
        jobs.get(3).makeProgress();
    }

    /**
     * Switches to the main scene when the
     * "main" button is pressed.
     */

    public void onMainButton() {
        ViewSwitcher.switchTo(View.MAIN);
    }
}

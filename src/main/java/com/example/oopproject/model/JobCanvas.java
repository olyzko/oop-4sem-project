package com.example.oopproject.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class JobCanvas extends Canvas {
    private GraphicsContext g;

    private boolean isDone = false;

    private double currentX = 0;
    private double currentY = 0;

    private static final double PIXELS_PER_PROGRESS = 4;

    public JobCanvas(int x, int y) {
        super(400, 300);

        setTranslateX(x);
        setTranslateY(y);

        g = getGraphicsContext2D();
        g.setFill(Color.BLACK);
    }

    public void makeProgress() {
        if (isDone)
            return;

        g.fillRect(currentX, currentY, PIXELS_PER_PROGRESS, PIXELS_PER_PROGRESS);

        currentX += PIXELS_PER_PROGRESS;

        if (currentX >= 400) {
            currentX = 0;

            currentY += PIXELS_PER_PROGRESS;

            if (currentY >= 300) {
                isDone = true;
            }
        }
    }
}

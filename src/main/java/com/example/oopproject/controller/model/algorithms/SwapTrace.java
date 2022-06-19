package com.example.oopproject.controller.model.algorithms;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SwapTrace {
    private final List<Pair<Integer, Integer>> trace = new ArrayList<>();

    public List<Pair<Integer, Integer>> getTrace() {
        return trace;
    }

    public void addStep(int firstElement, int secondElement) {
        trace.add(new Pair<>(firstElement, secondElement));
    }
}

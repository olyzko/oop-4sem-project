package com.example.oopproject.controller.model.algorithms;

import javafx.util.Pair;

import java.util.List;

public interface ISorting {
    void sort(int[] arr);

    List<Pair<Integer, Integer>> getTrace();
}

package com.example.oopproject.controller.model.algorithms;

import com.example.oopproject.controller.SortingsController;

/**
 * Sorting algorithms factory
 */

public class SortingAlgorithmsFactory {

    /**
     * Get an implementation of the requested sorting algorithm
     *
     * @param sortType type of the algorithm represented by a string
     * @return         corresponding implementation.
     */

    public ISorting getSorting(String sortType) {
        if (sortType.equalsIgnoreCase("INSERTION")) {
            return new InsertionSort();
        } else if (sortType.equalsIgnoreCase("SELECTION")){
            return new SelectionSort();
        } else if (sortType.equalsIgnoreCase("MERGE")){
            return new MergeSort();
        }
        //default is an insertion sort so that NullPointerException
        //will be avoided
        return new InsertionSort();
    }
}

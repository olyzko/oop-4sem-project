package com.example.oopproject.controller.model.algorithms;

public class SelectionSort extends SwapTrace implements ISorting {
    public void sort(int[] array) {
        int size = array.length;

        for (int step = 0; step < size - 1; step++) {
            int min_idx = step;

            for (int i = step + 1; i < size; i++) {

                // To sort in descending order, change > to < in this line.
                // Select the minimum element in each loop.
                if (array[i] < array[min_idx]) {
                    min_idx = i;
                }
            }

            // put min at the correct position
            addStep(array[step], array[min_idx]);
            int temp = array[step];
            array[step] = array[min_idx];
            array[min_idx] = temp;
        }
    }
}

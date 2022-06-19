package com.example.oopproject.controller.model.algorithms;

public class InsertionSort extends SwapTrace implements ISorting {

    public void sort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    addStep(arr[j], arr[j - 1]);
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }
}

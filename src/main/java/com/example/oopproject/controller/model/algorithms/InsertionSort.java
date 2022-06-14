package com.example.oopproject.controller.model.algorithms;

public class InsertionSort implements ISorting {

    public static void sort(int[] arr) {
        for (int i : arr) {
            int key = arr[i];
            int j = i-1;

            while (j>=0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public void sort(double[] arr) {

    }
}

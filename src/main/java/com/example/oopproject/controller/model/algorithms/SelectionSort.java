package com.example.oopproject.controller.model.algorithms;

public class SelectionSort {
    static void sort(int[] arr) {
        for (int i : arr) {
            int minIndex = i;
            for (int j : arr)
                if (arr[j] < arr[minIndex])
                    minIndex = j;

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}

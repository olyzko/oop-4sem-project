package com.example.oopproject.controller.model.algorithms;

public class SelectionSort {
    static void sort(int[] arr) {
        for (int i=0; i< arr.length; i++) {
            int minIndex = i;
            for (int j=0; j< arr.length; j++)
                if (arr[j] < arr[minIndex])
                    minIndex = j;

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}

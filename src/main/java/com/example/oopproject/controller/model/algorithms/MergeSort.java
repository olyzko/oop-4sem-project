package com.example.oopproject.controller.model.algorithms;

public class MergeSort extends SwapTrace implements ISorting {

    void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid-left+1;
        int n2 = right-mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                addStep(arr[k], leftArray[i]);
                arr[k] = leftArray[i];
                i++;
            }
            else {
                addStep(arr[k], rightArray[j]);
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            addStep(arr[k], leftArray[i]);
            arr[k] = leftArray[i];
            i++; k++;
        }

        while (j < n2) {
            addStep(arr[k], rightArray[j]);
            arr[k] = rightArray[j];
            j++; k++;
        }
    }

    void sortRecursive(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = (right-left)/2 + left;

            // Sort first and second halves
            sortRecursive(arr, left, mid);
            sortRecursive(arr, mid+1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    public void sort (int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        sortRecursive(arr, left, right);
    }
}

package com.example.oopproject.controller.model.algorithms;

import java.util.Random;

public class Shuffle {
    public static void shuffleArray(int[] array) {
        int N = array.length;
        for (int i = 0; i < N; i++) {
            int r = new Random().nextInt(i + 1);
            int temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }
    }
}

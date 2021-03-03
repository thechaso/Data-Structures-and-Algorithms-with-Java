package com.company;

import java.util.Arrays;

public class Array {
    private int[] arr;
    private int count = 0;

    public Array(int length) {
        arr = new int[length];
    }

    public void insert(int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = val;
                break;
            }
        }
        count ++;
        if (count > arr.length) {
            arr = Arrays.copyOf(arr, arr.length + 1);
            arr[arr.length - 1] = val;
        }

    }

    public void deleteIndex(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IllegalArgumentException();
        }
        else {
            for (int i = index; i < arr.length - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr = Arrays.copyOf(arr, arr.length - 1);
            count --;
        }
    }

    public void deleteValue(int val) {
        boolean isValid = false;
        int index = 0;
        for (int i: arr) {
            if (i == val) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new IllegalArgumentException();
        }
        else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == val) {
                    index = i;
                }
            }
            for (int i = index; i < arr.length - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr = Arrays.copyOf(arr, arr.length - 1);
            count --;
        }
    }

    public void getValueAtIndex(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IllegalArgumentException();
        }
        else {
            System.out.println(arr[index]);
        }
    }

    public void insertionSort() {
        for (var i = 0; i < arr.length; i++) {
            var current = arr[i];
            var j = i - 1;
            while ((j >= 0) && (arr[j] > current)) {
                arr[j + 1] = arr[j];
                j --;
            }
            arr[j + 1] = current;
        }
    }

    public void bubbleSort() {
        for (var i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] < arr[j - 1]) {
                    var temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public void selectionSort() {
        int i, j , minVal, minIndex, temp = 0;
        for (i = 0; i < arr.length; i++) {
            minVal = arr[i];
            minIndex = i;
            for (j = i; j < arr.length; j++) {
                if (arr[j] < minVal) {
                    minVal = arr[j];
                    minIndex = j;
                }
            }
            if (minVal < arr[i]) {
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public void print() {
        System.out.println(Arrays.toString(arr));
    }
}

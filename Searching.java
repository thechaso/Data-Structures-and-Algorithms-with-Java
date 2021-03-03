package com.company;

import java.util.Arrays;

public class Searching {
    public void linearSearch(int[] arr, int val) {
        int[] index = new int[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                index = Arrays.copyOf(index, index.length + 1);
                index[index.length - 1] = i;
            }
        }
        System.out.print("Value found at index: ");
        for (int i: index) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    //binary search can be implemented recursively or iteratively
    //binary search recursive implementation
    public boolean binarySearchRecursive(int[] arr, int val) {
        //first takes into account the whole  array
        return binarySearchRecursive(arr, val, 0, arr.length - 1);
    }
    //recursive helper method which splits the array into the 2 halves and views desired half
    private boolean binarySearchRecursive(int[] arr, int val, int left, int right) {
        //left and right are the inclusive indices of the part of the array we are viewing
        if (left > right) return false;//left can never be greater than right if val inside arr
        int mid = (left + right) / 2;//take midpoint
        if (arr[mid] == val) return true;//if midpoint is the value return true
        //if value is less than midpoint search the left
        if (val < arr[mid]) return binarySearchRecursive(arr, val, left, mid - 1);
        //else search the right half
        else return binarySearchRecursive(arr, val, mid + 1, right);
    }
    //binary search iterative implementation
    public boolean binarySearchIterative(int[] arr, int val) {
        //left and right are the inclusive indices of the part of the array we are viewing
        int left = 0, right = arr.length -1; //we first start by viewing the whole array
        while (left <= right) {
            int mid = (left + right) / 2;//take midpoint
            if (arr[mid] == val) return true;//if midpoint is the value return true
            //if value is less than midpoint search the left by decreasing right bound
            if (val < arr[mid]) right = mid - 1;
            //else search the right half by increasing left bound
            else left = mid + 1;
        }
        return false;
    }
}

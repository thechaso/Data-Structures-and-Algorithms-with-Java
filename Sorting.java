package com.company;

import java.util.HashMap;

public class Sorting {
    //bubble sort
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    //insertion sort
    public void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {//iterate through the array
            int current = arr[i];//get the current element of iteration
            int j = i - 1;//get indices of elements before the current element
            //get all the other elements while the second is greater then 0 which is in bound and
            //the element before current is greater than current in which case we should change it
            while ((j >= 0) && (arr[j] > current)) {
                arr[j + 1] = arr[j];//shift elements to the right
                j--;
            }
            arr[j + 1] = current;//insert current element
        }
    }
    //selection sort
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {//iterate through the array
            int minVal = arr[i];//initially take current element as minValue
            int minIndex = i;//the index of minValue is minIndex
            //everything before the current element should be sorted
            for (int j = i; j < arr.length; j++) {//iterate through unsorted part of array
                if (arr[j] < minVal) {//if there is an element lesser than minValue
                    minVal = arr[j];//update minValue
                    minIndex = j;//update minIndex
                }
            }
            if (minVal < arr[i]) {//if minValue is unsorted
                //swap
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
    //merge sort
    public int[] mergeSort(int[] arr) {
        //base case
        if (arr.length <= 1) return arr;//if array has completely been split then there is nothing else to do
        //general case
        int midIndex = arr.length / 2;//get midpoint to split array
        int[] left = new int[midIndex];//create left side of array
        int[] right;//create right side of array
        //check if array can be split evenly
        if (arr.length % 2 == 0) right = new int[midIndex];
        else right = new int[midIndex + 1];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];//initialize left array by getting left half elements from array
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[midIndex + i];//initialize right array by getting right half elements from array
        }
        left = mergeSort(left);//sort left side
        right = mergeSort(right);//sort right side
        int[] result = merge(left, right);//merge both
        return result;//return sorted array
    }

    //takes 2 arrays and merges them together in a sorted order
    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];//final array length
        int leftPointer = 0, rightPointer = 0, resultPointer = 0;//will be used iterate because we are using while  loops
        while (leftPointer < left.length || rightPointer < right.length) {//continue traversing until we have not reached end of arry
            if (leftPointer < left.length && rightPointer < right.length) {//if both pointers are still traversing
                if (left[leftPointer] < right[rightPointer]) {//if left element lass than right element
                    result[resultPointer++] = left[leftPointer++];//add lower value to result array
                }
                else {
                    result[resultPointer++] = right[rightPointer++];//add lower value to result array
                }
            }
            else if (leftPointer < left.length) {//if right is done traversing
                result[resultPointer++] = left[leftPointer++];//add left element to array
            }
            else if (rightPointer < right.length) {//if left is done traversing
                result[resultPointer++] = right[rightPointer++];//add right element to result array
            }
        }
        return result;//return result array
    }

    //string sorting problem
    public String stringSort(String word) {
        String sortedWord = "";
        char[] arr = word.toCharArray();
        //implementing bubble sort
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    char temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (char i : arr) sortedWord += Character.toString(i);
        return sortedWord;
    }
    //minimum swaps to sort problem
    //first approach to this problem will use selection sort
    public int minSwapsToSortSelectionSort(int[] arr) {
        int numSwaps = 0;
        for (int i = 0; i < arr.length; i++) {
            int minVal = arr[i];
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < minVal) {
                    minVal = arr[j];
                    minIndex = j;
                }
            }
            if (minVal < arr[i]) {
                int temp = arr[i];
                arr[i] = minVal;
                arr[minIndex] = temp;
                numSwaps++;
            }
        }
        return numSwaps;
    }
    //the second approach to this problem is more efficient and it uses graph theory
    public int minSwapsToSort(int[] arr) {
        boolean[] visited = new boolean[arr.length + 1];//visited array will be used to track cycles
        HashMap<Integer, Integer> map = new HashMap<>();//hashmap to relate position and value
        for (int i = 1; i < visited.length; i++) {
            map.put(i, arr[i - 1]);//populate hashmap
        }
        int swapCount = 0;//number of swaps
        for (int i = 1; i <= map.size(); i++) {//iterate through hashmap, positions starting at 1
            int nextNode;
            if (!visited[i]) {//if current node is not visited
                visited[i] = true;//mark it as visited
                if (i == map.get(i)) continue;//if position is same as value it is fine, so move on
                int nextIndex = map.get(i);//else treat value as position for next value
                while (!visited[nextIndex]) {//if next element is not visited
                    visited[nextIndex] = true;//mark it as visited
                    nextNode = map.get(nextIndex);//swap them
                    nextIndex = nextNode;//update positions
                    ++swapCount;//increase swap count
                }
            }
        }
        return swapCount;//return minimum number of swaps to sort array
    }
}

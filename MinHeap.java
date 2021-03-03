package com.company;

import java.util.Arrays;

public class MinHeap {
    private int capacity = 10;
    private int size = 0;
    int[] items = new int[capacity];
    //helper methods
    //getLeftChildIndex
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }
    //getRightChildIndex
    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }
    //getParentIndex
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }
    //hasLeftChild
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }
    //hasRightChild
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }
    //hasParent
    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }
    //leftChild
    private int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }
    //rightChild
    private int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }
    //parent
    private int parent(int index) {
        return items[getParentIndex(index)];
    }
    //swap
    private void swap(int indexOne, int indexTwo) {
        int temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }
    //ensureExtraCapacity
    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }
    //heapifyDown
    public void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChildIndex(index) < smallerChildIndex) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (items[index] < items[smallerChildIndex]) {
                break;
            }
            else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }
    //heapifyUp
    public void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index) > items[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }
    //class methods
    //peek
    public int peek() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        return items[0];
    }
    //poll
    public int poll() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        int item = items[0];
        items[0] = items[size - 1];
        size --;
        heapifyDown();
        return item;
    }
    //add
    public void add(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size ++;
        heapifyUp();
    }
    //toArray
    public int[] toArray() {
        return items;
    }
}

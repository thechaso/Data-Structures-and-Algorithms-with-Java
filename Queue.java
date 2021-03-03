package com.company;

import java.util.NoSuchElementException;

public class Queue {
    //Node Class
    private class Node {
        int value;
        Node next;

        public Node(int item) {
            this.value = item;
        }
    }
    private Node front, back;
    //isEmpty
    public boolean isEmpty() {
        var current = front;
        if (front == null) {
            return true;
        }
        return false;
    }
    //peek
    public int peek() {
        return front.value;
    }
    //add
    public void add(int item) {
        // Create new node
        Node node = new Node(item);
        //make node value to item
        node.value = item;
        //if queue is empty
        if (isEmpty()) {
            // set front and back to node
            front = back = node;
        }
        // If queue is not empty
        else {
            back.next = node;
            back = node;
        }
    }
    //remove
    public void remove() {
        // Exceptions
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (front == back) {
            front = back = null;
            return;
        }
        // second is the node after first
        var second = front.next;
        // Delete link between first and second node
        front.next = null;
        // set front to second node
        front = second;
    }
    //print
    public void print() {
        var current = front;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}

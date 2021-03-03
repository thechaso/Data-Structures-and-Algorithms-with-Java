package com.company;

import java.util.NoSuchElementException;

public class LinkedList {
    // Node Class
    private class Node {
        // Value in Linked List Node will point to
        private int value;
        // Next Node in Linked List
        private Node next;
        // Constructor
        public Node(int value) {
            this.value = value;
        }
    }
    // head
    private Node first;
    // tail
    private Node last;

    //addLast
    public void addLast(int item) {
        var node = new Node(item);
        // Value of the node/index = item we want to append
        node.value = item;
        // If list is empty
        if (first == null) {
            // Set head and tail to item/node
            first = last = node;
        }
        // If list is not empty
        else {
            // element after the tail node will be the item
            last.next = node;
            // New tail will be set to appended item
            last = node;
        }
    }
    //addFirst
    public void addFirst(int item) {
        var node = new Node(item);
        node.value = item;
        if (first == null) {
            first = last = node;
        }
        else {
            node.next = first;
            first = node;
        }
    }
    //indexOf
    public int indexOf(int item) {
        int index = 0;
        var current = first;
        // while we have not reached the end of the list, we iterate through elements
        while (current != null) {
            // If this is value we are looking for
            if (current.value == item) {
                return index;
            }
            // Else we will move on to the next node and continue searching
            current = current.next;
            // Index will increment by 1
            index++;
        }
        // If value not in list, return -1
        return -1;
    }
    //contains
    public boolean contains(int item) {
        return indexOf(item) != -1;
    }
    //removeFirst
    public void removeFirst() {
        // If list is empty
        if (first == null) {
            // error
            throw new NoSuchElementException();
        }
        // If list has 1 element
        if (first == last) {
            // make list empty
            first = last = null;
            return;
        }
        // store second node which will be first node in temporary variable
        var second = first.next;
        // delete link between head and second nodes
        first.next = null;
        // make head point to initial second node
        first = second;
    }
    //removeLast
    public Node getPrevious(Node node) {
        var current = first;
        while (current != null) {
            if (current.next == null) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    public void removeLast() {
        var secondLast = getPrevious(last);
        System.out.println(secondLast);
        last = secondLast;
        last.next = null;
    }
    //print
    public void print() {
        var current = first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}

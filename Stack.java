package com.company;

import java.util.EmptyStackException;

public class Stack {
    //Node Class
    private class Node {
        int value;
        Node next;

        public Node(int item) {
            this.value = item;
        }
    }
    //Node top
    Node top;
    //isEmpty
    public boolean isEmpty() {
        if (top == null){
            return true;
        }
        return false;
    }
    //push
    public void push(int item) {
        Node node = new Node(item);
        node.next = top;
        top = node;
    }
    //pop
    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        var second = top.next;
        top.next = null;
        top = second;
    }
    //peek
    public int peek() {
        return top.value;
    }
    //print
    public void print() {
        var current = top;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}

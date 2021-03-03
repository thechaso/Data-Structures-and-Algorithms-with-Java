package com.company;
//BinarySearchTree

class Node {
    // Node Attributes, leaves wil have null left and right
    Node left, right;
    int data; //value of node
    //Constructor
    public Node(int data) {
        this.data = data;
    }
    //insert
    public void insert(int value) {
        //if value we want to insert is less than root
        if (value <= data) {
            //traverse towards the left side
            //if this is a leaf
            if (left == null) {
                //insert the new node
                left = new Node(value);
            }
            else {
                //while we don't reach a leaf, keep traversing left
                left.insert(value);
            }
        }
        //if value we want to insert > root
        else {
            // If we reach a node whose right is null
            if (right == null) {
                //insert the new node
                right = new Node(value);
            }
            else {
                //while we don't reach a null node, keep traversing right
                right.insert(value);
            }
        }
    }
    //remove
    public void remove(int value) {
        //if value we want to delete is less than root
        //traverse to the left side
        if (value < data) {
            //if we have reached our value --> remove it
            if (left.data == value) {
                left = null;
            }
            //else keep traversing left
            else {
                left.remove(value);
            }
        }
        //same thing for right side
        if (value > data) {
            //if we have reached our value --> remove it
            if (right.data == value) {
                right = null;
            }
            //else keep traversing right
            else {
                right.remove(value);
            }
        }
    }
    //contains
    public boolean contains(int value) {
        //if value we want to find is the root, return true
        if (value == data) {
            return true;
        }
        // otherwise if value we want to find < root, traverse left
        else if (value < data) {
            //if left node is null
            if (left == null) {
                //return false
                return false;
            }
            else {
                //while we don't reach a  null node continue traversing left
                return left.contains(value);
            }
        }
        //same thing for right
        else {
            if (right ==  null) {
                return false;
            }
            else {
                return right.contains(value);
            }
        }
    }
    //printInOrder
    //left --> root --> right
    public void printInOrder() {
        //print all the left nodes first
        if (left != null) {
            left.printInOrder();
        }
        // then print root
        System.out.println(data);
        //then print right nodes
        if (right != null) {
            right.printInOrder();
        }
    }
    //printPreorder
    //root --> left --> right
    public void printPreOrder() {
        //print root first
        System.out.println(data);
        //print all left nodes
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }
    //printPostOrder
    //left --> right --> root
    public void printPostOrder() {
        //print all left nodes first
        if (left != null) {
            left.printPostOrder();
        }
        //print all right nodes
        if (right != null) {
            right.printPostOrder();
        }
        //print root
        System.out.println(data);
    }
    public void printInorderIteratively() {
        var currentLeft = left;
        while (currentLeft != null) {
            System.out.println(currentLeft.data);
            currentLeft = currentLeft.left;
        }
        System.out.println(data);
        var currentRight= right;
        while (currentRight != null) {
            System.out.println(currentRight.data);
            currentRight = currentRight.right;
        }
    }
}

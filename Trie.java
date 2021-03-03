package com.company;

public class Trie {
    //Node Class
    class Node {
        public char c;
        public boolean isWord;
        //children array will store 26 elements as it is the number of letters
        //it will only store lower case letters
        //each index will be responsible for a certain character
        //children[0] = 'a', children[1] = 'b', children[2] = 'c', etc
        public Node[] children;
        //constructor
        public Node(char c) {
            this.c = c;
            isWord = false;
            children = new Node[26];
        }
    }

    public Node root;//this is a dummy node which wont store a real value

    //constructor
    public Trie() {
        root = new Node('\0');//this means it is an empty character
    }
    //insert method will insert word into trie
    public void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
             if (current.children[c - 'a'] == null) current.children[c - 'a'] = new Node(c);
             current = current.children[c - 'a'];
        }
        //the very last character in any word we are inserting always has an isWord = true
        current.isWord = true;
    }
    //search method returns if word is in the trie
    public boolean search(String word) {
        Node node = getNode(word);
        return node != null && node.isWord;
    }
    //startsWith method returns if any word in the trie starts with the given prefix
    public boolean startsWith(String prefix) {
        return getNode(prefix) != null;
    }

    //helper method will return last node in the word we are looking for
    public Node getNode(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c =  word.charAt(i);
            if (current.children[c - 'a'] == null) return null;
            current = current.children[c - 'a'];
        }
        return current;//current is very last character of the word
    }
}

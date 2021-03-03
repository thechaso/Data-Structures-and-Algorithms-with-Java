package com.company;

import java.util.Arrays;
import java.util.LinkedList;

//Entry Class is like the node class, each index in our array will consist of linkedLists of entry nodes
class Entry <K, V> { // K, V are dataTypes
    int hash;//this will store the hash code
    K key;
    V value;
    //constructor will initialize key, value, and hash
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }
    //isCollisionMethod
    public boolean equals(Entry<K, V> other) {
        //if hashCodes are the same return true
        if (hash == other.hash) {
            return true;
        }
        return key.equals(other.key);
    }
    public String toString() {
        return key + "-->" + value;
    }
}
//HashTable Class
public class HashTable<K, V>{
    private static final int defaultCapacity = 3;
    private static final double defaultLoadFactor = 0.75;
    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>> [] table;//we wills tore entryType nodes
    //Constructor
    public HashTable(int capacity, double maxLoadFactor) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        if (maxLoadFactor <= 0) {
            throw new IllegalArgumentException();
        }
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(defaultCapacity, capacity);
        this.threshold = (int) (maxLoadFactor * this.capacity);
        table = new LinkedList[this.capacity];
    }
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private int getIndexFromHash(int keyHash) {
        //return index of the key which maps to the argument hashCode
        return (keyHash * 0x7FFFFFFF) % capacity;
    }
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }
    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
        if (key == null) {
            return null;
        }
        //new linkedList of entryType nodes in bucketIndex in table array
        //table = [LinkedList(Entry<key, value>, Entry<key, value>)]
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null) {
            return null;
        }
        //for each node in the linkedList
        for (Entry<K, V> entry: bucket) {
            //if node has the hashcode as the key' hashcode
            if (entry.key.equals(key)) {
                return entry;
            }
        }
        return null;
    }
    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    int bucketIndex = getIndexFromHash(entry.hash);
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if (bucket == null) newTable[bucketIndex] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }
    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
        //assign a linkedList to buckIndex in table Array LinkedList
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null) {
            //make a blank LinkedList
            table[bucketIndex] = bucket = new LinkedList<>();
        }
        //
        Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
        if (existentEntry == null) {
            //Add the entry node to bucket LinkedList
            bucket.add(entry);
            if (size++ > threshold) {
                resizeTable();
            }
            return null;
        }
        else {
            V oldVal = existentEntry.value;
            existentEntry.value = entry.value;
            return oldVal;
        }
    }
    public boolean containsKey(K key) {
        //get index of key
        int bucketIndex = getIndexFromHash(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }
    public V insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        //make new node
        Entry<K, V> newEntry = new Entry<>(key, value);
        //get index of the entry node's hash code
        int bucketIndex = getIndexFromHash(newEntry.hash);
        //insert the new node into bucketIndex
        return bucketInsertEntry(bucketIndex, newEntry);
    }
}

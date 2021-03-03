package com.company;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IntroStringProblems {
    //substrings
    public void printAllSubstrings(String word) {
        char[] arr = word.toCharArray();
        //pick starting point
        for (int start = 1; start <= arr.length; start++) {
            //pick ending point
            for (int end = 0; end <= arr.length - start; end++) {
                //characters from current start to current end
                int current = end + start - 1;
                for (int i = end; i <= current; i++) {
                    System.out.print(arr[i]);
                }
                System.out.println();
            }
        }
    }

    public String[] getAllSubstrings(String word) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {//i = starting point
            for (int j = i + 1; j <= word.length(); j++) {//j = ending point not inclusive
                String substring = word.substring(i, j);//word[i, j)
                list.add(substring);
            }
        }
        String[] stringArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            stringArr[i] = list.get(i);
        }
        return stringArr;
    }

    public static String[] getSubstringsOfLength(String t, int len) {
        String[] substrings = new String[]{};
        for (int i = 0; i < t.length(); i++) {
            int end = i + len;
            if (end <= t.length()) {
                String newSubString = t.substring(i, end);
                substrings = Arrays.copyOf(substrings, substrings.length + 1);
                substrings[substrings.length - 1] = newSubString;
            }
        }
        return substrings;
    }

    //reverse string using recursion
    public String reverseStringRecursion(String word) {
        //function(abcd) = function(bcd) + a = function(cd) + b + a = function(d) + c + b + a
        //= base case --> d + c + b + a
        //base case
        if (word.isEmpty()) return word;
        //general case
        String reversed = reverseStringRecursion(word.substring(1)) + word.charAt(0);
        return reversed;
    }

    //check if word is palindrome
    public boolean isPalindrome(String word) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            stack.push(current);
            queue.add(current);
        }
        while (!stack.isEmpty()) {
            char stackNode = stack.pop();
            char queueNode = queue.remove();
            if (stackNode != queueNode) return false;
        }
        return true;
    }
    //get all permutations of a string
    public void printPermutations(String word) {
        permute("", word);//calling helper method
    }
    /*
    This is a recursive helper method printing all permutations of a given string
    @param prefix is what has already been permuted
    so if word is abc, prefix would be a, then b, then c
    @param suffix is what is left to permute so in the above example b and c would be left to permute
    base case is if prefix is blank, at that point we have got all permutations
    general case is iterating through the remaining suffix, and recurse
    @Param new prefix will be initial prefix and the first character of original remaining
    @Param new remaining will be from remaining[0, currentLetter) + remaining[currentLetter + 1, end)
    this will basically surround the currentNode
     */
    private void permute(String prefix, String remaining) {
        //base case
        if (remaining.isEmpty()) {
            System.out.println(prefix);
        }
        //general case
        for (int i = 0; i < remaining.length(); i++) {
            permute(prefix + remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1));
        }
    }
    //isAnagram
    /*
    Take 2 input strings
    convert the strings to character arrays
    Sort the character arrays
    if they are equal then return true, else return false
     */
    public boolean isAnagram(String target, String word) {
        char[] targetArr = target.toCharArray();
        char[] wordArr = word.toCharArray();
        Arrays.sort(targetArr);
        Arrays.sort(wordArr);
        return Arrays.equals(targetArr, wordArr);
    }
}

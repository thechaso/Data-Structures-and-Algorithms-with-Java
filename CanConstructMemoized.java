package com.company;

import java.util.HashMap;

public class CanConstructMemoized {
    public boolean canConstruct(String target, String[] wordBank) {
        //base case
        if (target.equals("")) return true;//we know that this is possible and so we return true to parent
        //general case
        for (String word : wordBank) {//for each element in wordBank array
            //check if the word is contained somewhere inside the target string
            //and check if the word starts at the begging of target, not in the middle or end
            if ((target.contains(word)) && (target.indexOf(word) == 0)) {
                //slice the target from the word to get the suffix which is the new child node
                //ex. abcd - ab = cd as we slice the string
                String suffix = target.substring(word.length());
                if (canConstruct(suffix, wordBank)) {//recurse and check if the child node can be constructed
                    //if child node can be constructed return true
                    return true;
                }
            }
        }
        return false;//return false if target cannot be constructed
    }

    public HashMap<String, Boolean> memo = new HashMap<>();
    public boolean canConstructMemoization(String target, String[] wordBank) {
        //check if argument is inside memo
        if (memo.containsKey(target)) return memo.get(target);
        //base case
        if (target.equals("")) return true;//we know that this is possible and so we return true to parent
        //general case
        for (String word : wordBank) {//for each element in wordBank array
            //check if the word is contained somewhere inside the target string
            //and check if the word starts at the begging of target, not in the middle or end
            if ((target.contains(word)) && (target.indexOf(word) == 0)) {
                //slice the target from the word to get the suffix which is the new child node
                //ex. abcd - ab = cd as we slice the string
                String suffix = target.substring(word.length());
                if (canConstructMemoization(suffix, wordBank)) {//recurse and check if the child node can be constructed
                    //if child node can be constructed return true
                    memo.put(target, true);//store value in memo
                    return true;
                }
            }
        }
        memo.put(target, false);//store value in memo
        return false;//return false if target cannot be constructed
    }

    public void main() {
        System.out.println(canConstructMemoization("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(canConstructMemoization("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(canConstructMemoization("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}));
    }
}

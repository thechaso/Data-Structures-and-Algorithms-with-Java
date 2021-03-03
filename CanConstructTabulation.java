package com.company;

import java.util.Arrays;

public class CanConstructTabulation {
    public boolean canConstruct(String target, String[] wordBank) {
        //create table of length target.length + 1
        boolean[] table = new boolean[target.length() + 1];
        //initialize table by filling it with false values
        Arrays.fill(table, false);
        //seed value
        table[0] = true;
        //iterate through table
        for (int i = 0; i < table.length; i++) {
            //check if it is possible to gat table element
            if (table[i]) {
                //iterate through wordBank
                for (String word : wordBank) {
                    //check if indices are inside bounds
                    //check if the target contains the word
                    //check if the word matches the characters starting position
                    if (i + word.length() <= target.length() && target.contains(word) && target.substring(i, i + word.length()).equals(word)) {
                        //update further table elements
                        table[i + word.length()] = true;
                    }
                }
            }
        }
        //return the last element of table which is the target.length() index
        return table[target.length()];
    }

    public void main() {
        System.out.println(canConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(canConstruct("skateboard", new String[]{"bo", "rd", "ate", "t", "ska"}));
        System.out.println(canConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"f", "eee", "eeeeee", "eeeeeeeeeeeeeeeeee"}));
    }
}

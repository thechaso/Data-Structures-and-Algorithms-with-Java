package com.company;

import java.util.Arrays;

public class CountConstructTabulation {
    public int countConstruct(String target, String[] wordBank) {
        //create table of length target.length() + 1
        int[] table = new int[target.length() + 1];
        //initialize table by filling it with 0s
        Arrays.fill(table, 0);
        //seed value
        table[0] = 1;
        //iterate through table
        for (int i = 0; i < table.length; i++) {
            //iterate through wordBank
            for (String word : wordBank) {
                //check if indices are inside bounds
                //check if the word is inside the target
                //check if the word begins at index 0 of target
                if (i + word.length() < table.length && target.contains(word) && target.substring(i, i + word.length()).equals(word)) {
                    table[i + word.length()] += table[i];//update further elements of table
                }
            }
        }
        return table[target.length()];
    }
    public void main() {
        System.out.println(countConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.println(countConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
    }
}

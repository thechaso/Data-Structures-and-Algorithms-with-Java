package com.company;

import java.util.Arrays;

public class AllConstructTabulation {
    public int[][][] allConstruct(String target, String[] wordBank) {
        //create a table of length target.length() + 1
        int[][][] table = new int[target.length() + 1][1][0];//initialize table by filling it with empty arrays
        //seed values

        return table;
    }
    public void main() {
        System.out.println(Arrays.deepToString(allConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "ef", "c"})));
    }
}

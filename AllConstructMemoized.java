package com.company;

import java.util.*;
import java.util.LinkedList;
import java.util.Stack;

public class AllConstructMemoized {
    public String[] addFirstSingleDimension(String[] elements, String element) {
        String[] newArray = Arrays.copyOf(elements, elements.length + 1);
        newArray[0] = element;
        System.arraycopy(elements, 0, newArray, 1, elements.length);
        return newArray;
    }

    public String[][] addFirst(String[][] arr, String element) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = addFirstSingleDimension(arr[i], element);
        }
        return arr;
    }

    public String[][] allConstruct(String target, String[] wordBank) {
        //base case
        if (target.equals("")) return new String[][]{};
        //general case
        ArrayList<String[][]> result = new ArrayList<>();
        for (String word : wordBank) {
            if ((target.contains(word)) && (target.indexOf(word) == 0)) {
                String suffix = target.substring(word.length());
                String[][] suffixWays = allConstruct(suffix, wordBank);
                String[][] targetWays = addFirst(suffixWays, word);
                result.add(targetWays);
                System.out.println(Arrays.deepToString(targetWays));
            }
        }
        return new String[][]{};
    }
    public void main() {
        System.out.println(Arrays.deepToString(allConstruct("purple", new String[]{"purp", "p", "ur", "le", "purp"})));
        //System.out.println(Arrays.deepToString(allConstruct("purple", new String[]{"purp", "p", "ur", "le", "purp"})));

    }
}

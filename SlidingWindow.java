package com.company;

import java.util.Arrays;

public class SlidingWindow {
    //to solve this problem we can also just use java syntax
    //return t.contains(p) or t.indexOf(p)
    public int[] slidingWindow(String t, String p) {
        int[] indicesFound = new int[]{};
        for (int start = 0; start < t.length(); start++) {
            int end = start + p.length();//not inclusive
            //range will be [start, end)
            if (end <= t.length()) {
                String window = "";
                for (int k = start; k < end; k++) {
                    window += t.charAt(k);
                }
                if (window.equals(p)) {
                    indicesFound = Arrays.copyOf(indicesFound, indicesFound.length + 1);
                    indicesFound[indicesFound.length - 1] = start;
                }
            }
        }
        return indicesFound;
    }
}

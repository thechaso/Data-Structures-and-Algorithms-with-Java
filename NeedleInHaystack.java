package com.company;

import java.util.Arrays;

public class NeedleInHaystack {
    public int[] needleInHaystack(String text, String pattern) {
        //implement sliding window algorithm
        //compare window to substring using hash values
        int[] indicesFound = new int[]{};
        for (int start = 0; start < text.length(); start++) {
            int end = start + pattern.length();//non inclusive
            if (end <= text.length()) {
                String window = "";
                for (int k = start; k < end; k++) {
                    window += text.charAt(k);
                }
                int windowHash = window.hashCode();
                int patternHash = pattern.hashCode();
                if (windowHash == patternHash) {
                    indicesFound = Arrays.copyOf(indicesFound, indicesFound.length + 1);
                    indicesFound[indicesFound.length - 1] = start;
                }
            }
        }
        return indicesFound;
    }
}

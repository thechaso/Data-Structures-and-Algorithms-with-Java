package com.company;

import java.util.Arrays;

public class HowSumTabulation {
    public int[] howSum(int targetSum, int[] numbers) {
        //create table
        int[][] table = new int[targetSum + 1][0];
        //initialize table by filling it with null values
        for (int i = 0; i <= targetSum; i++) table[i] = null;
        //seed values
        table[0] = new int[]{};
        //iterate through table
        for (int i = 0; i <= targetSum; i++) {
            //check if table[i] is possible to get
            if (table[i] != null) {
                //iterate through numbers array
                for (int num : numbers) {
                    //check if indices are out of bounds
                    if (i + num <= targetSum) {
                        //create new array at further index of table
                        table[i + num] = new int[table[i].length];
                        //make a copy of all the elements at table[i]
                        table[i + num] = Arrays.copyOf(table[i], table[i].length + 1);
                        //append num
                        table[i + num][table[i + num].length - 1] = num;
                    }
                }
            }
        }
        return table[targetSum];
    }
    public void main() {
        System.out.println(Arrays.toString(howSum(7, new int[]{5, 3, 4})));
        System.out.println(Arrays.toString(howSum(8, new int[]{2, 3, 5})));
        System.out.println(Arrays.toString(howSum(300, new int[]{7, 14})));
    }
}

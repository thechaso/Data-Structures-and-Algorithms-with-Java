package com.company;

import java.util.Arrays;

public class CanSumTabulation {
    public boolean canSum(int targetSum, int[] numbers) {
        //create table
        boolean[] table = new boolean[targetSum + 1];
        //initialize table by filling it with false values
        Arrays.fill(table, false);
        //seed values
        table[0] = true;
        //iterate through table
        for (int i = 0; i <= targetSum; i++) {
            if (table[i]) {//if it is possible to get table[i] then move on to next steps
                //iterate through numbers array
                for (int j = 0; j < numbers.length; j++) {
                    //check if index we are updating in table is not out of bounds
                    // update table by making element of teh table true
                    if (i + numbers[j] <= targetSum) table[i + numbers[j]] = true;
                }
            }
        }
        return table[targetSum];//return element in table referring to targetSum
    }
    public void main() {
        System.out.println(canSum(300, new int[]{7, 14}));
    }
}

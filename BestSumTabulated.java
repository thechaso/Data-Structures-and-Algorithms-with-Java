package com.company;

import java.util.Arrays;

public class BestSumTabulated {
    public int[] bestSum(int targetSum, int[] numbers) {
        //create table of length targetSum + 1
        int[][] table = new int[targetSum + 1][0];
        //initialize array by filling it with null  values
        for (int i = 0; i <= targetSum; i++) table[i] = null;
        //seed values
        table[0] = new int[]{};
        //iterate through table
        for (int i = 0; i <= targetSum; i++) {
            //check if it is possible to get to current table element
            if (table[i] != null) {
                //iterate through numbers array
                for (int num : numbers) {
                    //make the new array as a copy of the initial array of the current table element
                    int[] newArr = Arrays.copyOf(table[i], table[i].length + 1);
                    //append the numbers array element
                    newArr[newArr.length - 1] = num;
                    //check if the index is inside the bounds of table
                    //and if the further element in table is null or the newArray is shorter than current array
                    if ((i + num <= targetSum) && (table[i + num] == null || newArr.length < table[i + num].length)) {
                        //update further positions
                        table[i + num] = newArr;
                    }
                }
            }
        }
        //return targetSum index of table
        return table[targetSum];
    }
    public void main() {
        System.out.println(Arrays.toString(bestSum(8, new int[]{2, 3, 5})));
        System.out.println(Arrays.toString(bestSum(100, new int[]{1, 2, 5, 25})));
    }
}

package com.company;

import java.util.Arrays;
import java.util.HashMap;

public class BestSumMemoized {
    //We will basically be using the howSum algorithm but optimizing the length afterwards by taking
    //array with shortest length
    public int[] bestSum(int targetSum, int[] numbers) {
        //base case
        //if targetSum is 0, it is possible to get that sum, and so we rerun blank array to parent
        if (targetSum == 0) return new int[]{};
        if (targetSum < 0) return null;//targetSum shouldn't be less than 0
        //general case
        int[] shortestCombination = null;//we will be updating this by comparing to array lengths
        for (int num : numbers) {//for each element in numbers array
            int remainder = targetSum - num;//parent nod value
            int[] remainderCombination = bestSum(remainder, numbers);//recurse
            if (remainderCombination != null) {//if the remainderCombination is possible to attain
                //make a new array combination which will basically be [remainderCombo, num] appended
                int[] combination = Arrays.copyOf(remainderCombination, remainderCombination.length + 1);
                combination[combination.length - 1] = num;//append num
                //update shortestCombination to new array if its length is even shorter
                if (shortestCombination == null || combination.length < shortestCombination.length) {
                    shortestCombination = Arrays.copyOf(combination, combination.length);
                }
            }
        }
        return shortestCombination;//return shortest path which can be null or an array
    }

    public HashMap<Integer, int[]> memo = new HashMap<>();
    //We will basically be using the howSum algorithm but optimizing the length afterwards by taking
    //array with shortest length
    public int[] bestSumMemoized(int targetSum, int[] numbers) {
        //check if argument is inside memo
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        //base case
        //if targetSum is 0, it is possible to get that sum, and so we rerun blank array to parent
        if (targetSum == 0) return new int[]{};
        if (targetSum < 0) return null;//targetSum shouldn't be less than 0
        //general case
        int[] shortestCombination = null;//we will be updating this by comparing to array lengths
        for (int num : numbers) {//for each element in numbers array
            int remainder = targetSum - num;//parent nod value
            int[] remainderCombination = bestSumMemoized(remainder, numbers);//recurse
            if (remainderCombination != null) {//if the remainderCombination is possible to attain
                //make a new array combination which will basically be [remainderCombo, num] appended
                int[] combination = Arrays.copyOf(remainderCombination, remainderCombination.length + 1);
                combination[combination.length - 1] = num;//append num
                //update shortestCombination to new array if its length is even shorter
                if (shortestCombination == null || combination.length < shortestCombination.length) {
                    shortestCombination = Arrays.copyOf(combination, combination.length);
                }
            }
        }
        memo.put(targetSum, shortestCombination);//store values inside memo
        return shortestCombination;//return shortest path which can be null or an array
    }
    public void main() {
        System.out.println(Arrays.toString(bestSumMemoized(7, new int[]{5, 3, 4, 7})));
        System.out.println(Arrays.toString(bestSumMemoized(8, new int[]{2, 3, 5})));
        System.out.println(Arrays.toString(bestSumMemoized(8, new int[]{1, 4, 5})));
        System.out.println(Arrays.toString(bestSumMemoized(100, new int[]{1, 2, 5, 25})));
    }
}

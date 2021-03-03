package com.company;

import java.util.Arrays;
import java.util.HashMap;

public class HowSumMemoized {
    public int[] howSum(int targetSum, int[] numbers) {
        //base case
        if (targetSum == 0) return new int[]{};
        if (targetSum < 0) return null;//no target sum less tha 0
        //general case
        for (int num : numbers) {
            int remainder = targetSum - num;//remainder can't be negative, remainder will also be child node
            int[] remainderResult = howSum(remainder, numbers);//recurse and find possible sums of children nodes
            if (remainderResult != null) {//if result is not null, there is sum, so let's find it
                //append edge weights connecting parent and child node to array
                remainderResult = Arrays.copyOf(remainderResult, remainderResult.length + 1);
                remainderResult[remainderResult.length - 1] = num;
                return remainderResult;
            }
        }
        return null;//else return null
    }

    //memo keys will contain the targetSum
    //memo values will contain integer arrays which sum to the key which is the targetSum
    public HashMap<Integer, int[]> memo = new HashMap<>();
    public int[] howSumMemoized(int targetSum, int[] numbers) {
        //check if memo contains argument
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        //base case
        if (targetSum == 0) return new int[]{};
        if (targetSum < 0) return null;//no target sum less tha 0
        //general case
        for (int num : numbers) {
            int remainder = targetSum - num;//remainder can't be negative, remainder will also be child node
            int[] remainderResult = howSumMemoized(remainder, numbers);//recurse and find possible sums of children nodes
            if (remainderResult != null) {//if result is not null, there is sum, so let's find it
                //append edge weights connecting parent and child node to array
                remainderResult = Arrays.copyOf(remainderResult, remainderResult.length + 1);
                remainderResult[remainderResult.length - 1] = num;
                memo.put(targetSum, remainderResult);//memoize by storing key and value
                return remainderResult;
            }
        }
        memo.put(targetSum, null);//memoize by storing key and value
        return null;//else return null
    }

    public void main() {
        System.out.println(Arrays.toString(howSumMemoized(300, new int[]{7, 14})));
        System.out.println(Arrays.toString(howSumMemoized(8, new int[]{2, 3, 5})));
    }
}

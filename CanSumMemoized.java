package com.company;

import java.util.HashMap;

public class CanSumMemoized {
    public boolean canSum(int targetSum, int[] numbers) {
        //base case
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;//we are only going to have positive values for n=targetSum
        //general case
        for (int num : numbers) {
            int remainder = targetSum - num;//get the child node
            if (canSum(remainder, numbers)) return true;//recurse flush out solutions of child
        }
        return false;
    }

    //memo key = targetSums which is the root of the tree and subtrees
    //memo value = boolean weather or not sum can be acquired
    HashMap<Integer, Boolean> memo = new HashMap<>();
    public boolean canSumMemoized(int targetSum, int[] numbers) {
        //check is argument is inside memo
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        //base case
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;//we are only going to have positive values for n=targetSum
        //general case
        for (int num : numbers) {
            int remainder = targetSum - num;//get the child node
            if (canSumMemoized(remainder, numbers))  {//recurse flush out solutions of children
                memo.put(targetSum, true);//store value in memo
                return true;
            }
        }
        memo.put(targetSum, false);//store value in memo
        return false;
    }

    public void main() {
        System.out.println(canSumMemoized(300, new int[]{7, 14}));
        System.out.println(canSum(300, new int[]{7, 14}));
    }
}

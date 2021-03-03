package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class FibMemoized {
    //Classic recursion
    public static int fib(int n) {
        //base cases
        if (n == 0) return 0;
        if (n == 1) return 1;
        //general case
        return fib(n - 1) + fib(n - 2);
    }

    //Memoization
    public static HashMap<Integer, Integer> memo = new HashMap<>();
    public static int fibMemoized(int n) {
        //check if n is already calculated
        if (memo.containsKey(n)) return memo.get(n);//if so we don't need to recurse
        //base cases
        if (n == 0) return 0;
        if (n == 1) return 1;
        //general case
        memo.put(n, fibMemoized(n - 1) + fibMemoized(n - 2));//memoize
        return memo.get(n);
    }

    public void main() {
        HashMap<Integer, Integer> memo = new HashMap<>();
        Scanner input = new Scanner(System.in);
        System.out.println(fibMemoized(input.nextInt()));
    }
}

package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class GridTravellerMemoized {
    public static int gridTraveller(int m, int n) {
        //base cases
        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;
        //general case
        return gridTraveller(m - 1, n) + gridTraveller(m, n - 1);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        System.out.println(gridTraveller(m, n));
    }

    public static HashMap<String, Integer> memo = new HashMap<>();

    public static int gridTravellerMemoized(int m, int n) {
        //ex. key = "2,3": value = 3
        String key = m + "," + n;
        //are the arguments in the memo
        if (memo.containsKey(key)) return memo.get(key);
        //base cases
        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;
        //general case
        int sol = gridTraveller(m - 1, n) + gridTraveller(m, n - 1);
        memo.put(key, sol);
        return sol;
    }

    public static void main() {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        System.out.println(gridTravellerMemoized(m, n));
    }

}

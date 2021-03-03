package com.company;

import java.util.Arrays;

public class FibTabulated {
    public int fib(int n) {
        //table must contain n + 1 elements because indices should range from 0 to n inclusive
        int[] table = new int[n + 1];
        //initial value of each element in table will start with 0
        //this is because we will be adding to each element and so 0 would be a good base to start summing
        Arrays.fill(table, 0);
        //fib(1) = 1 and fib(0) is already = table[0] = 0, kind of like base case
        table[1] = 1;
        for (int i = 0; i <= table.length - 3; i++) {//iterate through table not exceeding size
            int curValue = table[i];//current element we are at
            table[i + 1] += curValue;//add the next value byu this current value
            table[i + 2] += curValue;//and add the next to next element  by this value as well
        }
        return table[n];//return the table index
    }
    public void main() {
        System.out.println(fib(6));
        System.out.println(fib(7));
        System.out.println(fib(8));
        System.out.println(fib(60));
    }
}

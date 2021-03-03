package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class GridTravellerTabulated {
    public int gridTraveller(int m, int n) {
        int[][] table = new int[m + 1][n + 1];//initialize table with m+1 by n+1 dimensions
        for (int i = 0; i < table.length; i++) Arrays.fill(table[i], 0);//fill table with 0s
        table[1][1] = 1;//1 way to navigate through 1 by 1 grid
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                //update down and right values
                int curValue = table[i][j];
                if (j + 1 <= n) table[i][j + 1] += curValue;
                if (i + 1 <= m) table[i + 1][j] += curValue;
            }
        }
        return table[m][n];
    }

    public void main() {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        System.out.println(gridTraveller(m, n));
    }
}

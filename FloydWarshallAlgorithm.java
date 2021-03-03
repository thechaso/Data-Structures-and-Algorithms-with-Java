package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FloydWarshallAlgorithm {
    private int numNodes;
    private boolean solved;
    private int[][] dp;
    private Integer[][] next;
    private final int reachesNegativeCycle = -1;

    public FloydWarshallAlgorithm(int[][] matrix) {
        numNodes=  matrix.length;
        dp = new int[numNodes][numNodes];
        next = new Integer[numNodes][numNodes];
        // Copy input matrix and setup 'next' matrix for path reconstruction.
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (matrix[i][j] != Integer.MAX_VALUE) next[i][j] = j;
                dp[i][j] = matrix[i][j];
            }
        }
    }

    public int[][] getMatrix() {
        solve();
        return dp;
    }

    public void solve() {
        if (solved) return;

        // Compute all pairs shortest paths.
        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Identify negative cycles by propagating the value 'NEGATIVE_INFINITY'
        // to every edge that is part of or reaches into a negative cycle.
        for (int k = 0; k < numNodes; k++)
            for (int i = 0; i < numNodes; i++)
                for (int j = 0; j < numNodes; j++)
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = Integer.MIN_VALUE;
                        next[i][j] = reachesNegativeCycle;
                    }

        solved = true;//mark solve as true so that we are not stuck in a the same cycle
    }

    public List<Integer> reconstructShortestPath(int start, int end) {
        solve();
        List<Integer> path = new ArrayList<>();
        if (dp[start][end] == Integer.MAX_VALUE) return path;
        int at = start;
        for (; at != end; at = next[at][end]) {
            // Return null since there are an infinite number of shortest paths.
            if (at == reachesNegativeCycle) return null;
            path.add(at);
        }
        // Return null since there are an infinite number of shortest paths.
        if (next[at][end] == reachesNegativeCycle) return null;
        path.add(end);
        return path;
    }

    // Creates a graph with n nodes. The adjacency matrix is constructed
    // such that the value of going from a node to itself is 0.
    public static int[][] createGraph(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(matrix[i], Integer.MAX_VALUE);
            matrix[i][i] = 0;
        }
        return matrix;
    }

    public void main() {
        Scanner input = new Scanner(System.in);
        // Construct graph.
        System.out.print("Enter Number of Vertices: ");
        int n = input.nextInt();
        System.out.println("Enter Number of Edges: ");
        int e = input.nextInt();
        int[][] m = createGraph(n);

        // Add some edge values.
        System.out.println("Enter " + e + " edges: ");
        for (int i = 0; i < e; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            int weight = input.nextInt();
            m[u][v] = weight;
        }

        FloydWarshallAlgorithm solver = new FloydWarshallAlgorithm(m);
        int[][] dist = solver.getMatrix();

        System.out.println(Arrays.deepToString(dist));
    }
}

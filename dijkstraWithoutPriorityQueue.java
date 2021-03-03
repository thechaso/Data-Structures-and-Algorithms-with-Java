package com.company;

import java.util.Scanner;

public class dijkstraWithoutPriorityQueue {
    //below method will print shortest path from 0 to all other nodes
    public void dijkstra(int[][] adjacencyMatrix) {//takes in adjacency matrix as parameter
        int vertices = adjacencyMatrix.length;//number of vertices
        boolean[] visited = new boolean[vertices];//visited array
        int[] dist = new int[vertices];//distance array will store shortest distances
        dist[0] = 0;//distance form starting node to itself will be 0
        for (int i = 1; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;//set all other elements to infinity
        }
        for (int i = 0; i < vertices - 1; i++) {
            //find vertex with minimum distance
            int minVertex = findMinVertex(dist, visited);
            visited[minVertex] = true;//mark node as visited
            //Explore neighbors
            for (int j = 0; j < vertices; j++) {//go through neighbors
                //if we are not at the source node, if the node has not been visited, and if it is not infinity
                if (adjacencyMatrix[minVertex][j] != 0 && !visited[j] && dist[minVertex] != Integer.MAX_VALUE) {
                    //set new distance as initial value plus edge weight
                    int newDist = dist[minVertex] + adjacencyMatrix[minVertex][j];
                    if (newDist < dist[j]) {// if the new distance is shorter, mutate dist array
                        dist[j] = newDist;
                    }
                }
            }
        }
        //Print
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " " + dist[i]);

        }
    }

    public int findMinVertex(int[] dist, boolean[] visited) {//will return minVertex
        int minVertex = -1;//initial value
        for (int i = 0; i < dist.length; i++) {//for each of the distances
            //if it is not visited and minVertex is the initial value or it is not shortest distance
            if (!visited[i] && (minVertex == -1 || dist[i] < dist[minVertex])) {
                minVertex = i;//set minVertex to index of adjacency matrix
            }
        }
        return minVertex;//return minVertex
    }

    public void main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of vertices and edges: ");
        int vertices = input.nextInt();
        int edges = input.nextInt();
        int[][] adjacencyMatrix = new int[vertices][vertices];
        System.out.println("Enter " + edges + " edges and their respective weights");
        for (int i = 0; i < edges; i++) {
            int v1 = input.nextInt();
            int v2 = input.nextInt();
            int weight = input.nextInt();
            adjacencyMatrix[v1][v2] = weight;
            adjacencyMatrix[v2][v1] = weight;
        }
        dijkstra(adjacencyMatrix);
    }
}

package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraAlgorithm {
    class Edge {//similar to node class, it will take source and neighbors and store the weights
        int source;
        int neighbor;
        int weight;
        public Edge(int source, int neighbor, int weight) {//constructor
            this.source = source;
            this.neighbor=  neighbor;
            this.weight = weight;
        }
    }

    public void main() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of Vertices: ");
        int vertices = input.nextInt();//input number of nodes in graph
        ArrayList<Edge>[] graph = new ArrayList[vertices];//adjacency list
        for (int i = 0 ; i <  vertices; i++) {
            graph[i] = new ArrayList<>();//each element of the adjacency list will be list of neighbors
        }
        System.out.print("Enter Number of Edges: ");//input number of edges
        int edges = input.nextInt();//input number of edges
        System.out.println("Enter " + edges + " edges and their respective weights: ");
        for (int i = 0; i < edges; i++) {
            int v1 = input.nextInt();//input u node
            int v2 = input.nextInt();//input v node
            int wt = input.nextInt();//input weight between nodes u and v
            graph[v1].add(new Edge(v1, v2, wt));//add this to adjacency list of neighbors
            graph[v2].add(new Edge(v1, v2, wt));//undirected edge so it's bidirectional
        }
        System.out.print("Enter a source node: ");
        int source = input.nextInt();//input source

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();//blank priority queue
        priorityQueue.add(new Pair(source, source + " ", 0));//add source
        boolean[] visited = new boolean[vertices];//visited array
        System.out.println("Shortest Paths: ");
        while (!priorityQueue.isEmpty()) {//while priority queue is not empty
            Pair rem = priorityQueue.remove();//remove first element and store it
            if (visited[rem.node]) continue;//if it visited than skip operation
            visited[rem.node] = true;//otherwise if it isn't visited mark it as visited
            System.out.println(rem.node + " via " + rem.path + " @ " + rem.weight);//print
            for (Edge e : graph[rem.node]) {//for every edge in adjacency list
                if (!visited[e.neighbor]) {//if it is not yet visited
                    //add it to the priority queue to explore neighbors
                    priorityQueue.add(new Pair(e.neighbor, rem.path + e.neighbor, rem.weight + e.weight));
                }
            }
        }
    }

    //will help priority queue compare elements and give priority to shortest path
    public class Pair implements Comparable<Pair> {
        int node;
        String path;
        int weight;

        public Pair(int node, String path, int weight) {//constructor
            this.node = node;
            this.path = path;
            this.weight = weight;
        }

        public int compareTo(Pair o) {//built in method to compare pairs
            return this.weight - o.weight;
        }
    }
}

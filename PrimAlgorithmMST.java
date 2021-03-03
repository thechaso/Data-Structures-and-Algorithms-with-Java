package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimAlgorithmMST {//very similar to dijkstra's algorithm
    public class Edge {//will form elements in adjacency list, similar to node class
        int source;//where edge is coming from
        int neighbor;//where edge is going to
        int weight;//cost of edge
        public Edge(int source, int neighbor, int weight) {//constructor
            this.source = source;
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }

    //will be used to minimize cost
    public class Pair implements Comparable<Pair> {//Pair class elements in Priority Queue
        int vertex;
        int acquiringVertex;
        int weight;

        public Pair(int vertex, int acquiringVertex, int weight) {//constructor
            this.vertex = vertex;
            this.acquiringVertex = acquiringVertex;
            this.weight = weight;
        }

        public int compareTo(Pair o) {//for prioritizing minimal edge cost
            return this.weight - o.weight;
        }
    }

    public void main() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int vertices = input.nextInt();
        System.out.print("Enter number of edges: ");
        int edges = input.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[vertices];//adjacency list
        for (int i = 0; i <  vertices; i++) graph[i] = new ArrayList<>();
        System.out.println("Enter " + edges + " edges: ");
        for (int i = 0; i <  edges; i++) {
            int v1 = input.nextInt();
            int v2 = input.nextInt();
            int weight = input.nextInt();
            graph[v1].add(new Edge(v1, v2, weight));//undirected edge
            graph[v2].add(new Edge(v2, v1, weight));//undirected edge
        }

        PriorityQueue<Pair> priorityQueue=  new PriorityQueue<>();//blank priority queue
        //starting node is by default acquired by imaginary node -1 at a cost of 0
        priorityQueue.add(new Pair(0, -1, 0));//add starting node
        boolean[] visited = new boolean[vertices];//visited array
        while (!priorityQueue.isEmpty()) {
            Pair cur = priorityQueue.remove();//remove and store first element
            if (visited[cur.vertex]) continue;//do not use nodes already visited
            visited[cur.vertex] = true;//if node is not visited mark it visited
            if (cur.acquiringVertex != -1) {//exception is starting node, since -1 is imaginary acquiring node
                //work
                System.out.println(cur.vertex + " " + cur.acquiringVertex + " @ " + cur.weight);
            }
            for (Edge edge : graph[cur.vertex]) {//for every neighbor of current node
                if (!visited[edge.neighbor]) {//if neighbor has not been visited
                    priorityQueue.add(new Pair(edge.neighbor, cur.vertex, edge.weight));//add it to priority queue
                }
            }
        }
    }
}

package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class BellmanFordAlgorithm {
    public class Edge {//Each element in the edge list that is a directed edge
        int source, destination, weight;
        public Edge(int source, int destination, int weight) {//constructor
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    //below method takes in edge list, num vertices, and source node
    public int[] bellmanFord(Edge[] edges, int vertices, int source) {
        int[] dist = new int[vertices];//distance array will store the shortest distances
        Arrays.fill(dist, Integer.MAX_VALUE);//each element will start with infinity
        dist[source] = 0;//distance from source to itself is 0
        boolean relaxedEdge = true;//if we cannot relax an edge --> it is a negative cycle
        for (int v = 0; v < vertices - 1 && relaxedEdge; v++) {//iterate vertices - 1 times
            relaxedEdge = false;
            for (Edge edge : edges) {//for each edge in edge list
                if (dist[edge.source] + edge.weight < dist[edge.destination]) {//if distance is optimal
                    dist[edge.destination] = dist[edge.source] + edge.weight;//update distance array
                    relaxedEdge = true;//mark edge as relaxed
                }
            }
        }

        // Run algorithm a second time to detect which nodes are part
        // of a negative cycle. A negative cycle has occurred if we
        // can find a better path beyond the optimal solution.
        relaxedEdge = true;
        for (int v = 0; v < vertices - 1 && relaxedEdge; v++) {
            relaxedEdge = false;
            for (Edge edge : edges) {
                if (dist[edge.source] + edge.weight < dist[edge.destination]) {
                    dist[edge.destination] = Integer.MIN_VALUE;
                    relaxedEdge = true;
                }
            }
        }
        return dist;//return shortest distance array
    }

    public void main() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of Vertices: ");
        int V = input.nextInt();
        System.out.print("Enter Number of Edges: ");
        int E = input.nextInt();
        System.out.print("Enter Starting Node: ");
        int startNode = input.nextInt();
        Edge[] edges = new Edge[E];//edge list
        System.out.println("Enter " + E + " edges: ");
        for (int i = 0; i < E; i++) {
            int source = input.nextInt();
            int destination = input.nextInt();
            int weight = input.nextInt();
            edges[i] = new Edge(source, destination, weight);//add directed edge
        }
        int[] result = bellmanFord(edges, V, startNode);//call bellmanFord method
        for (int i = 0; i < V; i++)
            System.out.println(result[i]);//print each element of distance array
    }
}

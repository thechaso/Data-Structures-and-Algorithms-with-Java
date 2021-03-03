package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSort {
    static class Edge {//Edge class will help add edges to adjacency list
        int source, neighbor;//starting pos and its neighbors

        private Edge(int source, int destination) {//constructor
            this.source = source;
            this.neighbor = destination;
        }
    }

    public void solve() {//take input and output topological ordering, shortest, and longest paths
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of Vertices: ");
        int vertices = input.nextInt();
        System.out.print("Enter NUmber of Edges: ");
        int edges = input.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[vertices];//adjacency list
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();//each element in the list will contain another list
        }

        System.out.println("Enter " + edges + " edges: ");

        for (int i = 0; i < edges; i++) {
            int v1 = input.nextInt();
            int v2 = input.nextInt();
            graph[v1].add(new Edge(v1, v2));//add directed edges from source to destination
        }

        System.out.println("Enter Source and Destination: ");
        int source = input.nextInt();
        int destination = input.nextInt();

        System.out.println("Adjacency List: ");
        printAdjacencyList(graph);//display adjacency list

        //print topological ordering
        boolean[] visitedT = new boolean[vertices];//visited array
        java.util.Stack<Integer> stackT = new java.util.Stack<>();//blank stack
        System.out.println("Topological Ordering: ");
        System.out.println(Arrays.toString(topologicalSort(graph, visitedT, stackT, vertices)));
        //shortest path array
        boolean[] visitedS = new boolean[vertices];//visited array
        java.util.Stack<Integer> stackS = new java.util.Stack<>();//blank stack
        Integer[] shortestPathArr = dagShortestPath(graph, visitedS, stackS, source, vertices);
        System.out.println("Shortest Paths on DAG: ");
        System.out.println(Arrays.toString(shortestPathArr));
        //shortest path from source to destination
        System.out.println("Shortest Path from source to destination: ");
        System.out.println(shortestPathArr[destination]);
        //longest path array
        boolean[] visitedL = new boolean[vertices];//visited array
        java.util.Stack<Integer> stackL = new java.util.Stack<>();//blank stack
        Integer[] longestPathArr = dagLongestPath(graph, visitedL, stackL, source, vertices);
        System.out.println("Longest Paths on DAG: ");
        System.out.println(Arrays.toString(longestPathArr));
        //longest path from source to destination
        System.out.println("Longest Path from source to destination: ");
        System.out.println(longestPathArr[destination]);
    }

    public void topologicalSortUtil(ArrayList<Edge>[] graph, int source, boolean[] visited,
                                       Stack<Integer> stack) {
        visited[source] = true;//mark starting node as visited
        for (Edge edge : graph[source]) {//for each element in starting node's neighbors
            if (!visited[edge.neighbor]) {//if these neighbors are not visited
                topologicalSortUtil(graph, edge.neighbor, visited, stack);//recurse and sort the neighbors
            }
        }
        stack.push(source);//add source to stack
    }

    public Integer[] topologicalSort(ArrayList<Edge>[] graph, boolean[] visited,
                                     Stack<Integer> stack, int vertices) {
        for (int v = 0; v < vertices; v++) {//for every vertex in graph
            if (!visited[v]) {//if it is not visited
                topologicalSortUtil(graph, v, visited, stack);//sort it
            }
        }
        return stackToArray(stack);//return sorted array
    }

    public Integer[] dagShortestPath(ArrayList<Edge>[] graph, boolean[] visited, Stack<Integer> stack,
                                     int source, int numNodes) {
        Integer[] topSort = topologicalSort(graph, visited, stack, numNodes);//use topological sort
        Integer[] dist = new Integer[numNodes];//make blank array to store shortest distances between nodes
        dist[source] = 0;//distance between source and itself will be 0
        for (int i = 0; i < numNodes; i++) {
            int nodeIndex = topSort[i];//for every element in sorted array
            if (dist[nodeIndex] != null) {//if element is not null
                ArrayList<Edge> adjacentEdges = graph[nodeIndex];//get all the neighbors of this element
                if (adjacentEdges != null) {
                    for (Edge edge : adjacentEdges) {//for each edge in the neighbors
                        int newDist = dist[nodeIndex] + 1;//add distance to the weight of each edge which is 1
                        if (dist[edge.neighbor] == null) dist[edge.neighbor] = newDist;
                        else dist[edge.neighbor] = Math.min(dist[edge.neighbor], newDist);//get shortest distance
                    }
                }
            }
        }
        return dist;
    }

    //this method will basically repeat what dagShortestPath does except the implementation is different
    //multiply all edge weights by -1 so that the shortest path or minimum distance will be longest
    //then multiply the result by -1 to make positive again
    public Integer[] dagLongestPath(ArrayList<Edge>[] graph, boolean[] visited, Stack<Integer> stack,
                                    int source, int numNodes) {
        Integer[] topSort = topologicalSort(graph, visited, stack, numNodes);
        Integer[] dist = new Integer[numNodes];
        dist[source] = 0;
        for (int i = 0; i < numNodes; i++) {
            int nodeIndex = topSort[i];
            if (dist[nodeIndex] != null) {
                ArrayList<Edge> adjacentEdges = graph[nodeIndex];
                if (adjacentEdges != null) {
                    for (Edge edge : adjacentEdges) {
                        int newDist = dist[nodeIndex] - 1;//edge weight will be multiplied by -1
                        if (dist[edge.neighbor] == null) dist[edge.neighbor] = newDist;
                        else dist[edge.neighbor] = Math.min(dist[edge.neighbor], newDist);
                    }
                }
            }
        }
        Integer[] posDist = new Integer[dist.length];
        for (int i = 0; i < posDist.length; i++) {
            //we need to add a condition to check if number is not null
            //this is because we cannot multiply -1 by null value
            if (dist[i] != null) posDist[i] = -1 * dist[i];//convert negative number into positive numbers
            else posDist[i] = dist[i];
        }
        return posDist;
    }

    public void printAdjacencyList(ArrayList<Edge>[] graph) {//will print arrays of adjacency list
        for (ArrayList<Edge> edgeArrayList : graph) {
            String[] arr = new String[0];
            for (Edge num : edgeArrayList) {
                arr = Arrays.copyOf(arr, arr.length + 1);
                arr[arr.length - 1] = num.source + " " + num.neighbor;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public Integer[] stackToArray(Stack<Integer> stack) {//convert stack to array
        Integer[] arr = new Integer[stack.size()];
        int index = 0;
        while (!stack.isEmpty()) {
            arr[index] = stack.pop();
            index ++;
        }
        return arr;
    }
}

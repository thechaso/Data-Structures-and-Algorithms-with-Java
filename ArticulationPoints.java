package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticulationPoints {
    public int vertices, id, rootNodeOutComingEdgeCount;///num Nodes, id assigned to each node, out degree
    public boolean solved;//helps escape negative cycles
    public int[] lowLinkValues, ids;//each node's lowLinkValue and id
    //Articulation array will store boolean variables of true and false if a node is an articulation point
    public boolean[] visited, isArticulationPoint;//visited array and articulationPoints storing array
    public List<List<Integer>> graph;//adjacency list

    public ArticulationPoints(List<List<Integer>> graph, int vertices) {//constructor
        this.graph = graph;
        this.vertices = vertices;
    }

    // Returns the indexes for all articulation points in the graph even if the
    // graph is not fully connected.
    public boolean[] findArticulationPoints() {
        if (solved) return isArticulationPoint;//not repeating cycles
        id = 0;//initial id of node
        lowLinkValues = new int[vertices];
        ids = new int[vertices];
        visited = new boolean[vertices];
        isArticulationPoint = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {//for every vertex in graph
            if (!visited[i]) {//if node has not been visited
                rootNodeOutComingEdgeCount = 0;//out degree will be 0
                dfs(i, i, -1);//run dfs
                isArticulationPoint[i] = (rootNodeOutComingEdgeCount > 1);//if out degree is more than 1, its an AP
            }
        }
        solved = true;//mark it is solved
        return isArticulationPoint;//return array
    }

    public void dfs(int root, int at, int parent) {//will traverse graph and assign lowLinkValues and ids
        if (parent == root) rootNodeOutComingEdgeCount ++;//base case
        visited[at] = true;
        lowLinkValues[at] = ids[at] = id ++;//assign a new id at each recursion
        List<Integer> edges = graph.get(at);//wills store all neighbors of current node
        for (Integer neighbor : edges) {//for each neighbor current node has
            if (neighbor == parent) continue;//base case
            if (!visited[neighbor]) {//if it is not visited
                dfs(root, neighbor, at);//recurse and explore its neighbors
                lowLinkValues[at] = Math.min(lowLinkValues[at], lowLinkValues[neighbor]);//update lowLInkValue
                if (ids[at] <= lowLinkValues[neighbor]) {//if id of current node < lowLInk of neighbor
                    isArticulationPoint[at] = true;//it is an AP
                }
                else {//if lowLinkValue is greater than id number
                    lowLinkValues[at] = Math.min(lowLinkValues[at], ids[neighbor]);//update and minimize lowLinkValue
                }
            }
        }
    }
    /* Graph helpers */
    // Initialize a graph with 'n' nodes.
    public List<List<Integer>> createGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        return graph;
    }
    // Add an undirected edge to a graph.
    public void addEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    public void main() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int vertices = input.nextInt();
        System.out.print("Enter number of edges: ");
        int edges = input.nextInt();
        List<List<Integer>> graph = createGraph(vertices);
        ArticulationPoints solver = new ArticulationPoints(graph, vertices);
        System.out.println("Enter " + edges + " edges: ");
        for (int i = 0; i < edges; i++) {
            int v1 = input.nextInt();
            int v2 = input.nextInt();
            solver.addEdge(graph, v1, v2);
        }
        boolean[] isArticulationPoint = solver.findArticulationPoints();
        for (int i = 0; i < vertices; i++)
            if (isArticulationPoint[i]) System.out.println("Node " + i + " is an articulation point");
    }
}

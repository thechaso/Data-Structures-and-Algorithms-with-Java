package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bridges {
    private int vertices, id;//num nodes in graph, id of each node
    private int[] lowLinkValue, ids;//arrays to compare and update ids and lowLinkValues
    private boolean solved;//to escape negative cycles
    private boolean[] visited;//visited array
    private List<List<Integer>> graph;//adjacency list
    private List<Integer> bridges;//bridges list store pairs of nodes which are connected by bridges

    public Bridges(List<List<Integer>> graph, int vertices) {//constructor
        this.graph = graph;
        this.vertices = vertices;
        this.ids = new int[vertices];
    }

    // Returns a list of pairs of nodes indicating which nodes form bridges.
    // The returned list is always of even length and indexes (2*i, 2*i+1) form a
    // pair. For example, nodes at indexes (0, 1) are a pair, (2, 3) are another
    // pair, etc...
    public List<Integer> findBridges() {
        if (solved) return bridges;//base case if we have already done this
        id = 0;//initial id number is 0
        lowLinkValue = new int[vertices];
        visited = new boolean[vertices];
        bridges = new ArrayList<>();//will store pairs of nodes connected by bridges
        // Finds all bridges in the graph across various connected components.
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, bridges);
            }
        }
        solved = true;//mark solved as true
        return bridges;//return pairs of nodes connected by bridges list
    }

    public void dfs(int at, int parent, List<Integer> bridges) {
        visited[at] = true;//mark current node as visited
        ids[at] = ++id;//go to the next available id number and assign to current node
        lowLinkValue[at] = ids[at];//initial lowLinkValue will be id number
        for (Integer to : graph.get(at)) {//for each neighbor of the current node
            if (to == parent) continue;//base case
            if (!visited[to]) {//if neighbor has not been visited
                dfs(to, at, bridges);//recurse and run dfs on its neighbors
                //update lowLinkValue to minimum of current node id and its neighbor's id
                lowLinkValue[at] = Math.min(lowLinkValue[at], lowLinkValue[to]);
                if (ids[at] < lowLinkValue[to]) {//if node id is lower than lowLinkValue
                    //then the edge that connects those nodes is a bridge
                    bridges.add(at);//add source of edge
                    bridges.add(to);//add destination of edge
                } else {//if id is greater than lowLinkValue
                    //update lowLinkValue to minimum
                    lowLinkValue[at] = Math.min(lowLinkValue[at], ids[to]);
                }
            }
        }
    }

    // Initialize graph with 'n' nodes.
    public static List<List<Integer>> createGraph(int n) {//return adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        return graph;
    }

    // Add undirected edge to graph.
    public static void addEdge(List<List<Integer>> graph, int from, int to) {//add undirected edges
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

        System.out.println("Enter " + edges + " edges: ");
        for (int i = 0; i < edges; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            addEdge(graph, from, to);
        }

        Bridges solver = new Bridges(graph, vertices);
        List<Integer> bridges = solver.findBridges();

        System.out.println(bridges.toString());
        for (int i = 0; i < bridges.size() / 2; i++) {
            int node1 = bridges.get(2 * i);
            int node2 = bridges.get(2 * i + 1);
            System.out.printf("Bridge between nodes: %d and %d\n", node1, node2);
        }
    }
}
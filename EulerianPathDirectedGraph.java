package com.company;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class EulerianPathDirectedGraph {
    public int vertices;//num of nodes in graph
    public int edgeCount;//number of edges in the graph
    public int[] in, out;//will store inDegree and outDegree of each node
    public java.util.LinkedList<Integer> path;//eulerian path
    public List<List<Integer>> graph;//adjacency list

    public EulerianPathDirectedGraph(List<List<Integer>> graph) {//constructor
        this.graph = graph;
        this.vertices = graph.size();
        path = new LinkedList<>();
    }

    // Returns a list of edgeCount + 1 node ids that give the Eulerian path or
    // null if no path exists or the graph is disconnected.
    public int[] getEulerianPath() {
        setUp();//counts inDegree and outDegree of each node and edgeCount

        if (!graphHasEulerianPath()) return null;//if there is no eulerian path return nothing
        //if there is a eulerian path run a dfs on graph
        dfs(findStartNode());//fill path linkedlist with the eulerian path of nodes

        // Make sure all edges of the graph were traversed. It could be the
        // case that the graph is disconnected in which case return null.
        if (path.size() != edgeCount + 1) return null;

        // Convert path linkedlist into array for convenience.
        int[] soln = new int[edgeCount + 1];
        for (int i = 0; !path.isEmpty(); i++) soln[i] = path.removeFirst();

        return soln;//return array fo path
    }

    public void setUp() {
        // Arrays that track the in degree and out degree of each node.
        in = new int[vertices];
        out = new int[vertices];
        edgeCount = 0;
        //Compute in and out degree
        for (int from = 0; from < vertices; from++) {//for each vertex in graph
            for (int to : graph.get(from)) {//for every neighbor this vertex has
                //since directed edge is inbound to neighbor and outbound source vertex
                in[to]++;//increase inDegree of neighbor
                out[from]++;//increase outDegree of source vertex
                edgeCount++;//number of edges in the graph increases
            }
        }
    }

    public boolean graphHasEulerianPath() {//checks conditions
        if (edgeCount == 0) return false;//if there are no edges in the graph --> false
        int startNodes = 0, endNodes = 0;//default values for start and end nodes
        for (int i = 0; i < vertices; i++) {//for every vertex
            //check conditions
            //if outDegree and inDegree differ by more than 1 --> false
            if (out[i] - in[i] > 1 || in[i] - out[i] > 1) return false;
            //if they differ by 1
            else if (out[i] - in[i] == 1) startNodes++;
            //if they differ by 1
            else if (in[i] - out[i] == 1) endNodes++;
        }
        //at most 1 node that has in and out degrees differing by 1
        //all other nodes should have in = out degrees
        return (endNodes == 0 && startNodes == 0) || (endNodes == 1 && startNodes == 1);
    }

    public int findStartNode() {
        int start = 0;//default start node value
        for (int i = 0; i < vertices; i++) {//for every vertex
            // Unique starting node.
            if (out[i] - in[i] == 1) return 1;
            // Start at a node with an outgoing edge.
            if (out[i] > 0) start = i;
        }
        return start;
    }

    // Perform DFS to find Eulerian path.
    public void dfs(int at) {
        while (out[at] != 0) {
            int next = graph.get(at).get(--out[at]);//all neighbors
            dfs(next);//explore neighbors and recurse
        }
        path.addFirst(at);//update path
    }

    /* Graph creation helper methods */
    public List<List<Integer>> createGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        return graph;
    }

    public void addDirectedEdge(List<List<Integer>> g, int from, int to) {
        g.get(from).add(to);
    }

    public void main() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int vertices = input.nextInt();
        List<List<Integer>> graph = createGraph(vertices);
        System.out.print("Enter number of edges: ");
        int edges = input.nextInt();
        System.out.println("Enter " + edges + " edges: ");
        for (int i = 0; i < edges; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            addDirectedEdge(graph, from, to);
        }
        EulerianPathDirectedGraph solver;
        solver = new EulerianPathDirectedGraph(graph);
        System.out.println(Arrays.toString(solver.getEulerianPath()));
    }
}

package com.company;

import java.util.*;

public class TarjanStronglyConnectedComponents {
    private int vertices;//num Nodes in graph
    private List<List<Integer>> graph;//adjacency list
    private boolean solved;//to check of we repeating cycle
    private int sccCount, id;//number of strongly connected components and id number of each node
    private boolean[] visited;//visited array
    private int[] ids, low, comps;//arrays storing id number, lowLinkValues, and stronglyConnectedComponents
    private Deque<Integer> stack;//empty stack will store nodes in current connected component
    private final int unvisited = -1;//if a node is unvisited, its id will be -1

    public TarjanStronglyConnectedComponents(List<List<Integer>> graph) {//constructor
        this.graph = graph;
        vertices = graph.size();
    }

    // Returns the number of strongly connected components in the graph.
    public int sccCount() {
        if (!solved) solve();
        return sccCount;
    }

    // Get the connected components of this graph. If two indexes
    // have the same value then they're in the same SCC.
    public int[] getComps() {
        if (!solved) solve();
        return comps;
    }

    public void solve() {
        if (solved) return;
        ids = new int[vertices];
        low = new int[vertices];
        comps = new int[vertices];
        visited = new boolean[vertices];
        stack = new ArrayDeque<>();
        Arrays.fill(ids, unvisited);//all nodes are initially unvisited
        for (int i = 0; i < vertices; i++) {//for every node
            if (ids[i] == unvisited) {//if it is unvisited
                dfs(i);//run a dfs to assign it an id and a lowLinkValue
            }
        }
        solved = true;//mark solved as true
    }

    public void dfs(int at) {//will traverse graph and assign unique ids and lowLinkValues
        ids[at] = low[at] = id++;//assign an id to a node which will increase by one for a new node
        //lowLinkValue will be the same as teh id number initially
        stack.push(at);//add the current node to stack
        visited[at] = true;//mark current node as visited

        for (int neighbor : graph.get(at)) {//for every neighbor the current node has
            if (ids[neighbor] == unvisited) {//if it is not visited --> recurse neighbor
                dfs(neighbor);//run a dfs and explore its neighbors
            }
            if (visited[neighbor]) {//if neighbor has been visited
                low[at] = Math.min(low[at], low[neighbor]);//update lowLinkValue
            }
        }
        if (ids[at] == low[at]) {//if id number of a node is the same as its lowLinkValue
            for (int node = stack.pop(); ; node = stack.pop()) {//for every node in the stack
                visited[node] = false;//mark node as unvisited
                comps[node] = sccCount;//assign number of sccs to comps array
                if (node == at) break;//base case
            }
            sccCount++;//increase number of strongly connected components by 1
        }
    }

    //Graph Helper Methods
    // Initializes adjacency list with n nodes.
    public static List<List<Integer>> createGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        return graph;
    }

    // Adds a directed edge from node 'from' to node 'to'
    public static void addEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
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
            int v1 = input.nextInt();
            int v2 = input.nextInt();
            addEdge(graph, v1, v2);
        }
        TarjanStronglyConnectedComponents solver = new TarjanStronglyConnectedComponents(graph);
        int[] sccs = solver.getComps();
        Map<Integer, List<Integer>> multimap = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            if (!multimap.containsKey(sccs[i])) multimap.put(sccs[i], new ArrayList<>());
            multimap.get(sccs[i]).add(i);
        }
        System.out.printf("Number of Strongly Connected Components: %d\n", solver.sccCount());
        for (List<Integer> scc : multimap.values()) {
            System.out.println("Nodes: " + scc + " form a Strongly Connected Component.");
        }
    }
}

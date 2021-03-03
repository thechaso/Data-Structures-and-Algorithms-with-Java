package com.company;

import java.awt.*;
import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    //adjacency list will contain all neighbours of a node
    private LinkedList<Integer> adj [];//an array consisting of linked lists

    public Graph(int v) {//constructor takes in number of vertices/nodes in graph
        adj = new LinkedList[v];//set size of adjacency list
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();//sets each element to a linked list
        }
    }

    //this method adds edge between source to dest and dest to source, making it undirected
    public void addEdge(int source, int destination) {
        adj[source].add(destination);//linked list at adj[source] will add destination
        adj[destination].add(source);//linked list at adj[destination] will add source
    }

    //recursive helper method takes node source, dest, and boolean array visited
    private boolean dfsUtil(int source, int destination, boolean[] vis) {
        if (source == destination) return true;//base case, if we are at dest --> true
        for (int neighbour: adj[source]) {//for each node at linked list adj[source]
            if (!vis[neighbour]) {//if node has not been visited
                vis[neighbour] = true;//mark it as visited
                boolean isConnected = dfsUtil(neighbour, destination, vis);//recurse
                if (isConnected) return true;//if has path --> true
            }
        }
        return false;//else false
    }

    public boolean hasPathDFS(int source, int destination) {
        boolean vis[] = new boolean[adj.length];//initialize visited array
        vis[source] = true;//we have already visited starting point
        return dfsUtil(source, destination, vis);//run recursive algorithm to return boolean
    }

    public boolean hasPathDFSStack(int source, int destination) {
        boolean vis[] = new boolean[adj.length];//visited array
        vis[source] = true;//we have already visited source
        Stack<Integer> stack = new Stack<>();//make a new stack
        stack.push(source);//add the source
        while (!stack.isEmpty()) {
            int cur = stack.pop();//current will be first element in stack which will remove
            if (cur == destination) return true;//base case, if current is a t dest --> true
            for (int neighbor: adj[cur]) {//for each node in linked list adj[current]
                if (!vis[neighbor]) {//if neighbour is not yet visited
                    vis[neighbor] = true;//mark it as visited
                    stack.push(neighbor);//add it to the stack
                }
            }
        }
        return false;//if no path --> false
    }

    private void dfsConnectedComponentsUtil(int start, boolean[] vis) {//param is the starting node
        java.util.Stack<Integer> stack = new java.util.Stack<>();//make blank stack
        stack.push(start);//add starting node to stack
        vis[start] = true;//mark the node as visited
        while(!stack.isEmpty()) {//while stack has elements inside
            int cur = stack.pop();//current will be first element in stack which will remove
            for(Integer neighbour: adj[cur]) {//for every neighbor the node has
                if(!vis[neighbour]) {//if it is not visited
                    stack.push(neighbour);//add it to the stack
                    vis[neighbour] = true;//mark it as visited
                }
            }
        }
    }

    //this method will return number of connected components
    public int getConnectedComponentsDFS(int nodes) {
        int numberOfConnectedComponent = 0;//starts at 0
        boolean[] vis = new boolean[adj.length];
        for(int i = 0; i < nodes; i++) {//for each node in graph
            if(!vis[i]) {//if node is not visited
                //dfs will tweak visited list
                dfsConnectedComponentsUtil(i, vis);
                //dfs(i);//call dfs method
                numberOfConnectedComponent++;//increase count
            }
        }

        return numberOfConnectedComponent;//return count
    }

    //this method will return shortest path
    public int shortestPathBFS(int source, int destination) {
        boolean vis[] = new boolean[adj.length];//make visited array
        int[] parent = new int[adj.length];//make parent array
        Queue<Integer> q = new LinkedList<>();//make a queue of linked lists in each element
        q.add(source);//add starting node to queue
        //if node has been passed: parent[node] = -1
        parent[source] = -1;//make source node in parent visited
        vis[source] = true;//we have already reached source node
        while (!q.isEmpty()) {
            int cur = q.poll();//first element of queue
            if (cur == destination) break;//base case if we are at dest --> break
            for (int neighbor: adj[cur]) {//iterate through linked list in adj[cur]
                if (!vis[neighbor]) {//if it is not visited
                    vis[neighbor] = true;//mark it visited
                    q.add(neighbor);//add neighbor to queue
                    parent[neighbor] = cur;//mark parent[neighbour] as first element of queue
                }
            }
        }
        int cur = destination;//make current equal to dest
        int distance = 0;//distance will obviously start with 0
        while (parent[cur] != -1) {//while current parent node is not visited
            System.out.print(cur + " --> ");// print path
            cur = parent[cur];//make current node equal to the next parent node
            distance++;//increase distance by 1
        }
        return distance;//return distance
    }

    public void getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter vertices and edges");
        int v = input.nextInt();
        int e = input.nextInt();
        Graph graph = new Graph(v);
        System.out.println("Enter " + e + " Edges");
        for (int i = 0; i < e; i++) {
            int source = input.nextInt();
            int destination = input.nextInt();
            graph.addEdge(source, destination);
        }
        System.out.println("Enter Source and Destination");
        int source = input.nextInt();
        int destination = input.nextInt();

        System.out.println("possible: " + graph.hasPathDFS(source, destination));
        System.out.println("shortest path: " + graph.shortestPathBFS(source,  destination));
        System.out.println("Number of connected Components: " + graph.getConnectedComponentsDFS(v));
    }
}

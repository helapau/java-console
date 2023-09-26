package com.company.ds.graphs;

import com.company.ds.LinkedList;
import com.company.ds.Stack;
import com.company.ds.stackAndQueue.Queue;

import java.util.HashSet;
import java.util.Set;

/**
 * Implement a graph using adjacency list
 */
public class AdjacencyList {
    int vertices; //Total number of vertices in graph
    Object[] arr;
    private boolean nodeWasAdded;

    public AdjacencyList(int vertices) {
        this.nodeWasAdded = false;
        this.vertices = vertices;
        this.arr = new Object[vertices];
        for (int i = 0; i < vertices; i++) {
            arr[i] = new LinkedList<Integer>();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("AdjacencyList of directed Graph\n");
        for (int i = 0; i < vertices; i++) {
            LinkedList<Integer> nodes = (LinkedList<Integer>) arr[i];
            LinkedList.Node node = nodes.head;
            result.append("|");
            result.append(i);
            result.append("| => ");
            while (node != null) {
                result.append("[");
                result.append(node.data);
                result.append("] -> ");
                node = node.next;
            }
            result.append("null\n");
        }
        return result.toString();
    }

    public void printGraph() {
        System.out.println(this.toString());
    }

    public void addEdge(int source, int destination) {
        if (!nodeWasAdded) {
            nodeWasAdded = true;
        }
        LinkedList<Integer> sourceVertex = (LinkedList<Integer>) this.arr[source];
        sourceVertex.insertAtEnd(destination);
        //for undirected graph uncomment the lines below
//        LinkedList<Integer> destinationVertex = (LinkedList<Integer>) this.arr[destination];
//        destinationVertex.insertAtEnd(source);
    }

    public boolean isEmpty() {
        return !nodeWasAdded;
    }

    private static String bfsVisit(AdjacencyList graph, int node, boolean[] visited) {
        String result = "";
        // create queue for BFS and enque starting node for this level
        Queue<Integer> q = new Queue<>(graph.vertices);
        q.enqueue(node);
        // mark the start node for this level as visited
        visited[node] = true;

        // writing to result - while queue is not empty
        while (!q.isEmpty()) {
            int currentNode = q.dequeue();
            result += String.valueOf(currentNode);

            // get adjacent nodes to currentNode from and if they're not visited, enqueue them
            LinkedList<Integer> adjNodes = null;
            if (graph.arr[currentNode] != null) {
                adjNodes = (LinkedList<Integer>) graph.arr[currentNode];
            }

            LinkedList.Node currentAdjNode = adjNodes.head;
            while (currentAdjNode != null) {
                if (!visited[(Integer) currentAdjNode.data]) {
                    q.enqueue((Integer) currentAdjNode.data);
                    visited[(Integer) currentAdjNode.data] = true;
                }
                currentAdjNode = currentAdjNode.next;
            }
        }
        return result;
    }

    public String bfs() {
        String result = "";
        if (arr.length < 1) return result;

        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                // use wrapper function to make sure even disconnected vertices
                LinkedList<Integer> level = (LinkedList<Integer>) this.arr[i];
                LinkedList.Node source = level.head;
                result += bfsVisit(this, (Integer) source.data, visited);
            }
        }
        return result;
    }


    public String dfs() {
        String result = "";
        if (isEmpty()) return result;
        boolean[] visited = new boolean[this.vertices];
        Stack<Integer> stack = new Stack<>(this.vertices);

        for (int i = 0; i < this.vertices; i++) {
            if (!visited[i]) {
                stack.push(i);
                result += i;
                visited[i] = true;
            }
            while (!stack.isEmpty()) {
                // does the top of stack has any unvisited adjacent nodes?
                LinkedList<Integer> level = (LinkedList<Integer>) this.arr[stack.top()];
                LinkedList.Node node = level.head;
                // find 1st unvisited adjacent node
                while (node != null && visited[(Integer) node.data]) {
                    node = node.next;
                }
                if (node != null) {
                    stack.push((Integer) node.data);
                    result += (Integer) node.data;
                    visited[(Integer) node.data] = true;
                } else {
                    stack.pop();
                }
            }
        }
        return result;
    }
}

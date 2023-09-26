package com.company;

import com.company.ds.graphs.AdjacencyList;
import com.company.ds.stackAndQueue.Queue;

import java.util.Arrays;

public class Main {




    public static void main(String[] args) {
        AdjacencyList g = new AdjacencyList(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(5, 6);
        g.printGraph();

        String dfs = g.dfs();
        System.out.println(dfs);




    }
}

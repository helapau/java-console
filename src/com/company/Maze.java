package com.company;

import java.util.LinkedList;

public class Maze {
    public static final int DESTINATION = 2;
    public static final int WALL = 0;
    public static final int PATH = 1;

    int[][] maze;
    LinkedList<Position> steps = new LinkedList<>();
    Position start;
}

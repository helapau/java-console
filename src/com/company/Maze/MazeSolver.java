package com.company.Maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

import static com.company.Maze.Maze.*;

public class MazeSolver {

    private int[] convertStringArrToInt(String[] arrS) {
        int[] arrI = new int[arrS.length];
        for (int i = 0; i < arrS.length; i++) {
            arrI[i] = Integer.parseInt(arrS[i]);
        }
        return arrI;
    }

    private Maze readMaze(Scanner in) {
        int rows = Integer.parseInt(in.nextLine());
        int[][] maze = new int[rows][];
        for (int i = 0; i < rows; i++) {
            maze[i] = convertStringArrToInt(in.nextLine().split(", "));
        }
        int startRow = Integer.parseInt(in.nextLine());
        int startCol = Integer.parseInt(in.nextLine());
        Position start = new Position(startRow, startCol);
        Maze m = new Maze();
        m.maze = maze;
        m.start = start;
        return m;
    }

    public List<Maze> getMazes()  throws FileNotFoundException {
        List<Maze> mazes = new ArrayList<>();
        String cwd = Paths.get("").toAbsolutePath().toString();
        String filePath = cwd + "/src/com/company/mazes.txt";
        Scanner in = new Scanner(new File(filePath));

        while (true) {
            mazes.add(readMaze(in));
            if (in.hasNextLine()) {
                in.nextLine();
            } else {
                break;
            }
        }
        // read mazes from text file
        // maze declaration starts with number of rows
        // if line has values separated by comma+space --> maze
        // only int at line -> coordinate of the starting position
        return mazes;
    }


    private int[][] copyMaze(int[][] maze) {
        int[][] copy = new int[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            int[] row = maze[i];
            copy[i] = new int[row.length];
            System.arraycopy(row, 0, copy[i], 0, row.length);
        }
        return copy;
    }

    private boolean isMazeIndexValid(Position p, int[][] maze) {
        int rowIdx = p.rowIndex;
        int colIdx = p.columnIndex;
        return rowIdx >= 0 && colIdx >= 0 && rowIdx <= maze.length - 1 && colIdx <= maze[rowIdx].length - 1;
    }

    public boolean solve(Maze maze) {
        // todo challenge: modify so that only the correct path is printed (so no "go back" and anything before that)
        // steps is a stack data structure
        int[][] mazeCopy = copyMaze(maze.maze);
        maze.steps.push(maze.start);

        Map<String, Function<Position, Position>> directionToNextPosition = new HashMap<>();
        directionToNextPosition.put("down", currentP -> new Position(currentP.rowIndex + 1, currentP.columnIndex));
        directionToNextPosition.put("left", currentP -> new Position(currentP.rowIndex, currentP.columnIndex - 1));
        directionToNextPosition.put("up", currentP -> new Position(currentP.rowIndex - 1, currentP.columnIndex));
        directionToNextPosition.put("right", currentP -> new Position(currentP.rowIndex, currentP.columnIndex + 1));

        while (true) {
            Position current = maze.steps.peek();
            int row = current.rowIndex;
            int col = current.columnIndex;

            mazeCopy[row][col] = WALL;
            boolean moved = false;
            for (String direction : directionToNextPosition.keySet()) {

                Function<Position, Position> getNext = directionToNextPosition.get(direction);
                Position next = getNext.apply(current);
                int nextRow = next.rowIndex;
                int nextCol = next.columnIndex;
                if (isMazeIndexValid(next, mazeCopy)) {
                    if (mazeCopy[nextRow][nextCol] == DESTINATION) {
                        System.out.println("Moved " + direction);
                        return true;
                    } else if (mazeCopy[nextRow][nextCol] == PATH) {
                        maze.steps.push(next);
                        System.out.println("Moved " + direction);
                        moved = true;
                        break;
                    }
                }
            }
            if (!moved) {
                maze.steps.pop();
                System.out.println("Going back");
                if (maze.steps.size() == 0) {
                    return false;
                }
            }

        }
    }

    public static void test() throws FileNotFoundException {
        MazeSolver mazeSolver = new MazeSolver();
        List<Maze> mazes = mazeSolver.getMazes();
        for (Maze maze : mazes) {
            System.out.println("Solving new maze: ");
            boolean foundPath = mazeSolver.solve(maze);
            if (foundPath) {
                System.out.println("and reached the destination");
            } else {
                System.out.println("No path");
            }
        }
        System.out.println();
    }

}

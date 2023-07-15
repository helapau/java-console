package com.company;

public class Position {
    public final int rowIndex;
    public final int columnIndex;

    public Position(int i, int j) {
        rowIndex = i;
        columnIndex = j;
    }

    @Override
    public String toString() {
        return "Position{" +
                "rowIndex=" + rowIndex +
                ", columnIndex=" + columnIndex +
                '}';
    }
}

package com.wadhavekar.tictactoe;

public class PlayerTurn {
    int row,column,turn;

    public PlayerTurn(int row, int column, int turn) {
        this.row = row;
        this.column = column;
        this.turn = turn;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getTurn() {
        return turn;
    }
}

package com.wadhavekar.tictactoe;

public class PossibleWinner {
    int rowWinner,colWinner;

    public PossibleWinner(int rowWinner, int colWinner) {
        this.rowWinner = rowWinner;
        this.colWinner = colWinner;
    }

    public int getRowWinner() {
        return rowWinner;
    }

    public int getColWinner() {
        return colWinner;
    }
}

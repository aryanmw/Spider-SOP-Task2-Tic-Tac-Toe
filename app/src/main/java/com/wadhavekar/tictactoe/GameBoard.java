package com.wadhavekar.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class GameBoard extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private boolean[][] cellChecked;
    ArrayList<PlayerTurn> playerTurnGame;
    boolean gameOver = false;
    int turn = 0;
    String winner;

    Bitmap cross = BitmapFactory.decodeResource(getResources(),R.drawable.cross);
    Bitmap nut = BitmapFactory.decodeResource(getResources(),R.drawable.nut);

    public GameBoard(Context context) {
        this(context, null);
    }

    public GameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;

        cellChecked = new boolean[numColumns][numRows];
        playerTurnGame = new ArrayList<>();

        for (int i = 0 ; i <numColumns ; i++){
            for (int j = 0 ; j < numRows ; j++){

                cellChecked[i][j] = false;
            }
        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable d = getResources().getDrawable(R.drawable.board,null);
        d.draw(canvas);
        //canvas.drawColor(Color.WHITE);


        Paint pt = new Paint();
        pt.setAntiAlias(true);
        pt.setColor(Color.WHITE);
        pt.setStrokeCap(Paint.Cap.ROUND);
        pt.setStrokeJoin(Paint.Join.ROUND);
        pt.setStrokeWidth(30f);

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeWidth(20f);

        Paint pB = new Paint();
        pB.setAntiAlias(true);
        pB.setColor(Color.WHITE);
        pB.setStrokeCap(Paint.Cap.ROUND);
        pB.setStrokeJoin(Paint.Join.ROUND);
        pB.setStrokeWidth(30f);

        Paint pW = new Paint();
        pW.setAntiAlias(true);
        pW.setColor(Color.parseColor("#2D804C"));
        pW.setStrokeCap(Paint.Cap.ROUND);
        pW.setStrokeJoin(Paint.Join.ROUND);
        pW.setStrokeWidth(30f);

        int width = getWidth();
        int height = getHeight();

        int buffer = 45;

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (gameOver == false) {
                    if (cellChecked[i][j]) {

                        int index = searchInPlayerTurnGameArrayList(i,j);

                        if (playerTurnGame.get(index).getTurn() == 1 ) {
                            canvas.drawLine((i * cellWidth) + buffer, (j * cellHeight) +buffer,
                                    ((i + 1) * cellWidth) - buffer, ((j + 1) * cellHeight)-buffer,
                                    pt);
                            canvas.drawLine((i * cellWidth) + buffer, ((j + 1) * cellHeight)-buffer,
                                    ((i + 1) * cellHeight)-buffer, (j * cellHeight) +buffer,
                                    pt);
    //
                        }

                       else {
                            canvas.drawCircle((i*cellWidth)+(cellWidth/2), (j*cellHeight)+(cellHeight/2), (cellHeight/2)-25, pB);
                            canvas.drawCircle((i*cellWidth)+(cellWidth/2), (j*cellHeight)+(cellHeight/2), (cellHeight/2)-55, pW);
    //
                        }


                    }
                }
            }
        }

        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, p);
        }

        for (int i = 1; i < numRows; i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, p);
        }


            for (int j = 0 ; j < numColumns ; j++){
                if (cellChecked[0][j] && cellChecked[1][j] && cellChecked[2][j]){
                    if (playerTurnGame.get(searchInPlayerTurnGameArrayList(0,j)).getTurn() == 1 && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,j)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(1,j)).getTurn() && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,j)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(2,j)).getTurn()){
                        //Toast.makeText(getContext(), "Winnerrr XX", Toast.LENGTH_SHORT).show();
                        winner = "X";
                        gameOver = true;
                    }
                    if (playerTurnGame.get(searchInPlayerTurnGameArrayList(0,j)).getTurn() == 2 && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,j)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(1,j)).getTurn() && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,j)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(2,j)).getTurn()){
                        //Toast.makeText(getContext(), "Winnerrr OO", Toast.LENGTH_SHORT).show();
                        winner = "O";
                        gameOver = true;
                    }
                }
            }

        for (int j = 0 ; j < numColumns ; j++){
            if (cellChecked[j][0] && cellChecked[j][1] && cellChecked[j][2]){
                if ( playerTurnGame.get(searchInPlayerTurnGameArrayList(j,0)).getTurn() == 1 && playerTurnGame.get(searchInPlayerTurnGameArrayList(j,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(j,1)).getTurn() && playerTurnGame.get(searchInPlayerTurnGameArrayList(j,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(j,2)).getTurn()){
                   // Toast.makeText(getContext(), "WinnerrrWInneerr XX", Toast.LENGTH_SHORT).show();
                    winner = "X";
                    gameOver = true;
                }
                if ( playerTurnGame.get(searchInPlayerTurnGameArrayList(j,0)).getTurn() == 2 && playerTurnGame.get(searchInPlayerTurnGameArrayList(j,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(j,1)).getTurn() && playerTurnGame.get(searchInPlayerTurnGameArrayList(j,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(j,2)).getTurn()){
                    //Toast.makeText(getContext(), "WinnerrrWInneerr OO", Toast.LENGTH_SHORT).show();
                    winner = "O";
                    gameOver = true;
                }
            }
        }

        if (cellChecked[0][0] && cellChecked[1][1] && cellChecked[2][2]){
            if (playerTurnGame.get(searchInPlayerTurnGameArrayList(0,0)).getTurn() == 1 && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(1,1)).getTurn() && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(2,2)).getTurn()){
                //Toast.makeText(getContext(), "Cross winner XX", Toast.LENGTH_SHORT).show();
                winner = "X";
                gameOver = true;
            }
            if (playerTurnGame.get(searchInPlayerTurnGameArrayList(0,0)).getTurn() == 2 && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(1,1)).getTurn() && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(2,2)).getTurn()){
                //Toast.makeText(getContext(), "Cross winner OO", Toast.LENGTH_SHORT).show();
                winner = "O";
                gameOver = true;
            }
        }
        if (cellChecked[2][0] && cellChecked[1][1] && cellChecked[0][2]){
            if (playerTurnGame.get(searchInPlayerTurnGameArrayList(2,0)).getTurn() == 1 && playerTurnGame.get(searchInPlayerTurnGameArrayList(2,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(1,1)).getTurn() && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,2)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(2,0)).getTurn()){
                //Toast.makeText(getContext(), "Counter Cross winner XX", Toast.LENGTH_SHORT).show();
                winner = "X";
                gameOver = true;
            }
            if (playerTurnGame.get(searchInPlayerTurnGameArrayList(2,0)).getTurn() == 2 && playerTurnGame.get(searchInPlayerTurnGameArrayList(2,0)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(1,1)).getTurn() && playerTurnGame.get(searchInPlayerTurnGameArrayList(0,2)).getTurn() == playerTurnGame.get(searchInPlayerTurnGameArrayList(2,0)).getTurn()){
                //Toast.makeText(getContext(), "Counter Cross winner OO", Toast.LENGTH_SHORT).show();
                winner = "O";
                gameOver = true;
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int row = (int)(event.getX() / cellWidth);
            int column = (int)(event.getY() / cellHeight);


            if (cellChecked[row][column] == false) {
                cellChecked[row][column] = !cellChecked[row][column];
                if (playerTurnGame.size() == 0){
                    PlayerTurn playerTurn = new PlayerTurn(row,column,1);
                    playerTurnGame.add(playerTurn);
                }
                else{
                    if (playerTurnGame.get(playerTurnGame.size()-1).getTurn() == 1){
                        PlayerTurn playerTurn = new PlayerTurn(row,column,2);
                        playerTurnGame.add(playerTurn);
                    }
                    else{
                        PlayerTurn playerTurn = new PlayerTurn(row,column,1);
                        playerTurnGame.add(playerTurn);
                    }
                }
            }
            if (gameOver == false) {
                invalidate();
            }
        }

        return true;
    }

    private int searchInPlayerTurnGameArrayList(int row, int column){
        for (int i = 0 ; i < playerTurnGame.size() ; i++){
            if (playerTurnGame.get(i).getRow() == row && playerTurnGame.get(i).getColumn() == column){
                return i;
            }
        }
        return -1;
    }

    public boolean getIfGameOver(){
        return gameOver;
    }

    public boolean cellsFilled(){
        int count = 0;
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                if (cellChecked[i][j]){
                    count++;
                }
            }
        }
        if (count == 9 && !gameOver){
            return true;
        }
        return false;
    }
    public String getWinner(){
        return winner;
    }
}
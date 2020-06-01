package com.wadhavekar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    GameBoardForComputer gameBoard;
    GameBoard gBoard;
    boolean kill = false;
    SoundPlayer sound;
    TextView playerX,playerO;
    int countPlayerX = 0,countPlayerO = 0;
    private static final String SHARED_PREFS = "RPS";
    private static final String SCOREX = "Score for X";
    private static final String SCOREO = "Score for O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound = new SoundPlayer(this);
        gBoard = new GameBoard(this);
        gameBoard = new GameBoardForComputer(this);
        gameBoard.setNumColumns(3);
        gameBoard.setNumRows(3);
        gBoard.setNumColumns(3);
        gBoard.setNumRows(3);
        linearLayout = findViewById(R.id.gameBoard_LinearLayout);
        if (getIntent().getIntExtra("myval",0) == 0){
            linearLayout.addView(gBoard);
        }
        else {
            linearLayout.addView(gameBoard);
        }
        playerX = findViewById(R.id.tv_playerX);
        playerX.setText(getIntent().getStringExtra("PlayerX") + ": X");
        playerO = findViewById(R.id.tv_playerO);
        playerO.setText(getIntent().getStringExtra("PlayerO") + ": O");

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        countPlayerX = sp.getInt(SCOREX,0);
        countPlayerO = sp.getInt(SCOREO,0);





        gameDone();
        checkIfDraw();
        //Toast.makeText(this, ""+kill, Toast.LENGTH_SHORT).show();

    }


    public void gameDone(){
        String gameWinner;
        if (getIntent().getIntExtra("myval",0) == 0) {
            gameWinner = gBoard.getWinner();
            kill = gBoard.getIfGameOver();
        } else {
            gameWinner = gameBoard.getWinner();
            kill = gameBoard.getIfGameOver();
        }
        if (kill && gameWinner.equals("X")){
            sound.playXSound();
            countPlayerX++;
            saveScoreX(countPlayerX);
            Intent intent = new Intent(MainActivity.this,Scores.class);
            intent.putExtra("winner",getIntent().getStringExtra("PlayerX"));
            startActivity(intent);
        }
        else if (kill && gameWinner.equals("O")){
            sound.playOSound();
            countPlayerO++;
            saveScoreO(countPlayerO);
            Intent intent = new Intent(MainActivity.this,Scores.class);
            intent.putExtra("winner",getIntent().getStringExtra("PlayerO"));
            startActivity(intent);
        }

        else{
            refresh(1000);
        }

    }
    private void checkIfDraw(){
        boolean draw;
        if (getIntent().getIntExtra("myval",0) == 0){
            draw = gBoard.cellsFilled();
        }
        else{
            draw = gameBoard.cellsFilled();
        }
        if (draw){
            kill = true;
            sound.draw();
            Intent intent = new Intent(MainActivity.this,Scores.class);
            startActivity(intent);
           // Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();

        }
        //refresh(1000);
    }
    public void refresh(int milliseconds){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                content();
//                colourPlayers();
                checkIfDraw();
                gameDone();

            }
        };


        handler.postDelayed(runnable,milliseconds);
        if (kill){

            handler.removeCallbacks(runnable);
        }


    }
    public void saveScoreX (int num){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SCOREX,num);
        editor.apply();
        //Log.i("SP","??????????"+sharedPreferences);
    }
    public void saveScoreO (int num){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SCOREO,num);
        editor.apply();
        //Log.i("SP","??????????"+sharedPreferences);
    }
}

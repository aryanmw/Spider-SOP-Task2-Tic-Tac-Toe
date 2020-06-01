package com.wadhavekar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Scores extends AppCompatActivity {
    ArrayList<MyScore> myScore;
    private static final String SHARED_PREFS = "RPS";
    private static final String SCOREX = "Score for X";
    private static final String SCOREO = "Score for O";
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    TextView tv_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        tv_winner = findViewById(R.id.tv_winner);

        tv_winner.setText(getIntent().getStringExtra("winner") + " won the game");



        SharedPreferences sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        myScore = new ArrayList<>();
        myScore.add(new MyScore("Player O",sp.getInt(SCOREO,0)));
        myScore.add(new MyScore("Player X",sp.getInt(SCOREX,0)));

        Toast.makeText(this, ""+myScore.size(), Toast.LENGTH_SHORT).show();


        adapter = new RecyclerViewAdapter(myScore);
        recyclerView = findViewById(R.id.rvMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(Scores.this));
        recyclerView.setAdapter(adapter);



    }
}

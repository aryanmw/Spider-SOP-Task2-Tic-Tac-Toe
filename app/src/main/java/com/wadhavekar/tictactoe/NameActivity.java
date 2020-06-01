package com.wadhavekar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {
    TextView player1,player2;
    EditText etPlayer1,etPlayer2;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        done = findViewById(R.id.button_done);
        player2 = findViewById(R.id.tv_player2);
        etPlayer2 = findViewById(R.id.et_player2Name);
        etPlayer1 = findViewById(R.id.et_player1Name);
        final Intent intent = new Intent(NameActivity.this,MainActivity.class);
        if (getIntent().getIntExtra("value",0) == 0){
            player2.setVisibility(View.VISIBLE);
            etPlayer2.setVisibility(View.VISIBLE);
            intent.putExtra("myval",0);
        }
        else{
            intent.putExtra("myval",1);
        }




            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etPlayer1.getText().toString().equals("") && etPlayer2.getText().toString().equals("")){
                        Toast.makeText(NameActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        int randomNumber = (int) (Math.random()%2) + 1;
                        if (randomNumber == 1){
                            if (getIntent().getIntExtra("value",0) == 1){
                                intent.putExtra("PlayerO","Computer");
                                intent.putExtra("PlayerX",etPlayer1.getText().toString());
                            }
                            else{
                                intent.putExtra("PlayerX",etPlayer2.getText().toString());
                                intent.putExtra("PlayerO",etPlayer1.getText().toString());
                            }
                        }
                        else{
                            if (getIntent().getIntExtra("value",0) == 1){
                                intent.putExtra("PlayerO","Computer");
                                intent.putExtra("PlayerX",etPlayer1.getText().toString());
                            }
                            else{
                                intent.putExtra("PlayerO",etPlayer2.getText().toString());
                                intent.putExtra("PlayerX",etPlayer1.getText().toString());
                            }
                        }
                        startActivity(intent);
                    }

                }
            });

    }
}

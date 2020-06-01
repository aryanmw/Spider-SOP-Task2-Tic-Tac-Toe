package com.wadhavekar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InitialScreen extends AppCompatActivity {
    TextView frnd,comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);
        frnd = findViewById(R.id.tv_vFrnd);
        comp = findViewById(R.id.tv_vComp);
        frnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialScreen.this,NameActivity.class);
                intent.putExtra("value",0);
                startActivity(intent);
            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialScreen.this,NameActivity.class);
                intent.putExtra("value",1);
                startActivity(intent);
            }
        });
    }
}

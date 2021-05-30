package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Difficulty_Section extends AppCompatActivity {
    Button btnEasy,btnIntermediate,btnAdvanced,btnChallenging;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);

        btnEasy = findViewById(R.id.btnEasy);
        btnIntermediate = findViewById(R.id.btnIntermediate);
        btnAdvanced = findViewById(R.id.btnAdvanced);
        btnChallenging = findViewById(R.id.btnChallenging);


        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Difficulty_Section.this,Easy_Mode.class);
                startActivity(i);
            }
        });
        btnIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Difficulty_Section.this,Intermediate_Mode.class);
                startActivity(i);
            }
        });
        btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Difficulty_Section.this,Advance_Mode.class);
                startActivity(i);
            }
        });
    }
}
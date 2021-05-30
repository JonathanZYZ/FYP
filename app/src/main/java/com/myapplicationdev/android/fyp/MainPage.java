package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {
    Button btnStart,btnRevision,btnHowToPlay,btnSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);
        btnRevision = findViewById(R.id.btnRevisionSection);
        btnSettings = findViewById(R.id.btnSettings);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPage.this,Difficulty_Section.class);
                startActivity(i);
            }
        });

    }
}
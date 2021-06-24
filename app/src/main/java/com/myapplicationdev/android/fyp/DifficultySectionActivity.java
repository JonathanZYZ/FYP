package com.myapplicationdev.android.fyp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DifficultySectionActivity extends AppCompatActivity {
    Button btnEasy,btnIntermediate,btnAdvanced,btnBack;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);

        btnEasy = findViewById(R.id.btnEasy);
        btnIntermediate = findViewById(R.id.btnIntermediate);
        btnAdvanced = findViewById(R.id.btnAdvanced);
        btnBack = findViewById(R.id.btnBack);
        mediaPlayer = MediaPlayer.create(DifficultySectionActivity.this,R.raw.mouse_click);

        btnEasy.setOnClickListener(view -> {
            Intent i = new Intent(DifficultySectionActivity.this, EasyModeActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnIntermediate.setOnClickListener(view -> {
            Intent i = new Intent(DifficultySectionActivity.this, IntermediateModeActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnAdvanced.setOnClickListener(view -> {
            Intent i = new Intent(DifficultySectionActivity.this, AdvancedModeActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(DifficultySectionActivity.this, MainActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

    }
}
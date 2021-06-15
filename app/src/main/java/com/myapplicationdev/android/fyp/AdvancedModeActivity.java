package com.myapplicationdev.android.fyp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdvancedModeActivity extends AppCompatActivity {
    Button btnStart;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_mode);
        btnStart = findViewById(R.id.btnStartAdvanced);
        mediaPlayer = MediaPlayer.create(AdvancedModeActivity.this,R.raw.mouse_click);
        btnStart.setOnClickListener(view -> {
            Intent i = new Intent(AdvancedModeActivity.this, StartQuizActivity.class);
            i.putExtra("mode","advanced");
            startActivity(i);
            mediaPlayer.start();
        });
    }
}
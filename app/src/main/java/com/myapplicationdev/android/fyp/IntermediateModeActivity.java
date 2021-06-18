package com.myapplicationdev.android.fyp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntermediateModeActivity extends AppCompatActivity {
    Button btnStart;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_mode);
        btnStart = findViewById(R.id.btnStartIntermediate);
        mediaPlayer = MediaPlayer.create(IntermediateModeActivity.this, R.raw.mouse_click);
        btnStart.setOnClickListener(view -> {
            Intent i = new Intent(IntermediateModeActivity.this, StartQuizActivity.class);
            i.putExtra("mode", "intermediate");
            startActivity(i);
            mediaPlayer.start();
        });
    }
}
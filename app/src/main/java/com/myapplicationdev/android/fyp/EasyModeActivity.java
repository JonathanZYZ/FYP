package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

public class EasyModeActivity extends AppCompatActivity {
    Button btnStart;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy__mode);
        btnStart = findViewById(R.id.btnStartEasy);
        mediaPlayer = MediaPlayer.create(EasyModeActivity.this,R.raw.mouse_click);
        btnStart.setOnClickListener(view -> {
            Intent i = new Intent(EasyModeActivity.this, StartQuizActivity.class);
            i.putExtra("mode","easy");
            startActivity(i);
            mediaPlayer.start();
        });
    }
}
package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Advance_Mode extends AppCompatActivity {
    Button btnStart;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance__mode);
        btnStart = findViewById(R.id.btnStartAdvanced);
        mediaPlayer = MediaPlayer.create(Advance_Mode.this,R.raw.mouse_click);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Advance_Mode.this,Start_Quiz.class);
                i.putExtra("mode","advanced");
                startActivity(i);
                mediaPlayer.start();
            }
        });
    }
}
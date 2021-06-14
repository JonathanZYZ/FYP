package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnStart,btnRevision,btnHowToPlay,btnSettings;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.Play);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);
        btnRevision = findViewById(R.id.btnRevisionSection);
        btnSettings = findViewById(R.id.btnSettings);
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.mouse_click);

        btnStart.setOnClickListener(view -> {
            Log.d("test","test");
            Intent i = new Intent(MainActivity.this,Difficulty_Section.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnHowToPlay.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,HowToPlay.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnRevision.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,RevisionSection.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnSettings.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,SettingsPage.class);
            startActivity(i);
            mediaPlayer.start();
        });
    }
}
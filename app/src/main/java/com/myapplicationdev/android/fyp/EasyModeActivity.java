package com.myapplicationdev.android.fyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EasyModeActivity extends AppCompatActivity {
    Button btnStart;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_mode);
        btnStart = findViewById(R.id.btnStartEasy);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Boolean sound = sharedPreferences.getBoolean("sound",true);
        if(sound==true){
            mediaPlayer = MediaPlayer.create(EasyModeActivity.this, R.raw.mouse_click);
        } else {
            mediaPlayer = new MediaPlayer();
        }

        btnStart.setOnClickListener(view -> {
            Intent i = new Intent(EasyModeActivity.this, StartQuizActivity.class);
            i.putExtra("mode","easy");
            startActivity(i);
            mediaPlayer.start();
        });
    }
}
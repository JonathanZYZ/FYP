package com.myapplicationdev.android.fyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Boolean sound = sharedPreferences.getBoolean("sound",true);
        if(sound==true){
            mediaPlayer = MediaPlayer.create(IntermediateModeActivity.this, R.raw.mouse_click);
        } else {
            mediaPlayer = new MediaPlayer();
        }
        btnStart.setOnClickListener(view -> {
            Intent i = new Intent(IntermediateModeActivity.this, StartQuizActivity.class);
            i.putExtra("mode", "intermediate");
            startActivity(i);
            mediaPlayer.start();
        });
    }
}
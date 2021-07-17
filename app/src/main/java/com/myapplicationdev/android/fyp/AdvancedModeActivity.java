package com.myapplicationdev.android.fyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdvancedModeActivity extends AppCompatActivity {
    Button btnStart;
    MediaPlayer mediaPlayer;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_advanced_mode);
        btnStart = findViewById(R.id.btnStartAdvanced);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Boolean sound = sharedPreferences.getBoolean("sound", true);

        if (sound) {
            mediaPlayer = MediaPlayer.create(AdvancedModeActivity.this, R.raw.mouse_click);
        } else {
            mediaPlayer = new MediaPlayer();
        }

        btnStart.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(AdvancedModeActivity.this, StartQuizActivity.class);
            i.putExtra("mode", "advanced");
            startActivity(i);
            mediaPlayer.start();
        });


    }
}
package com.myapplicationdev.android.fyp.Modes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.StartQuizActivity;

public class AdvancedModeActivity extends AppCompatActivity {
    Button btnStart;
    MediaPlayer mediaPlayer;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_mode);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnStart = findViewById(R.id.btnStartAdvanced);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        SharedPreferences sharedPreferences = getSharedPreferences("audio", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("sound") && sharedPreferences.contains("music")) {
            int sound = sharedPreferences.getInt("sound", 0);
            if (sound == 0) {
                mediaPlayer = new MediaPlayer();
            } else {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mouse_click);
            }
        } else {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mouse_click);
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
package com.myapplicationdev.android.fyp.Modes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.StartQuizActivity;

public class IntermediateModeActivity extends AppCompatActivity {
    Button btnStart;
    MediaPlayer mediaPlayer;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_mode);

        btnStart = findViewById(R.id.btnStartIntermediate);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean sound = sharedPreferences.getBoolean("sound", true);

        if (sound) {
            mediaPlayer = MediaPlayer.create(IntermediateModeActivity.this, R.raw.mouse_click);
        } else {
            mediaPlayer = new MediaPlayer();
        }

        btnStart.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(IntermediateModeActivity.this, StartQuizActivity.class);
            i.putExtra("mode", "intermediate");
            startActivity(i);
            mediaPlayer.start();
        });
    }
}
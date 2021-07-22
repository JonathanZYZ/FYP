package com.myapplicationdev.android.fyp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.myapplicationdev.android.fyp.Modes.AdvancedModeActivity;
import com.myapplicationdev.android.fyp.Modes.EasyModeActivity;
import com.myapplicationdev.android.fyp.Modes.IntermediateModeActivity;

public class DifficultySectionActivity extends AppCompatActivity {
    Button btnEasy, btnIntermediate, btnAdvanced, btnBack;
    MediaPlayer mediaPlayer;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);

        btnEasy = findViewById(R.id.btnEasy);
        btnIntermediate = findViewById(R.id.btnIntermediate);
        btnAdvanced = findViewById(R.id.btnAdvanced);
        btnBack = findViewById(R.id.btnBack);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean sound = sharedPreferences.getBoolean("sound", true);

        if (sound) {
            mediaPlayer = MediaPlayer.create(DifficultySectionActivity.this, R.raw.mouse_click);
        } else {
            mediaPlayer = new MediaPlayer();
        }

        btnEasy.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(DifficultySectionActivity.this, EasyModeActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnIntermediate.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(DifficultySectionActivity.this, IntermediateModeActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnAdvanced.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(DifficultySectionActivity.this, AdvancedModeActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnBack.setOnClickListener(view -> {
            v.vibrate(50);
            finish();
        });

    }
}
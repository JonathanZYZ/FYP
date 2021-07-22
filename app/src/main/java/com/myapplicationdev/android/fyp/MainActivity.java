package com.myapplicationdev.android.fyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Scoreboards.ShowScoreboardActivity;


public class MainActivity extends AppCompatActivity {
    Button btnStart, btnRevision, btnHowToPlay, btnSettings, btnLeaderBoard;
    MediaPlayer mediaPlayer, backgroundMusic;
    Vibrator v;
    int LAUNCH_SETTINGS_PAGE = 1;
    SharedPreferences sharedPreferences;
    int music, sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.Play);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);
        btnRevision = findViewById(R.id.btnRevisionSection);
        btnSettings = findViewById(R.id.btnSettings);
        btnLeaderBoard = findViewById(R.id.btnLeaderBoard);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        sharedPreferences = getSharedPreferences("audio", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("sound") && sharedPreferences.contains("music")) {
            music = sharedPreferences.getInt("music", 0);
            sound = sharedPreferences.getInt("sound", 0);
            if (music == 0) {
                backgroundMusic = new MediaPlayer();
            } else {
                backgroundMusic = MediaPlayer.create(MainActivity.this, R.raw.background_music);
                backgroundMusic.start();
                backgroundMusic.setLooping(true);
            }
            if (sound == 0) {
                mediaPlayer = new MediaPlayer();
            } else {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mouse_click);
            }
        } else {
            backgroundMusic = MediaPlayer.create(MainActivity.this, R.raw.background_music);
            backgroundMusic.start();
            backgroundMusic.setLooping(true);
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mouse_click);
        }


        btnStart.setOnClickListener(view -> {
            //    Log.d("test", "test");
            v.vibrate(50);
            Intent i = new Intent(MainActivity.this, DifficultySectionActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnHowToPlay.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(MainActivity.this, HowToPlayActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnRevision.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(MainActivity.this, RevisionSectionActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnSettings.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(MainActivity.this, SettingPageActivity.class);
            startActivityForResult(i, LAUNCH_SETTINGS_PAGE);
            mediaPlayer.start();
        });


        btnLeaderBoard.setOnClickListener(view -> {
            v.vibrate(50);
            Intent i = new Intent(MainActivity.this, ShowScoreboardActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Are you sure you want to exit?");
        alertDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
            backgroundMusic.stop();
            finish();
        });
        alertDialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog myDialog = alertDialog.create();
        myDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(MainActivity.this,resultCode,Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK) {
            int sound = data.getIntExtra("sound", 0);
            int music = data.getIntExtra("music", 0);
            Log.i("Check", sound + " ," + music);
            if (sound == 1) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mouse_click);
            } else {
                mediaPlayer = new MediaPlayer();
            }
            backgroundMusic.stop();
            if (music == 1) {
                backgroundMusic = MediaPlayer.create(MainActivity.this, R.raw.background_music);
                backgroundMusic.start();
                backgroundMusic.setLooping(true);
            }

        }
    }
}

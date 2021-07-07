package com.myapplicationdev.android.fyp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Scoreboards.ScoreboardActivity;


public class MainActivity extends AppCompatActivity {
    Button btnStart, btnRevision, btnHowToPlay, btnSettings, btnLeaderBoard;
    MediaPlayer mediaPlayer,backgroundMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.Play);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);
        btnRevision = findViewById(R.id.btnRevisionSection);
        btnSettings = findViewById(R.id.btnSettings);
        btnLeaderBoard = findViewById(R.id.btnLeaderBoard);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mouse_click);
        backgroundMusic = MediaPlayer.create(MainActivity.this,R.raw.background_music);

        btnStart.setOnClickListener(view -> {
            //    Log.d("test", "test");
            Intent i = new Intent(MainActivity.this, DifficultySectionActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnHowToPlay.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, HowToPlayActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnRevision.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, RevisionSectionActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnSettings.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, SettingPageActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });


        btnLeaderBoard.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, ScoreboardActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        backgroundMusic.start();
        backgroundMusic.setLooping(true);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backgroundMusic.stop();
       AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
       alertDialog.setTitle("Are you sure you want to exit?");
       alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               backgroundMusic.stop();
               finish();

           }
       });
       alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.dismiss();
           }
       });
        AlertDialog myDialog = alertDialog.create();
        myDialog.show();
    }

}

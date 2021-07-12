package com.myapplicationdev.android.fyp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Scoreboards.ShowScoreboardActivity;


public class MainActivity extends AppCompatActivity {
    Button btnStart, btnRevision, btnHowToPlay, btnSettings, btnLeaderBoard;
    MediaPlayer mediaPlayer,backgroundMusic;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.Play);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);
        btnRevision = findViewById(R.id.btnRevisionSection);
        btnSettings = findViewById(R.id.btnSettings);
        btnLeaderBoard = findViewById(R.id.btnLeaderBoard);
        v =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);




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
            startActivity(i);
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
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Boolean sound = sharedPreferences.getBoolean("sound",true);
        if(sound==true){
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mouse_click);
        } else {
            mediaPlayer = new MediaPlayer();
        }
        Boolean music = sharedPreferences.getBoolean("music",true);
        if(music==true){
            backgroundMusic = MediaPlayer.create(MainActivity.this,R.raw.background_music);
        } else{
            backgroundMusic = new MediaPlayer();
        }
        if (backgroundMusic.isPlaying()){
            backgroundMusic.stop();
        }

    }

    @Override
    public void onBackPressed() {
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
